package com.sms.controller.head;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.controller.BaseController;
import com.sms.entity.Student;
import com.sms.entity.Teacher;
import com.sms.entity.User;
import com.sms.service.student.impl.StudentServiceImpl;
import com.sms.service.teacher.impl.TeacherServiceImpl;
import com.sms.service.user.impl.UserServiceImpl;
import com.sms.util.AppUtil;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
 * @author 蔡名锋:
 * @version 创建时间：2018年4月3日 下午7:57:08
 * 
 *          <pre>
* 类说明:
 *          </pre>
 */
@Controller
@RequestMapping(value = "/head")
public class HeadController extends BaseController {

	/**
	 * <pre>
	 * 说明
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private StudentServiceImpl studentService;
	@Autowired
	private TeacherServiceImpl teacherService;
	@Autowired
	private UserServiceImpl userService;
	private User nowUser;

	/**
	 * 获取头部信息
	 */
	@RequestMapping(value = "/getUname")
	@ResponseBody
	public Object getList() {
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();

			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			PageData pds = (PageData) session.getAttribute(Const.SESSION_userpds);
			nowUser = (User) session.getAttribute(Const.NEW_USER);

			if (null == pds) {
				String account = session.getAttribute(Const.SESSION_USERNAME).toString(); // 获取当前登录者loginname
				if (nowUser == null) {
					return "login";
				} else {
					
					logger.info("获取登录者的信息...");
					pd.put("account", account);
					pds = userService.findByUId(pd);
					
					int roleId = nowUser.getRoleId();

					// 根据角色id判断应查询的数据
					switch (roleId) {
					case 1:
						logger.info("管理员登录昵称设置为admin");
						pds.put("USERNAME", "admin");
						break;
					case 2:
						logger.info("教师登录查询其名称");
						Teacher teacher = teacherService.findTeaNameByTno(account);
						pds.put("USERNAME", teacher.getTeaName() + " 老师");
						break;
					case 3:
						logger.info("学生登录查询其名称");
						Student student = studentService.findStuNameBySno(account);
						pds.put("USERNAME", student.getStuName() + " 学生");
						break;

					default:
						pds.put("USERNAME", pds.getString("account"));
						break;
					}
				}
				session.setAttribute(Const.SESSION_userpds, pds);
			}

			pdList.add(pds);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}

}
