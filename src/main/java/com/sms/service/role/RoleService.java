package com.sms.service.role;

import com.sms.entity.SysRole;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月29日 下午9:37:22
* <pre>
* 类说明:
* </pre>
*/
public interface RoleService {
	boolean insert(SysRole role) throws Exception;
	boolean deleteRoleById(SysRole role) throws Exception;
	SysRole findByRoleId(Integer roleId) throws Exception;
	boolean edit(Integer roleId);
}
