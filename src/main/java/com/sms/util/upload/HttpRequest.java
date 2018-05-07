package com.sms.util.upload;

import java.util.Map;

/**
 * @Project: ykframework
 * @Title: F5HttpRequest.java
 * @Description:  F5HttpClient、F5HttpsClient提交请求前使用的信息，包括请求地址、参数等。
 * @Company: 
 * @Author: 刘守建
 * @Date: Dec 6, 2011 4:27:33 PM
 * @version 1.0
 */
public class HttpRequest {

	private String method = null;
	private String url = null;
	private HttpParame[] httpParams = null;
	private Map<String, String> headers = null;
	
	private boolean isBaseAuth = false;
	private String strBasePassword = null;
	private String strBaseAuthName = null;
	
	/**
	 * 提交方式
	 * @author 刘守建
	 */
	public static class MethodType{
		public static String GET = "GET";
		public static String POST = "POST";
	}
	
	/**
	 * 构造函数
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param method 使用的提交方式 POST\GET
	 * @param headers 请求包头信息
	 */
	public HttpRequest (String url,HttpParame[] params,String method,Map<String, String> headers){
		this.url = url;
		this.method = method;
		this.httpParams = params;
		this.headers = headers;
	}

	/**
	 * 获取提交方式 POST\GET
	 * @return
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * 设置提交方式 POST\GET
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 获取请求包头信息
	 * @return
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * 设置请求包头信息
	 * @param headers
	 */
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	/**
	 * 获取请求地址
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置请求地址
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取求情参数
	 * @return
	 */
	public HttpParame[] getHttpParams() {
		return httpParams;
	}

	/**
	 * 设置请求参数
	 * @param httpParams
	 */
	public void setHttpParams(HttpParame[] httpParams) {
		this.httpParams = httpParams;
	}

	/**
	 * 判断是否使用base64认证
	 * @return
	 */
	public boolean isBaseAuth() {
		return isBaseAuth;
	}

	/**
	 * 设置是否使用base64认证
	 * @param isBaseAuth
	 */
	public void setBaseAuth(boolean isBaseAuth) {
		this.isBaseAuth = isBaseAuth;
	}

	/**
	 * 获取base64认证的密码
	 * @return
	 */
	public String getBasePassword() {
		return strBasePassword;
	}

	/**
	 * 设置base64认证密码
	 * @param strBasePassword
	 */
	public void setBasePassword(String strBasePassword) {
		this.strBasePassword = strBasePassword;
	}

	/**
	 * 获取base64认证账号
	 * @return
	 */
	public String getBaseAuthName() {
		return strBaseAuthName;
	}

	/**
	 * 设置base64认证账号
	 * @param strBaseAuthName
	 */
	public void setBaseAuthName(String strBaseAuthName) {
		this.strBaseAuthName = strBaseAuthName;
	}

}
