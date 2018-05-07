package com.sms.controller.password;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sms.controller.BaseController;
import com.sms.service.user.impl.UserServiceImpl;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年5月6日 上午12:46:14
* <pre>
* 类说明:
* </pre>
*/
@Controller
public class PwController extends BaseController {

	/**
	 * <pre>说明</pre>
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/toEditPw")
	public ModelAndView toEditPw() {
		ModelAndView mv = this.getModelAndView();
		Subject user = SecurityUtils.getSubject();
		Session session = user.getSession();
		
		String account = (String) session.getAttribute(Const.SESSION_USERNAME);
		mv.addObject("account", account);
		mv.setViewName("admin/pwedit");
		return mv ;
	}
	
	@RequestMapping("/savePw")
	@ResponseBody
	public String  savePw() {
		PageData pd = this.getPageData();
		String msg = "";
		try {
			msg = userService.savePw(pd);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "程序异常，修改失败";
		}
		return JSON.toJSONString(msg);
	}

}
