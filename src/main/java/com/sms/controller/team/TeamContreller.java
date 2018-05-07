package com.sms.controller.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.entity.Major;
import com.sms.entity.Page;
import com.sms.service.majorteam.impl.MajorTeamServiceImpl;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月22日 下午8:27:18
* <pre>
* 类说明:
* </pre>
*/
@Controller
@RequestMapping("/team")
public class TeamContreller extends BaseController{
	/**
	 * <pre>说明</pre>
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MajorTeamServiceImpl majorTeamService;
	
	@RequestMapping(value = "/teamList")
	public ModelAndView toTeamList(Page page){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		page.setPd(pd);
		
		List<PageData> teamLsit = null;
		try {
			teamLsit = majorTeamService.listPageTeam(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("查询到班级条数：" + teamLsit.size());
		mv.addObject("teamList",teamLsit);
		mv.addObject("pd", page.getPd());
		mv.setViewName("team/team_list");
		return mv;
	}

	/**
	 * 去修改班级页面
	 */
	@RequestMapping(value = "/goEditT")
	public ModelAndView goEditT() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		//回填班级信息
		PageData team = majorTeamService.findByMajorClassId(pd);
		
		List<Major> majors = majorTeamService.findMajorAll();

		mv.setViewName("team/team_edit");
		mv.addObject("pd", pd);
		mv.addObject("team", team);
		mv.addObject("majors", majors);
		mv.addObject("msg", "editMajorTeam");

		return mv;
	}
	
	@RequestMapping(value = "editMajorTeam", method = RequestMethod.POST)
	@ResponseBody
	public String editMajorTeam() {
		PageData pd = this.getPageData();
		PageData newpd = new PageData();
		String msg = "";
		boolean b = majorTeamService.saveMajorTeam(pd);
		if (b) {
			msg = "success";
			newpd.put("result", msg);
		} else {
			msg = "服务器异常，保存失败";
			newpd.put("msg", msg);
		}
		return JSON.toJSONString(newpd);
	}
	
	@RequestMapping(value = "delMajorTeam", method = RequestMethod.POST)
	@ResponseBody
	public String delMajorTeam() {
		PageData pd = this.getPageData();
		PageData newpd = new PageData();
		String msg = "";
		boolean b;
		try {
			b = majorTeamService.deleteByMajorClassId(pd);
			if (b) {
				msg = "success";
				newpd.put("result", msg);
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "服务器异常，删除失败";
			newpd.put("msg", msg);
		} 
		return JSON.toJSONString(newpd);
	}
	
	/**
	 * 去新增班级页面
	 */
	@RequestMapping(value = "/goAddMajorTeam")
	public ModelAndView goAddMajorTeam() {
		ModelAndView mv = this.getModelAndView();
		
		List<Major> majors = majorTeamService.findMajorAll();
		mv.setViewName("team/team_add");
		mv.addObject("majors", majors);
		mv.addObject("msg", "saveMajorTeam");
		return mv;
	}
	
	/**
	 * 去新增班级页面
	 */
	@RequestMapping(value = "/saveMajorTeam")
	@ResponseBody
	public String saveMajorTeam() {
		PageData pd = this.getPageData();
		PageData newpd = new PageData();
		
		String msg = null;
		try {
			msg = majorTeamService.saveMajorTeamDo(pd);
			newpd.put("result", msg);
			newpd.put("msg", msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			newpd.put("msg", "服务器异常,新增失败");
		} 
		return JSON.toJSONString(newpd);
	}
	
	
	
}
