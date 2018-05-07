package com.sms.controller.admin.course;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sms.controller.BaseController;
import com.sms.entity.Course;
import com.sms.entity.Page;
import com.sms.service.course.impl.CourseServiceImpl;
import com.sms.service.score.impl.ScoreServiceImpl;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月26日 下午2:14:51
* <pre>
* 类说明:
* </pre>
*/
@Controller
@RequestMapping("/course")
public class CourseManageController extends BaseController {

	/**
	 * <pre>说明:课程管理控制器</pre>
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "courseService")
	private CourseServiceImpl courseService;
	@Resource(name = "scoreService")
	private ScoreServiceImpl scoreService;

	@RequestMapping(value = "/courseList")
	public ModelAndView toCourseList(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		//查询课程信息数据
		List<Course> courses = courseService.findAllByPage(page);
		
		mv.addObject("pd", pd);
		mv.addObject("courses", courses);
		mv.setViewName("course/course_list");
		return mv;
	}
	
	
	@RequestMapping(value = "/goAddCourse")
	public String toAddCourse() {
		return "course/course_add";
	}
	
	@RequestMapping(value = "/getAllCategory" ,produces = "text/html;charset=UTF-8;",method = RequestMethod.GET)
	@ResponseBody
	public String getAllCategory() {
		
		String allCategory = courseService.findAllCategory();
		
		return allCategory;
	}
	
	@RequestMapping(value = "/saveCourse" ,produces = "text/html;charset=UTF-8;",method = RequestMethod.POST)
	public ModelAndView saveCourse() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		try {
			String msg = courseService.saveCourse(pd);
			mv.addObject("msg", msg);
		} catch (Exception e) {
			mv.addObject("msg", "服务器异常，操作失败，请与相关工作人员反映");
			e.printStackTrace();
		} finally {
			mv.setViewName("save_result");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/goEditC")
	public ModelAndView toEditCourse() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Course course = courseService.findByCno(pd.getString("cno"));
		mv.addObject("course",course);
		mv.setViewName("course/course_edit");
		return mv;
	}
	
	@RequestMapping(value = "/editCourse" ,produces = "text/html;charset=UTF-8;",method = RequestMethod.POST)
	public ModelAndView editCourse() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg = "";
		try {
			msg = courseService.editCourse(pd);
		} catch (Exception e) {
			msg = "程序异常，操作失败，请与相关工作人员反映";
			e.printStackTrace();
		} finally {
			mv.addObject("msg", msg);
			mv.setViewName("save_result");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/delCourse" ,produces = "text/html;charset=UTF-8;",method = RequestMethod.POST)
	@ResponseBody
	public String delCourse() {
		PageData pd = this.getPageData();
		
		String data = null;
		try {
			data = courseService.delCourse(pd.getString("cno"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	@RequestMapping(value = "/editOpen" ,produces = "text/html;charset=UTF-8;",method = RequestMethod.POST)
	@ResponseBody
	public String editOpen() {
		PageData pd = this.getPageData();
		
		String msg = null;
		try {
			msg = courseService.editOpen(pd);
		} catch (Exception e) {
			msg = "程序异常";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping("/toTeacherCm")
	public ModelAndView toTeacherCm(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		try {
			List<PageData> classSchedule = scoreService.findCourseMessage(page);
			mv.addObject("list", classSchedule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.addObject("pd", pd);
			mv.setViewName("teacher/class_schedule");
		}
		
		return mv;
	}
	
	@RequestMapping("/toStudentCm")
	public ModelAndView toStudentCm(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		try {
			List<PageData> classSchedule = scoreService.findCourseMessage2(page);
			mv.addObject("list", classSchedule);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mv.addObject("pd", pd);
			mv.setViewName("student/class_schedule");
		}
		
		return mv;
	}
	
}
