package com.sms.service.excel;

import java.util.Map;

import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月15日 下午6:16:31
* <pre>
* 类说明:
* </pre>
*/
public interface UserExportService {

	public Map<String, Object> UserExport(PageData pd);
}
