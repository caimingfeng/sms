package com.sms.controller.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.entity.FlowUser;
import com.sms.entity.SysMenu;
import com.sms.entity.SysRole;
import com.sms.entity.TwoEntityResponse;
import com.sms.entity.User;
import com.sms.service.menu.impl.MenuServiceImpl;
import com.sms.service.role.impl.RoleServiceImpl;
import com.sms.service.user.impl.UserServiceImpl;
import com.sms.util.Const;
import com.sms.util.PageData;
import com.sms.util.Tools;

/**
 * @author 蔡名锋:
 * @version 创建时间：2018年3月29日 上午08:50:00
 * 
 *          <pre>
* 类说明:用户登录控制
 *          </pre>
 */
@Controller
@RequestMapping(value = "/userControl")
public class UserController extends BaseController {
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private RoleServiceImpl roleService;
	@Autowired
	private MenuServiceImpl menuService;
	/**
	 * <pre>
	 * 说明
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 访问登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login_toLogin")
	public ModelAndView toLogin() throws Exception {

		logger.info("访问系统的登录界面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); // 读取系统名称
		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		User user = (User) session.getAttribute(Const.NEW_USER);
		if (user != null) {
			mv.setViewName("redirect:userControl/mainIndex");
		} else {
			mv.setViewName("login");
			mv.addObject("pd", pd);
		}
		
		return mv;
	}

	/**
	 * 验证用户登录信息
	 * 
	 * @return 返回json信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/newLogin", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String userLogin(@RequestBody User user) throws Exception {

		logger.info("验证用户名和密码..." + ",roleId=" + user.getRoleId());

		// 验证码校验
		String code = user.getCode();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		String sessionCode = (String) session.getAttribute(Const.SESSION_SECURITY_CODE);
		if (sessionCode == null || !sessionCode.equalsIgnoreCase(code)) {
			return getLoginResponseJSON("399", "验证码错误或过期");
		}

		// 确认账号和密码的组合
		PageData pageData = this.getPageData();
		HttpServletRequest request = this.getRequest();

		String passwd = new SimpleHash("SHA-1", user.getAccount(), user.getPassword()).toString();
		pageData.put("account", user.getAccount());
		pageData.put("password", passwd);
		pageData.put("roleId", user.getRoleId());

		// 去数据库验证账号和密码用户状态等信息
		if (!userService.isExist(user.getAccount())) {
			return getLoginResponseJSON("400", "账号无效");
		}
		if (!userService.validateUser(pageData)) {
			return getLoginResponseJSON("401", "账号或密码有误");
		}
		if (!userService.judeRole(pageData)) {
			return getLoginResponseJSON("402", "用户类型有误");
		}

		// 更新用户登录信息
		Date currentTime = new Date();
		pageData.put("ip", getClientIP(request));
		pageData.put("lastLogin", currentTime);
		pageData.put("lastUpDate", currentTime);
		pageData.put("lastUpdateBy", "system");
		if (!userService.updateLoginInfo(pageData)) {
			return getLoginResponseJSON("500", "系统繁忙,请稍后再试");
		}

		// 获取用户信息
		User newUser = userService.findByAccount(user.getAccount());

		// Shiro的验证过程
		UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			return getLoginResponseJSON("500", "系统内部错误");
		}

		// 更新用户流水
		FlowUser flowUser = new FlowUser();
		flowUser.setUllId(0);
		flowUser.setUserId(newUser.getUserId());
		flowUser.setToken("1");
		flowUser.setOperatingType("1");
		flowUser.setOperatingDate((Date) pageData.get("lastLogin"));
		flowUser.setCreatedBy("system");
		Date date = new Date();
		flowUser.setCreationDate(date);
		flowUser.setLastUpdateBy("system");
		flowUser.setLastUpdateDate(date);
		if (!userService.insertFlowUser(flowUser)) {
			return getLoginResponseJSON("500", "系统f繁忙，请稍后再试");
		}

		session.setAttribute(Const.NEW_USER, newUser);
		session.setAttribute(Const.SESSION_USERNAME, newUser.getAccount());

		// 根据用户角色进行重定向
		if (newUser.getRoleId() == 1) {
			return getLoginResponseJSON("200", "userControl/mainIndex");
		} else if (newUser.getRoleId() == 2) {
			return getLoginResponseJSON("200", "userControl/mainIndex");
		} else {
			return getLoginResponseJSON("200", "userControl/mainIndex");
		}
	}

	@RequestMapping(value = "/mainIndex")
	public ModelAndView toMainIndex() throws Exception {
		logger.info("进入首页面...");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); // 读取系统名称

		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			User user = (User) session.getAttribute(Const.NEW_USER);
			
			if (user == null) {
				logger.info("session失效");
				mv.setViewName("redirect:../login.jsp");
				return mv;
			}
			logger.info("当前登录的用户名为：" + user.getAccount());
			List<SysMenu> menuList = (List<SysMenu>) session.getAttribute("menuList");
			if (menuList != null) {
				
			} else {
				// 查询角色权限
				SysRole role = roleService.findByRoleId(user.getRoleId());
				String visibleMenuId = role.getVisibleMenuId();
				List<Integer> menuIds = getMenuIds(visibleMenuId);
				if (menuIds != null) {
					List<SysMenu> menus = menuService.findMenuById(menuIds);

					if (menus != null) {
						session.setAttribute(Const.SESSION_menuList, menus);
					} else {
						logger.info("该角色的所属权限在权限表已删除");
					}
				}
			}
			

			/*
			 * System.out.println("父级菜单的长度:"+menus.size()); for (SysMenu
			 * sysMenu : menus) { List<SysMenu> subMenu =
			 * sysMenu.getSubMenu();
			 * System.out.println(sysMenu.getMenuName()+"下子菜单个数：" +
			 * subMenu.size()); }
			 */

			mv.addObject("user", user);
		} catch (Exception e) {
			mv.setViewName("login");
			logger.error(e.getMessage(), e);
		}

		mv.setViewName("admin/index");
		mv.addObject("pd", pd);

		return mv;
	}

	/**
	 * 返回登录操作响应信息
	 * 
	 * @param code
	 *            200-成功or 399-402 -请求失败 or 500 -服务器错误
	 * @return Response JSON
	 */
	public static String getLoginResponseJSON(String code, String message) {

		TwoEntityResponse response = new TwoEntityResponse();
		response.setCode(code);
		response.setMsg(message);

		return JSON.toJSONString(response);

	}

	/**
	 * 获取客户端ip地址
	 * 
	 * @return IP in String
	 */
	public static String getClientIP(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return Time in String
	 */
	public static String getCurrentTime() {

		Date date = new Date();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(date).toString();

	}

	/**
	 * 权限处理
	 */
	public static List<Integer> getMenuIds(String visibleMenuId) {
		System.out.println("未处理前可见菜单：" + visibleMenuId + "******");
		if (visibleMenuId == null) {
			return null;
		} else {
			String newVisibleMenuId = visibleMenuId.replace(" ", "");

			if ("".equals(newVisibleMenuId)) {
				return null;
			} else {
				List<Integer> menuIds = new ArrayList<>();
				String[] strTemp = newVisibleMenuId.split(",");

				for (String str : strTemp) {
					if (str == "") {
						break;
					}
					menuIds.add(Integer.parseInt(str));
				}
				return menuIds;
			}
		}
	}
}
