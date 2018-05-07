package com.sms.service.excel;

import org.springframework.web.multipart.MultipartFile;

import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月15日 下午2:54:40
* <pre>
* 类说明:Exce业务逻辑导入用户的接口，要求实现学生导入和教师导入
* </pre>
*/
public interface UserImportService {
	public boolean UserImport( MultipartFile file, PageData pd);
}
