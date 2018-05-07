package com.sms.service.menu.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sms.entity.SysMenu;
import com.sms.service.BaseService;
import com.sms.service.menu.MenuService;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月29日 下午11:12:59
* <pre>
* 类说明:
* </pre>
*/
@Service("menuService")
public class MenuServiceImpl extends BaseService implements MenuService {
	@Override
	public boolean insertMenu(SysMenu menu) throws Exception {
		// TODO Auto-generated method stub
		if(menu.getMenuId()!=0){
			return(Integer)dao.insert("SysMenuMapper.addMenu", menu) == 1;
		}
		return false;
	}

	@Override
	public boolean deleteMenuById(SysMenu menu) throws Exception {
		// TODO Auto-generated method stub
		if(menu.getMenuId()!=0){
			return (Integer)dao.update("SysMenuMapper.deleteMenuById", menu) == 1;
		}
		return false;
	}

	@Override
	public boolean editMenu(SysMenu menu) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)dao.update("SysMenuMapper.editMenuById", menu) == 1;
	}

	@Override
	public List<SysMenu> findMenuById(List<Integer> menuIds) throws Exception {
		// TODO Auto-generated method stub
		return (List<SysMenu>)dao.findForList("SysMenuMapper.findtMenuById", menuIds);
	}
	
	//根据父级菜单id查询子菜单(0 - 顶级菜单)
	public List<SysMenu> listMenuByParentId(Integer parentId) throws Exception {
		// TODO Auto-generated method stub
		return (List<SysMenu>)dao.findForList("SysMenuMapper.listMenuByParentId", parentId);
	}

	//根据menuId查询菜单信息
	public SysMenu findMenuByMenuId(Integer menuId) throws Exception {
		// TODO Auto-generated method stub
		return (SysMenu)dao.findForObject("SysMenuMapper.findtMenuByMenuId", menuId);
	}
	
	//找到当前最大的MENU的ID
	public int findMaxMenuId() throws Exception{
		return (int)dao.findForObject("SysMenuMapper.findMaxMenuId", null);
	}

	//根据顶级菜单ID修改菜单图片
	public void editMenuIconById(SysMenu menu) throws Exception{
		dao.update("SysMenuMapper.editMenuIconById", menu);
	}
}
