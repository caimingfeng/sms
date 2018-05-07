package com.sms.controller.menu;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.entity.SysMenu;
import com.sms.service.menu.impl.MenuServiceImpl;
import com.sms.util.Const;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月3日 上午11:50:53
* <pre>
* 类说明:
* </pre>
*/
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {
	/**
	 * <pre>说明</pre>
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MenuServiceImpl menuService;
	
	/**
	 * 
	 * <pre>说明:显示所有顶级菜单</pre>
	 */
	@RequestMapping(value = "/listTopMenu")
	public ModelAndView listTopMenu() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<SysMenu> SysMenuList = menuService.listMenuByParentId(0);
			modelAndView.addObject("menuList", SysMenuList);
			modelAndView.setViewName("menu/menuList");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return modelAndView;
	}
	
	/**
	 * 获取当前菜单的所有子菜单
	 * @param menuId
	 * @param response
	 */
	@RequestMapping(value="/sub")
	public void getSub(@RequestParam Integer menuId,HttpServletResponse response)throws Exception{
		try {
			logger.info("开始获取menuId为"+menuId+"的子菜单");
			List<SysMenu> subMenu = menuService.listMenuByParentId(menuId);
			/*JSONArray arr = JSONArray.fromObject(subMenu);*/
			PrintWriter out;
			
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			/*String json = arr.toString();*/
			String json = JSON.toJSONString(subMenu);
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	/**
	 * 请求编辑菜单页面
	 */
	@RequestMapping(value = "/toEdit", produces = "text/html;charset=UTF-8")
	public ModelAndView toEdit(SysMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		SysMenu SysMenu = new SysMenu();
		int menuId = menu.getMenuId();
		SysMenu = menuService.findMenuByMenuId(menuId);
		SysMenu newParentMenu = new SysMenu();
		newParentMenu = menuService.findMenuByMenuId(SysMenu.getParentId());
		List<SysMenu> SysMenuList = menuService.listMenuByParentId(0);
		modelAndView.addObject("menuList", SysMenuList);
		modelAndView.addObject("parentMenuName", newParentMenu.getMenuName());
		modelAndView.addObject("parentMenuID", newParentMenu.getMenuId());
		modelAndView.addObject("menuId", menu.getMenuId());
		modelAndView.addObject("menuName", SysMenu.getMenuName());
		modelAndView.addObject("menuUrl", SysMenu.getMenuUrl());
		modelAndView.addObject("menuOrder", SysMenu.getMenuOrder());
		modelAndView.addObject("menuType", SysMenu.getMenuType());
		modelAndView.setViewName("menu/menuEdit");
		return modelAndView;
	}
	
	/**
	 *  保存编辑菜单信息
	 */
	@RequestMapping(value = "/saveEdit")
	public ModelAndView saveEdit(SysMenu SysMenu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			SysMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			SysMenu.setRemark("last action is edit");
			SysMenu.setLastUpdateDate(new Date());
			logger.info("开始保存菜单信息...");
			menuService.editMenu(SysMenu);
			modelAndView.addObject("resultMsg", "编辑成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "编辑失败");
			logger.error("保存信息失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}
	
	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/saveDelete")
	public ModelAndView saveDelete(SysMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		int menuId = menu.getMenuId();
		try {
			SysMenu SysMenu = menuService.findMenuByMenuId(menuId);
			SysMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			SysMenu.setRemark("last action is delete");
			SysMenu.setLastUpdateDate(new Date());
			logger.info("开始执行删除菜单操作...");
			menuService.deleteMenuById(SysMenu);
			modelAndView.addObject("resultMsg", "删除成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "删除失败");
			logger.error("删除信息失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}
	
	/**
	 * 请求新增菜单页面
	 */
	@RequestMapping(value = "/toAdd")
	public ModelAndView toAdd() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<SysMenu> SysMenuList = menuService.listMenuByParentId(0);
		modelAndView.addObject("menuList", SysMenuList);
		modelAndView.setViewName("menu/menuAdd");
		return modelAndView;
	}
	
	/**
	 * 保存新增菜单信息
	 */
	@RequestMapping(value = "/saveAdd", produces = "text/html;charset=UTF-8")
	public ModelAndView saveAdd(SysMenu SysMenu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Integer newId = menuService.findMaxMenuId() + 1;
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			SysMenu.setMenuId(newId);
			SysMenu.setCreatedBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			SysMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			SysMenu.setRemark("last action is add");
			SysMenu.setStsCd("A");
			SysMenu.setCreationDate(new Date());
			logger.info("开始新增菜单...");
			menuService.insertMenu(SysMenu);
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
	 * 请求编辑菜单图标页面
	 */
	@RequestMapping(value = "/toEditIcon")
	public ModelAndView toEditIcon(SysMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		int menuId = menu.getMenuId();
		SysMenu SysMenu = menuService.findMenuByMenuId(menuId);
		String icon = SysMenu.getMenuIcon();
		modelAndView.addObject("menuId", menuId);
		modelAndView.addObject("menuIcon", icon);
		modelAndView.setViewName("menu/menuIcon");
		return modelAndView;
	}
	
	/**
	 *  保存修改图标信息
	 */
	@RequestMapping(value = "/saveEditIcon")
	public ModelAndView saveEditIcon(SysMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			menu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			menu.setRemark("last action is edit menu icon");
			menu.setLastUpdateDate(new Date());
			menuService.editMenuIconById(menu);
			modelAndView.addObject("resultMsg", "修改图标成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "修改图标失败");
			logger.error("修改图标失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}
	
	/**
	 * 保存编辑母菜单name
	 */
	@RequestMapping(value = "/saveEditParentMenuName")
	public ModelAndView saveEditParentMenuName(SysMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			SysMenu newMenu = menuService.findMenuByMenuId(menu.getMenuId());
			newMenu.setMenuName(menu.getMenuName());
			newMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setRemark("last action is edit name");
			newMenu.setLastUpdateDate(new Date());
			menuService.editMenu(newMenu);
			modelAndView.addObject("resultMsg", "编辑成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "编辑失败");
			logger.error("保存信息失败", e);
		}
		// modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 *  保存编辑母菜单order
	 */
	@RequestMapping(value = "/saveEditParentMenuOrder")
	public ModelAndView saveEditParentMenuOrder(SysMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			SysMenu newMenu = menuService.findMenuByMenuId(menu.getMenuId());
			newMenu.setMenuOrder(menu.getMenuOrder());
			newMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setRemark("last action is edit order");
			newMenu.setLastUpdateDate(new Date());
			menuService.editMenu(newMenu);
			modelAndView.addObject("resultMsg", "编辑成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "编辑失败");
			logger.error("保存信息失败", e);
		}
		// modelAndView.setViewName("save_result");
		return modelAndView;
	}

}
