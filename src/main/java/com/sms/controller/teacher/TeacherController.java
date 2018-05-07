package com.sms.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.entity.Teacher;
import com.sms.service.teacher.impl.TeacherServiceImpl;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年5月5日 下午8:12:07
* <pre>
* 类说明:
* </pre>
*/
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

	/**
	 * <pre>说明:教师管理控制</pre>
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TeacherServiceImpl teacherService;
	
	@RequestMapping("/toinfor")
	public ModelAndView toInfor() {
		ModelAndView mv = this.getModelAndView();
		try {
			Teacher teacher = teacherService.findTeacherMessage();
			mv.addObject("teacher", teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("teacher/infor");
		return mv ;
	}
	
	@RequestMapping("/saveInfor")
	@ResponseBody
	public String saveInfor() {
		PageData pd = this.getPageData();
		String msg = "";
		try {
			msg = teacherService.saveMessage(pd);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "程序异常，修改失败";
		}
		return JSON.toJSONString(msg);
	}
}
