package com.sms.controller.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sms.controller.BaseController;
import com.sms.util.Const;
import com.sms.util.PageData;
import com.sms.util.Tools;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月1日 上午09:50:53
* <pre>
* 类说明:
* </pre>
*/

@Controller
public class LoginController extends BaseController {

	
	/**
	 * <pre>说明</pre>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value="/tab")
	public String tab(){
		System.out.println("加载子页面...");
		return "admin/tab";
	}
	
	/**
	 * 进入首页后的默认页面
	 * @return
	 */
	@RequestMapping(value="/login_default")
	public String defaultPage(){
		return "admin/default";
	}
	
	/**
	 * 退出系统
	 * 
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		logger.info("退出系统...");
		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		session.removeAttribute(Const.SESSION_SECURITY_CODE);
		session.removeAttribute(Const.NEW_USER);
		session.removeAttribute(Const.SESSION_menuList);
		session.removeAttribute("role");
		session.removeAttribute(Const.SESSION_USERNAME);
		
		currentUser.logout();
		
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String msg = pd.getString("msg");
		logger.info("msg：" + msg);
		
		pd.put("msg", msg);
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		
		mv.setViewName("login");
		mv.addObject("pd",pd);
		
		return mv;
	}
	
}
