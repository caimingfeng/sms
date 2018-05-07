package com.sms.service.teacher;

import com.sms.entity.Student;
import com.sms.entity.Teacher;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月3日 下午8:05:03
* <pre>
* 类说明:
* </pre>
*/
public interface TeacherService {
	void insertTea(Teacher teacher);
	void deleteTea(Teacher teacher);
	Student findTea(Teacher teacher);
	void editTea(Teacher teacher);
}
