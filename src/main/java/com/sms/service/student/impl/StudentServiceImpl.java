package com.sms.service.student.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.sms.entity.Page;
import com.sms.entity.Student;
import com.sms.service.BaseService;
import com.sms.service.student.StudentService;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月3日 下午8:09:12
* <pre>
* 类说明:
* </pre>
*/
@Service("studentService")
public class StudentServiceImpl extends BaseService implements StudentService {
	
	@Override
	public void insertStu(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteStu(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public Student findStu(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editStu(Student student) {
		// TODO Auto-generated method stub

	}

	public Student findStuNameBySno(String sno) throws Exception {
		// TODO Auto-generated method stub
		return (Student) dao.findForObject("StudentMapper.findStuNameBySno", sno);
	}

	public List<PageData> listPdPageStudent(Page page) throws Exception {
		PageData pd = page.getPd();
		page.setPd(pd);
		log.info("开始分页查询学生");
		
		/**
		 * 2.按条件检索学生并返回数据
		 */
		return (List<PageData>) dao.findForList("StudentMapper.studentlistPage", page);
	}

	public PageData findStudentMessage() throws Exception {
		Subject user = SecurityUtils.getSubject();
		Session session = user.getSession();
		
		String sno = (String) session.getAttribute(Const.SESSION_USERNAME);
		return (PageData) dao.findForObject("StudentMapper.findBySno", sno);
	}

	public String saveMessage(PageData pd) throws Exception {
		dao.update("StudentMapper.updateBySno", pd);
		return "success";
	}
}
