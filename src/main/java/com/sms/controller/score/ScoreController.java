package com.sms.controller.score;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.entity.CourseMessage;
import com.sms.entity.Page;
import com.sms.entity.Score;
import com.sms.service.score.impl.ScoreServiceImpl;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年5月4日 上午12:43:49
* <pre>
* 类说明:
* </pre>
*/
@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController {

	/**
	 * <pre>说明</pre>
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "scoreService")
	private ScoreServiceImpl scoreService;
	@RequestMapping("/addAllS")
	@ResponseBody
	public String addAllS() {
		PageData pd = this.getPageData();
		
		String msg = "";
		try {
			msg = scoreService.addAllS(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "程序出现异常，增加学生到教学班失败";
			e.printStackTrace();
		}
		
		return JSON.toJSONString(msg);
	}
	
	@RequestMapping("/toScoreInput")
	public ModelAndView toScoreInput(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		try {
			List<CourseMessage> teachingClasses = scoreService.findTeachingClass(page);
			mv.addObject("list", teachingClasses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.addObject("pd", pd);
			mv.setViewName("teacher/score_input");
		}
		
		return mv;
	}
	
	@RequestMapping("/goEditScore")
	public ModelAndView goEditScore() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("teacher/score_input_setup");
		return mv;
	}
	
	@RequestMapping("/setUpScore")
	@ResponseBody
	public String setUpScore() {
		//ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg = null;
		try {
			scoreService.editScore(pd);
			msg = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*mv.addObject("pd", pd);
		mv.setViewName("forward:toScoreInputdo");*/
		
		//return mv;
		return JSON.toJSONString(msg);
	}
	
	@RequestMapping("/toScoreInputdo")
	public ModelAndView toScoreInputdo(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		//根据courseMessageId查询学生
		List<PageData> list;
		try {
			list = scoreService.findByCoursesMessageId(page);
			for (PageData newpd : list) {
				if(newpd.get("scoreType")!=null){
					mv.addObject("scoreType", newpd.get("scoreType"));
					break;
				}
			}
			mv.addObject("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.addObject("pd", pd);
			mv.setViewName("teacher/score_input_do");
		}
		
		return mv;
	}
	
	@RequestMapping("/saveScore")
	@ResponseBody
	public String saveScore() {
		//ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg = null;
		try {
			scoreService.saveScore(pd);
			msg = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON.toJSONString(msg);
	}
	
	@RequestMapping("/saveFinal")
	@ResponseBody
	public String saveFinal() {
		PageData pd = this.getPageData();
		String msg = null;
		try {
			scoreService.saveFinal(pd);
			msg = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "程序异常，提交失败";
			e.printStackTrace();
		}
		return JSON.toJSONString(msg);
	}
	
	@RequestMapping("/toScoreList")
	public ModelAndView toScoreList(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		try {
			List<CourseMessage> teachingClasses = scoreService.findTeachingClass2(page);
			mv.addObject("list", teachingClasses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.addObject("pd", pd);
			mv.setViewName("teacher/score_list");
		}
		
		return mv;
	}
	
	@RequestMapping("/toScoreQc")
	public ModelAndView toScoreQc(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		//根据courseMessageId查询学生
		List<PageData> list;
		try {
			list = scoreService.findByCoursesMessageId(page);
			for (PageData newpd : list) {
				if(newpd.get("scoreType")!=null){
					mv.addObject("scoreType", newpd.get("scoreType"));
					break;
				}
			}
			mv.addObject("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.addObject("pd", pd);
			mv.setViewName("teacher/score_qc");
		}
		
		return mv;
	}
	
	@RequestMapping("/toStudentScore")
	public ModelAndView toStudentScore(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list;
		try {
			list = scoreService.findBySno(page);
			mv.addObject("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mv.addObject("pd", pd);
			mv.setViewName("student/score_qc");
		}
		
		return mv;
	}
	@RequestMapping("/toScoreDetail")
	public ModelAndView toScoreDetail() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Score score;
		try {
			score = scoreService.findByGradeId(pd);
			mv.addObject("score", score);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mv.setViewName("student/score_detail");
		}
		return mv;
	}
	@RequestMapping("/toCalculationGpa")
	public ModelAndView toCalculationGpa() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("student/calculation_gpa");
		return mv;
	}
	@RequestMapping("/calculationGpa")
	@ResponseBody
	public String calculationGpa() {
		Map<String, Object> data = null;
		PageData pd = this.getPageData();
		try {
			data = scoreService.calculationGpa(pd);
		} catch (Exception e) {
			data.put("msg", "程序错误，查询失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(data);
	}
	
}
