package com.sms.service.student;

import com.sms.entity.Student;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月3日 下午8:05:03
* <pre>
* 类说明:
* </pre>
*/
public interface StudentService {
	void insertStu(Student student);
	void deleteStu(Student student);
	Student findStu(Student student);
	void editStu(Student student);
}
