package com.sms.controller.student;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.entity.Page;
import com.sms.service.student.impl.StudentServiceImpl;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年5月4日 上午12:05:59
* <pre>
* 类说明:
* </pre>
*/
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

	/**
	 * <pre>说明:学生管理控制</pre>
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "studentService")
	private StudentServiceImpl studentService;
	
	@RequestMapping("/studentList")
	public ModelAndView goStudentM(Page page) {
		PageData pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		page.setPd(pd);
		try {
			
			List<PageData> students = studentService.listPdPageStudent(page);// 列出学生列表
			mv.addObject("students", students);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.setViewName("admin/student/list_student");
			mv.addObject("pd", page.getPd());
		}
		
		return mv;
	}

	@RequestMapping("/toinfor")
	public ModelAndView toInfor() {
		ModelAndView mv = this.getModelAndView();
		try {
			PageData student = studentService.findStudentMessage();
			mv.addObject("student", student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("student/infor");
		return mv ;
	}
	
	@RequestMapping("/saveInfor")
	@ResponseBody
	public String saveInfor() {
		PageData pd = this.getPageData();
		String msg = "";
		try {
			msg = studentService.saveMessage(pd);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "程序异常，修改失败";
		}
		return JSON.toJSONString(msg);
	}
}
