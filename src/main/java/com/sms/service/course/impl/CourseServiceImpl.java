package com.sms.service.course.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sms.entity.Course;
import com.sms.entity.CourseCategory;
import com.sms.entity.Page;
import com.sms.service.BaseService;
import com.sms.service.course.CourseService;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
 * @author 蔡名锋:
 * @version 创建时间：2018年4月26日 下午2:25:47
 * 
 *          <pre>
* 类说明:处理课程业务
 *          </pre>
 */
@Service("courseService")
public class CourseServiceImpl extends BaseService implements CourseService {

	public List<Course> findAllByPage(Page page) {
		List<Course> courses = null;

		try {
			courses = (List<Course>) dao.findForList("CourseMapper.listPageCourse", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	public String findAllCategory() {

		List<CourseCategory> categories = null;
		try {
			categories = (List<CourseCategory>) dao.findForList("CourseCategoryMapper.findAll", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(categories);
	}

	public String saveCourse(PageData pd) throws Exception {
		String msg = null;
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		pd.put("createdBy", session.getAttribute(Const.SESSION_USERNAME));
		pd.put("creationDate", new Date());
		// pd.put("lastUpdateBy", session.getAttribute(Const.SESSION_USERNAME));
		// pd.put("lastUpdateDate", new Date());

		dao.insert("CourseMapper.insert", pd);
		msg = "success";
		return msg;
	}

	public Course findByCno(String cno) {
		try {
			return (Course) dao.findForObject("CourseMapper.findByCno", cno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String editCourse(PageData pd) throws Exception {
		String msg = null;
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		pd.put("lastUpdateBy", session.getAttribute(Const.SESSION_USERNAME));
		pd.put("lastUpdateDate", new Date());

		dao.update("CourseMapper.updateByCno", pd);
		msg = "success";
		return msg;
	}

	public String delCourse(String cno) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> data = new HashMap<>();
		
		data.put("msg", "程序异常，删除失败，请与工作人员反映");
		
		dao.delete("CourseMapper.deleteByCno", cno);
		data.put("result", "success");
		data.remove("msg");
		
		return JSON.toJSONString(data);
	}

	public String editOpen(PageData pd) throws Exception {
		String msg = "";
		dao.update("CourseMapper.editOpen", pd);
		msg = "success";
		return msg;
	}

}
