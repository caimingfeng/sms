package com.sms.controller.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sms.entity.SysMenu;
import com.sms.entity.SysRole;
import com.sms.service.menu.impl.MenuServiceImpl;
import com.sms.service.role.impl.RoleServiceImpl;
import com.sms.util.Const;

/**
 * @author 蔡名锋:
 * @version 创建时间：2018年4月2日 上午11:00:53
 * 
 *          <pre>
* 类说明:
 *          </pre>
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends com.sms.controller.BaseController {

	/**
	 * <pre>
	 * 说明
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleServiceImpl roleService;
	@Autowired
	private MenuServiceImpl menuService;

	/**
	 * 显示所有角色列表
	 */
	@RequestMapping(value = "/listRole")
	public ModelAndView listRole() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<SysRole> SysRoleList = roleService.findAllRole();
			modelAndView.addObject("roleList", SysRoleList);
			modelAndView.setViewName("role/RoleList");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return modelAndView;
	}

	/**
	 * 请求角色菜单授权页面（仅顶层菜单）
	 */
	@RequestMapping(value = "/toRights")
	public ModelAndView toRights(int roleId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<SysMenu> topMenuList = menuService.listMenuByParentId(0); // 顶层菜单列

		SysRole SysRole = roleService.findByRoleId(roleId);

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("role", SysRole);

		modelAndView.addObject("roleId", roleId);
		modelAndView.addObject("topMenu", topMenuList);
		modelAndView.addObject("visibleMenu", SysRole.getVisibleMenuId());
		modelAndView.setViewName("role/RoleRights");
		return modelAndView;
	}

	/**
	 * 保存角色菜单权限
	 */
	@RequestMapping(value = "/saveRights", produces = "text/html;charset=UTF-8")
	public ModelAndView saveRights(Integer roleId, String[] rights) throws Exception {
		ModelAndView modelAndView = new ModelAndView();

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		SysRole role = (SysRole) session.getAttribute("role");
		if (role == null) {
			role = roleService.findByRoleId(roleId);
		}
		try {
			StringBuffer newRights = new StringBuffer();

			if (rights.length > 1) {
				for (String string : rights) {
					logger.info(string);
				}
				newRights = newRights.append(rights[0]);
				for (int i = 1; i < rights.length; i++) {
					newRights = newRights.append("," + rights[i]);
				}
			} else if (rights.length == 1) {
				newRights = newRights.append(rights[0]);
				logger.info(newRights);
			} else {
				logger.info("收回所有权限");
			}
			logger.info(newRights.toString());
			role.setVisibleMenuId(newRights.toString());
			role.setLastUpdateBy((String) session.getAttribute(Const.SESSION_USERNAME));
			role.setLastUpdateDate(new Date());
			role.setRemark("last action is update visible menu");
			roleService.setVisibleByMenuIdAndRoleId(role);
			modelAndView.addObject("resultMsg", "权限更新成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "权限更新失败");
			logger.error("权限保存失败", e);
		}

		if (roleId == 1) {
			logger.info("更新管理员权限菜单");
			List<Integer> menuIds = getMenuIds(role.getVisibleMenuId());
			List<SysMenu> menus = menuService.findMenuById(menuIds);

			session.setAttribute(Const.SESSION_menuList, menus);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * 显示编辑页面
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(SysRole SysRole) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		/* int roleId=role.getRoleId(); */
		/* SysRole SysRole = roleService.findByRoleId(roleId); */
		;
		logger.info("角色id:" + SysRole.getRoleId() + ",角色名称:" + SysRole.getRoleName());
		modelAndView.addObject("SysRole", SysRole);
		modelAndView.setViewName("role/RoleEdit");
		return modelAndView;
	}

	/**
	 * 保存编辑信息
	 */
	@RequestMapping(value = "/saveEdit", produces = "text/html;charset=UTF-8")
	public ModelAndView saveEdit(SysRole SysRole) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();

		logger.info("修改后的名称：" + SysRole.getRoleName());
		try {
			SysRole.setRemark("last action is update roleName");
			SysRole.setLastUpdateBy((String) session.getAttribute(Const.SESSION_USERNAME));
			SysRole.setLastUpdateDate(new Date());
			roleService.editRoleById(SysRole); // 根据SysRole的ID来编辑相同ID的记录变成SysRole的数据
			modelAndView.addObject("resultMsg", "编辑成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "编辑失败");
			logger.error("编辑失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * 保存删除信息
	 */
	@RequestMapping(value = "/saveDelete", produces = "text/html;charset=UTF-8")
	public ModelAndView saveDelete(SysRole role) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		int roleId = role.getRoleId();
		SysRole newRole = roleService.findByRoleId(roleId);
		try {
			newRole.setLastUpdateBy((String) session.getAttribute(Const.SESSION_USERNAME));
			newRole.setRemark("删除成功");
			newRole.setLastUpdateDate(new Date());
			roleService.deleteRoleById(newRole);
			modelAndView.addObject("resultMsg", "删除成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "删除失败");
			logger.error("删除失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * 请求新增角色页面
	 */
	@RequestMapping(value = "/toAdd")
	public ModelAndView toAdd() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role/RoleAdd");
		return modelAndView;
	}

	/**
	 * 保存新增角色信息
	 */
	@RequestMapping(value = "/saveAdd", produces = "text/html;charset=UTF-8")
	public ModelAndView saveAdd(SysRole role) throws Exception {
		ModelAndView modelAndView = new ModelAndView();

		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();

		SysRole newRole = new SysRole();
		String roleName = role.getRoleName();
		try {
			newRole.setRoleId(roleService.findMaxRoleId() + 1);
			newRole.setRoleName(roleName);
			newRole.setCreatedBy((String) session.getAttribute(Const.SESSION_USERNAME));
			newRole.setLastUpdateBy((String) session.getAttribute(Const.SESSION_USERNAME));
			newRole.setStsCd("A");
			newRole.setRemark("新增成功");
			newRole.setCreationDate(new Date());
			roleService.insert(newRole);

			modelAndView.addObject("resultMsg", "新增成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "新增失败");
			logger.error("保存信息失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
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
