package com.sms.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.sms.dao.impl.DaoSupport;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月15日 下午6:55:45
* <pre>
* 类说明:
* </pre>
*/
public class BaseService {
	public Logger log = Logger.getRootLogger();
	@Resource(name = "daoSupport")
	public DaoSupport dao;
	
}
