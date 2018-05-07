package com.sms.controller.admin.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.entity.Page;
import com.sms.entity.SysRole;
import com.sms.service.excel.impl.UserExportServiceImpl;
import com.sms.service.excel.impl.UserImportServiceImpl;
import com.sms.service.role.impl.RoleServiceImpl;
import com.sms.service.user.impl.UserServiceImpl;
import com.sms.util.ObjectExcelView;
import com.sms.util.PageData;

/**
 * @author 蔡名锋:
 * @version 创建时间：2018年4月4日 上午08:50:00
 * 
 *          <pre>
* 类说明:用户登录控制
 *          </pre>
 */
@Controller
@RequestMapping(value = "/user")
public class NewUserController extends BaseController {

	/**
	 * <pre>
	 * 说明：用户管理控制
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;
	String menuUrl = "user/listUsers.do"; // 菜单地址(权限用)
	@Resource(name = "userService")
	private UserServiceImpl userService;
	@Resource(name = "roleService")
	private RoleServiceImpl roleService;
	@Resource(name = "userImportService")
	private UserImportServiceImpl userImportService;
	@Resource(name = "userExportService")
	private UserExportServiceImpl userExportService;
	/**
	 * 显示用户列表(用户组)
	 */
	@RequestMapping(value = "/listUsers")
	public ModelAndView listUsers(Page page) throws Exception {

		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		page.setPd(pd);
		
		List<PageData> userList = userService.listPdPageUser(page); // 列出用户列表
		List<SysRole> roleList = roleService.listAllERRoles(); // 列出所有二级角色

		mv.setViewName("user/user_list");
		mv.addObject("userList", userList);
		mv.addObject("roleList", roleList);
		mv.addObject("pd", page.getPd());
		return mv;
	}

	/**
	 * 去修改用户页面
	 */
	@RequestMapping(value = "/goEditU")
	public ModelAndView goEditU() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		// 顶部修改个人资料
		String fx = pd.getString("fx");

		// System.out.println(fx);

		if ("head".equals(fx)) {
			mv.addObject("fx", "head");
		} else {
			mv.addObject("fx", "user");
		}
		logger.info("userId的值为：" + pd.getString("userId"));
		List<SysRole> roleList = roleService.listAllERRoles(); // 列出所有二级角色
		pd = userService.findByUId(pd); // 根据ID读取

		mv.setViewName("user/user_edit");
		mv.addObject("pd", pd);
		mv.addObject("msg", "editU");
		mv.addObject("roleList", roleList);

		return mv;
	}

	/**
	 * 判断用户名是否存在
	 */
	@RequestMapping(value = "/hasU")
	@ResponseBody
	public String hasU() {
		Map<String, String> map = new HashMap<String, String>();
		String result = "success";
		String msg = "";
		PageData pd;
		try {
			pd = this.getPageData();
			if (!userService.findByUIdAndAccount(pd)) {
				result = "error";
				msg = "此用户名已存在";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", result);
		map.put("msg", msg);
		// 返回结果
		return JSON.toJSONString(map);
	}

	/**
	 * 修改用户
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/editU")
	public ModelAndView editU() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		if (pd.getString("password") != null && !"".equals(pd.getString("password"))) {
			pd.put("password", new SimpleHash("SHA-1", pd.getString("account"), pd.getString("password")).toString());
		}
		try {
			userService.editU(pd);
			mv.addObject("msg", "success");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			mv.addObject("msg", "更新失败，请稍后再试");
		} finally {
			mv.setViewName("save_result");
			return mv;
		}
	}

	/**
	 * 删除用户
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/deleteU")
	@ResponseBody
	public String deleteU() {
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		try {
			pd = this.getPageData();
			if (!userService.deleteU(pd)) {
				map.put("result", "error");
				map.put("msg", "删除失败，请稍后重试");
				logger.info("删除失败");
			} else {
				map.put("result", "success");
				logger.info("删除成功");
			}

		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result", "error");
			map.put("msg", "删除失败，请稍后重试");
			logger.info("删除异常");
		} finally {
			return JSON.toJSONString(map);
		}

	}

	/**
	 * 去新增用户页面
	 */
	@RequestMapping(value = "/goAddU")
	public ModelAndView goAddU() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<SysRole> roleList;

		roleList = roleService.listAllERRoles(); // 列出所有二级角色

		mv.setViewName("user/user_edit");
		mv.addObject("msg", "saveU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);

		return mv;
	}

	/**
	 * 新增一个用户，该用户仅存在用户表里
	 */
	@RequestMapping(value = "/saveU")
	public ModelAndView saveU() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		boolean flag = userService.saveU(pd);
		
		if (flag) {
			mv.addObject("msg", "success");
		} else {
			mv.addObject("msg", "failed");
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value = "/deleteAllU")
	@ResponseBody
	public String deleteAllU() {
		PageData pd = new PageData();
		String result = null;
		
		try {
			pd = this.getPageData();
			result = userService.deleteAllU(pd);
		} catch (Exception e) {
			logger.info("批量删除操作失败");
			logger.error(e.toString(), e);
			pd.put("result", "failed");
			pd.put("errormsg", "服务器错误，删除失败");
			return JSON.toJSONString(pd);
		} finally {
			logAfter(logger);
		}

		return result;
	}

	/*
	 * 导出用户信息到EXCEL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		try {
			Map<String, Object> dataMap = userExportService.UserExport(pd);
			logger.info("开始导出Excel");
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv, dataMap);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			String errorMsg = "系统崩溃，导出失败，请稍后再试或与开发人员联系";
			mv.addObject("pd", pd);
			mv.setViewName("forward:listUsers?status=1&errorMsg=" + errorMsg);
		}
		return mv;
	}

	/**
	 * 打开上传EXCEL页面
	 */
	@RequestMapping(value = "/goUploadExcel")
	public ModelAndView goUploadExcel() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("user/uploadexcel");
		return mv;
	}

	/**
	 * 下载模版
	 */
	@RequestMapping(value = "/downExcel")
	public void downExcel(HttpServletResponse response) throws Exception {
		
		userImportService.downExcel(response, this.getPageData());
		
	}

	/**
	 * 从EXCEL导入到数据库
	 */
	@RequestMapping(value = "/readExcel")
	public ModelAndView readExcel(@RequestParam(value = "excel", required = false) MultipartFile file)
			throws Exception {

		logger.info("执行文件导入操作.......");

		boolean flag = false;
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		if (null != file && !file.isEmpty()) {
			logger.info("上传的文件不为空");
			
			flag = userImportService.UserImport(file, pd);
			
			if (flag) {
				mv.addObject("msg", "success");
			} else {
				if (pd.getString("message") != null) {
					logger.info(pd.get("message"));
					mv.addObject("msg", "导入失败：\\n" + pd.getString("message"));
					//mv.addObject("msg", "导入失败:\\n111");
				} else {
					mv.addObject("msg", "导入失败，请检查Excel格式是否正确,或向联系开发人员反映");
				}
			}
		}

		mv.setViewName("save_result");
		return mv;
	}
}
