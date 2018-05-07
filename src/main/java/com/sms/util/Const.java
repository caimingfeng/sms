package com.sms.util;

import org.springframework.context.ApplicationContext;
/**
 * 项目名称：
 * @author:LRJ
 * 
*/
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/userControl/login_toLogin";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)|(monitor)|(userControl)).*";	//不对匹配该值的访问路径拦截（正则）
	public static final String MAN_BUYER_ID = "MAN_BUYER_ID";
	public static final String DSR_ID = "DSR_ID";
	public static final String ROLE_ID = "ROLE_ID";
	public static final String ROLE_NAME = "ROLE_NAME";
	public static final String NEW_USER = "NEW_USER"; //当前登录用户对象
	public static final String TOKEN = "TOKEN";
	public static final String MAN_HOME_PAGE = "eshop.jsp";
	public static final String BUYER_HOME_PAGE = "eshop.jsp";
	
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	




	

	
}
