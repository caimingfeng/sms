package com.sms.controller.admin.openclass;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.entity.Course;
import com.sms.entity.CourseMessage;
import com.sms.entity.Page;
import com.sms.entity.Teacher;
import com.sms.service.openclass.impl.OpenClassServiceImpl;
import com.sms.service.role.impl.RoleServiceImpl;
import com.sms.service.score.impl.ScoreServiceImpl;
import com.sms.service.student.impl.StudentServiceImpl;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月28日 下午9:54:33
* <pre>
* 类说明:
* </pre>
*/
@Controller
@RequestMapping("/openClass")
public class OpenClassController extends BaseController {

	/**
	 * <pre>说明:开班管理控制器</pre>
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private OpenClassServiceImpl openClassService;
	@Resource(name = "roleService")
	private RoleServiceImpl roleService;
	@Resource(name = "studentService")
	private StudentServiceImpl studentService;
	@Resource(name = "scoreService")
	private ScoreServiceImpl scoreService;
	
	@RequestMapping("/openClassList")
	public ModelAndView toOpenClassPage(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		
		try {
			List<CourseMessage> courseMessages = openClassService.listPageCourseMessage(page);
			mv.addObject("list", courseMessages);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.setViewName("admin/openclass/open_class_list");
		}
		
		return mv;
	}
	
	@RequestMapping("/delClass")
	@ResponseBody
	public String delClass() {
		PageData pd = this.getPageData();
		String msg = null;
		try {
			msg = openClassService.delClass(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "程序异常，删除失败，请与工作人员反映";
		}
		
		return JSON.toJSONString(msg);
	}

	@RequestMapping("/editClass")
	@ResponseBody
	public String editClass() {
		PageData pd = this.getPageData();
		String msg = null;
		try {
			msg = openClassService.editClass(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "程序异常，修改失败，请与工作人员反映";
		}
		
		return JSON.toJSONString(msg);
	}
	@RequestMapping("/getAllCourse")
	@ResponseBody
	public String getAllCourse() {
		//PageData pd = this.getPageData();
		List<Course>list = null;
		try {
			list = openClassService.findAllCourse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JSON.toJSONString(list);
	}
	
	@RequestMapping("/goAddC")
	public ModelAndView goAddC() {
		//PageData pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		List<Course>courses = null;
		List<Teacher>teachers = null;
		try {
			courses = openClassService.findAllCourse();
			teachers = openClassService.findAllTeacher();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.setViewName("admin/openclass/add_open_class");
		}
		mv.addObject("courses",courses);
		mv.addObject("teachers",teachers);
		return mv;
	}
	
	
	@RequestMapping("/saveOpenClass")
	public ModelAndView saveOpenClass() {
		PageData pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		try {
			msg = openClassService.saveClass(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "程序异常，请与相关工作人员联系";
			e.printStackTrace();
		} finally {
			mv.setViewName("save_result");
			mv.addObject("msg",msg);
		}
		
		return mv;
	}
	
	@RequestMapping("/goEditC")
	public ModelAndView goEditC() {
		PageData pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		List<CourseMessage> courseMessages = null;
		try {
			courseMessages = openClassService.findByPd(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (courseMessages.size()>0) {
				mv.addObject("list",courseMessages);
			}
			mv.addObject("pd", pd);
			mv.setViewName("admin/openclass/class_message_details");
		}
		
		return mv;
	}
	
	@RequestMapping("/goAddMessage")
	public ModelAndView goAddMessage() {
		PageData pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		mv.addObject("pd", pd);
		mv.setViewName("admin/openclass/add_details_message");
		return mv ;
	}
	
	@RequestMapping("/saveOpenClassMessage")
	public ModelAndView saveOpenClassMessage() {
		PageData pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		
		try {
			msg = openClassService.saveMessage(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "程序异常，请与相关工作人员联系";
			e.printStackTrace();
		} finally {
			mv.addObject("msg",msg);
			mv.setViewName("save_result");
		}
		
		return mv;
	}
	
	@RequestMapping("/delelteMessage")
	@ResponseBody
	public String delelteMessage() {
		PageData pd = this.getPageData();
		String msg = "";
		
		try {
			msg = openClassService.delelteMessage(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "程序异常，请与相关工作人员联系";
			e.printStackTrace();
		} 
		
		return JSON.toJSONString(msg);
	}
	
	@RequestMapping("/editMessage")
	@ResponseBody
	public String editMessage() {
		PageData pd = this.getPageData();
		String msg = "";
		
		try {
			msg = openClassService.editMessage(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "程序异常，请与相关工作人员联系";
			e.printStackTrace();
		} 
		
		return JSON.toJSONString(msg);
	}
	
	@RequestMapping("/listStudentM")
	public ModelAndView goStudentM(Page page) {
		PageData pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		page.setPd(pd);
		try {
			
			List<PageData> students = scoreService.listPdPageStudent(page);// 列出学生列表
			mv.addObject("students", students);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.setViewName("admin/openclass/manege_student");
			mv.addObject("pd", page.getPd());
		}
		
		return mv;
	}
	
	@RequestMapping("/deleteStudentByGradeId")
	@ResponseBody
	public String deleteStudentByGradeId() {
		PageData pd = this.getPageData();
		
		String msg = "";
		try {
			msg = scoreService.deleteStudentByGradeId(pd);// 移除学生
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "程序出现异常，移除操作失败";
			e.printStackTrace();
		}
		
		return JSON.toJSONString(msg);
	}
	
}
