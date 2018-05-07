package com.sms.interceptor.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * @author LRJ
 *  2015-3-6
 */
public class ShiroRealm extends AuthorizingRealm {

	   /**  
	    * 登录验证  
	    */   
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		 //令牌中可以取出用户名
		 String username = (String)token.getPrincipal();
		 //令牌中可以取出密码
	     String password = new String((char[])token.getCredentials());
		
	     if(null != username && null != password){
	    	 return new SimpleAuthenticationInfo(username, password, getName());
	     }else{
	    	 return null;
	     }
	     
	}
	
	/*
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {


		
		return null;
	}

}
