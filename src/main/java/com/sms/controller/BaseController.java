package com.sms.controller;


import java.io.Serializable;

import com.sms.entity.Page;
import com.sms.util.Logger;
import com.sms.util.PageData;
import com.sms.util.UuidUtil;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月27日 上午10:10:40
* <pre>
* 类说明:
* </pre>
*/
public class BaseController implements Serializable {
	
	protected Logger logger = Logger.getLogger(BaseController.class);

	private static final long serialVersionUID = 6357869213649815390L;
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 得到分页列表的信息 
	 */
	public Page getPage(){
		
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("start开始=================================");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end结束==================================");
	}
	
}
