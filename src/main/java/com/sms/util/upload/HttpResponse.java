package com.sms.util.upload;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * @Project: ykframework
 * @Title: F5HttpResponse.java
 * @Description:  F5HttpClient、F5HttpsClient提交后的返回结果类
 * @Company: 
 * @Author: 刘守建
 * @Date: Dec 6, 2011 4:27:33 PM
 * @version 1.0
 */
public class HttpResponse {

	/**
	 * HTTP状态码
	 */
	private int statusCode = 0;
	
	/**
	 * HTTP包体信息
	 */
	private String body = null;
	
	/**
	 * HTTP包头信息
	 */
	private Map<String, List<String>> headerFields = null;
	
	public HttpResponse(){};
	
	HttpResponse(HttpURLConnection con){
		InputStream is = null;
		BufferedReader bf = null;
		StringBuffer stBuf = new StringBuffer();
		try{
			statusCode = con.getResponseCode();
			if (statusCode == 200) {
				is = con.getInputStream();
			} else {
				is = con.getErrorStream();
			}
			is = con.getInputStream();
			bf = new BufferedReader(new InputStreamReader(is,"GBK"));
			String s = null;
			while ((s = bf.readLine()) != null) {
				stBuf.append(s);
			}
			
			headerFields = con.getHeaderFields();
			
			body = stBuf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bf != null) {
					bf.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取cookie
	 * @return
	 */
	public String getCookie(){
//		Cookie[] cookies = new
		if(headerFields == null){
			return null;
		}
		List<String> list = headerFields.get("Set-Cookie");
		StringBuffer buff = new StringBuffer();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				String ste =list.get(i);
				buff.append(ste + ";");
			}
		}
		return buff.toString();
	}
	
	/**
	 * 获取HTTP状态码
	 * @return
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * 获取请求返回的包体内容
	 * @return
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * 获取请求返回的包头内容
	 * @return
	 */
	public Map<String, List<String>> getHeaderFields() {
		return headerFields;
	}
	
}
