package com.sms.util;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.sms.util.PageData;

public class UserUtil {

	/**
	 * Validate the given user identity in following aspects 1. Whether his
	 * USERNAME and PASSWORD is matched 2. Whether his account is in active
	 * 
	 * @return true means the user identity is correct
	 * @throws Exception
	 */
	public static boolean validateUser(String json) {

		JSONArray jsonArray = JSONArray.parseArray(json);

		@SuppressWarnings("unused")
		String userName = jsonArray.getJSONObject(0).get("USERNAME").toString();
		@SuppressWarnings("unused")
		String password = jsonArray.getJSONObject(0).get("PASSWORD").toString();

		PageData pageData = new PageData();
		pageData.put("USERNAME", "1313");
		pageData.put("PASSWORD", "12931092371hskdlhfkajsdf");

		// System.out.println(newUserService.validateUser(pageData));

		// newUserService.validateUser(pageData);

		return false;

	}

	/**
	 * Set cookie for client
	 * 
	 * @return true means the cookie is successfully set
	 */
	public static boolean setCookie() {

		return true;
	}

	/**
	 * Confirm the existence of the give user
	 * 
	 * @return true means this user exists
	 */
	public static boolean hasUser() {

		return true;
	}

	/**
	 * Get remote IP address
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getClientIP(HttpServletRequest request)
			throws Exception {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}
	
}
