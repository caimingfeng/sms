package com.sms.service.openclass.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.sms.entity.Course;
import com.sms.entity.CourseMessage;
import com.sms.entity.Page;
import com.sms.entity.Teacher;
import com.sms.service.BaseService;
import com.sms.service.openclass.OpenClassService;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月28日 下午9:58:20
* <pre>
* 类说明:
* </pre>
*/
@Service("openClassService")
public class OpenClassServiceImpl extends BaseService implements OpenClassService {

	public List<CourseMessage> listPageCourseMessage(Page page) throws Exception {
		List<CourseMessage> courseMessages = null;
		
		courseMessages = (List<CourseMessage>) dao.findForList("CourseMessageMapper.listPageCM", page);
		return courseMessages;
	}

	public String delClass(PageData pd) throws Exception {
		String result = null;
		
		dao.delete("CourseMessageMapper.deleteById", pd);
		result = "success";
		return result;
		
	}

	public String editClass(PageData pd) throws Exception {
		Integer total = (Integer) dao.update("CourseMessageMapper.editClassById", pd);
		if (total>0) {
			return "success";
		} else {
			return null;
		}
	}

	public List<Course> findAllCourse() throws Exception {
		return (List<Course>) dao.findForList("CourseMapper.findAll", null);
	}

	public List<Teacher> findAllTeacher() throws Exception {
		// TODO Auto-generated method stub
		return (List<Teacher>) dao.findForList("TeacherMapper.findAll", null);
	}

	public String saveClass(PageData pd) throws Exception {
		String msg = "";
		int count = (int) dao.findForObject("CourseMessageMapper.findByTTC", pd);
		if (count == 0) {
			Subject user = SecurityUtils.getSubject();
			Session session = user.getSession();
			
			pd.put("createdBy", session.getAttribute(Const.SESSION_USERNAME));
			pd.put("creationDate", new Date());
			
			Calendar date = Calendar.getInstance();
		    String term = String.valueOf(date.get(Calendar.YEAR)+"年");
			int month = date.get(Calendar.MONTH);
			if (month >= 1 && month <= 7) {
				term = term + " 春季";
			} else {
				term = term + " 秋季";
			}
			pd.put("term", term);
			
			dao.insert("CourseMessageMapper.insertSelective", pd);
			msg = "success";
		} else {
			msg = "班级信息已经存在，不用再次开班";
		}
		
		return msg;
	}

	public List<CourseMessage> findByPd(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<CourseMessage>) dao.findForList("CourseMessageMapper.findByParentId", pd);
	}

	public String saveMessage(PageData pd) throws Exception {
		String msg = "";
		CourseMessage cMessage = (CourseMessage) dao.findForObject("CourseMessageMapper.selectById", pd);
		if (cMessage!=null) {
			pd.put("cno", cMessage.getCno());
			pd.put("teaId", cMessage.getTeaId());
			pd.put("teachingClass", cMessage.getTeachingClass());
			
			Subject user = SecurityUtils.getSubject();
			Session session = user.getSession();
			
			pd.put("createdBy", session.getAttribute(Const.SESSION_USERNAME));
			pd.put("creationDate", new Date());
			
			Calendar date = Calendar.getInstance();
		    String term = String.valueOf(date.get(Calendar.YEAR)+"年");
			int month = date.get(Calendar.MONTH);
			if (month >= 1 && month <= 7) {
				term = term + " 春季";
			} else {
				term = term + " 秋季";
			}
			pd.put("term", term);
			dao.insert("CourseMessageMapper.insertSelective", pd);
		}
		else {
			msg = "程序出错，增加信息失败";
		}
		msg = "success";
		return msg;
	}

	public String delelteMessage(PageData pd) throws Exception {
		String msg = "";
		dao.delete("CourseMessageMapper.deleteById", pd);
		msg = "success";
		return msg;
	}

	public String editMessage(PageData pd) throws Exception {
		String msg = "";
		dao.update("CourseMessageMapper.editClassById", pd);
		msg = "success";
		return msg;
	}

	
}
