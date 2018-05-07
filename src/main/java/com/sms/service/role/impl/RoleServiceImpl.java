package com.sms.service.role.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sms.entity.SysRole;
import com.sms.service.BaseService;
import com.sms.service.role.RoleService;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月29日 下午9:43:31
* <pre>
* 类说明:
* </pre>
*/
@Service("roleService")
public class RoleServiceImpl extends BaseService implements RoleService {
	
	@Override
	public boolean insert(SysRole role) throws Exception {
		// TODO Auto-generated method stub
		if(role.getRoleId()!=0){
			return(Integer)dao.insert("SysRoleMapper.addRole", role) == 1;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRoleById(SysRole role) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)dao.delete("SysRoleMapper.deleteRoleById", role) == 1;
	}

	@Override
	public SysRole findByRoleId(Integer roleId) throws Exception {
		// TODO Auto-generated method stub
		return (SysRole)dao.findForObject("SysRoleMapper.findByRoleId", roleId);
	}

	@Override
	public boolean edit(Integer roleId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//查询所有角色
	public List<SysRole> findAllRole() throws Exception {
		// TODO Auto-generated method stub
		return (List<SysRole>)dao.findForList("SysRoleMapper.findAllRole", null);
	}
	
	//根据菜单ID和角色ID设置该菜单对该角色是否可见。
	public void setVisibleByMenuIdAndRoleId(SysRole role) throws Exception{	
			dao.update("SysRoleMapper.setVisibleByMenuIdAndRoleId", role);
		}

	//根据ID修改角色
	public void editRoleById(SysRole role) throws Exception{
		if(role.getRoleId()!=0){
			dao.update("SysRoleMapper.editRoleById", role);
		}
	}

	//找到当前最大的角色ID
	public int findMaxRoleId() throws Exception{
		return (int)dao.findForObject("SysRoleMapper.findMaxRoleId", null);
	}

	public List<SysRole> listAllERRoles() throws Exception {
		// TODO Auto-generated method stub
		return (List<SysRole>)dao.findForList("SysRoleMapper.listAllERRoles", null);
	}

}
