package com.sms.service.menu;

import java.util.List;

import com.sms.entity.SysMenu;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月29日 下午11:08:36
* <pre>
* 类说明:
* </pre>
*/
public interface MenuService {
	boolean insertMenu(SysMenu menu) throws Exception;
	boolean deleteMenuById(SysMenu menu) throws Exception;
	boolean editMenu(SysMenu menu) throws Exception;
	List<SysMenu> findMenuById(List<Integer> menuIds) throws Exception;
}
