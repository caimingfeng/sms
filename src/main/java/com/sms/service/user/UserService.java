package com.sms.service.user;

import com.sms.entity.User;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月26日 下午3:40:25
* <pre>
* 类说明:
* </pre>
*/
public interface UserService {
	boolean insert(User user) throws Exception;
	boolean deleteByAccount(String account);
	User findByAccount(String account) throws Exception;
	boolean edit(User user);
	
}
