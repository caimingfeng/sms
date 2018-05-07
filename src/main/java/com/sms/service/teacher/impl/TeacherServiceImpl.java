package com.sms.service.teacher.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.sms.entity.Student;
import com.sms.entity.Teacher;
import com.sms.service.BaseService;
import com.sms.service.teacher.TeacherService;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月3日 下午8:09:48
* <pre>
* 类说明:
* </pre>
*/
@Service("teacherService")
public class TeacherServiceImpl extends BaseService implements TeacherService {
	
	@Override
	public void insertTea(Teacher teacher) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTea(Teacher teacher) {
		// TODO Auto-generated method stub

	}

	@Override
	public Student findTea(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editTea(Teacher teacher) {
		// TODO Auto-generated method stub

	}

	//根据教工号查询教师名称
	public Teacher findTeaNameByTno(String tno) throws Exception {
		// TODO Auto-generated method stub
		return (Teacher) dao.findForObject("TeacherMapper.findTeaNameByTno", tno);
	}
	public Teacher findTeacherMessage() throws Exception {
		Subject user = SecurityUtils.getSubject();
		Session session = user.getSession();
		
		String tno = (String) session.getAttribute(Const.SESSION_USERNAME);
		return (Teacher) dao.findForObject("TeacherMapper.findByTno", tno);
	}

	public String saveMessage(PageData pd) throws Exception {
		dao.update("TeacherMapper.updateByTno", pd);
		return "success";
	}
}
