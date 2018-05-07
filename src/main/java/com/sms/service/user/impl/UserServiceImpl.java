package com.sms.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sms.entity.FlowUser;
import com.sms.entity.Page;
import com.sms.entity.User;
import com.sms.service.BaseService;
import com.sms.service.user.UserService;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月26日 下午12:24:53
* <pre>
* 类说明:
* </pre>
*/
@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {
	@Override
	public boolean insert(User user) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)dao.insert("UserMapper.insertSelective", user) ==1;
	}

	@Override
	public boolean deleteByAccount(String account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByAccount(String account) throws Exception {
		// TODO Auto-generated method stub
		return (User) dao.findForObject("UserMapper.findByAccount", account);
	}

	@Override
	public boolean edit(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateUser(PageData pageData) throws Exception {
		return (Integer)dao.findForObject("UserMapper.validateUser", pageData) == 1;
		
	}

	/**
	 * 
	 * <pre>说明:判断用户的有效性</pre>
	 */
	public boolean isExist(String account) throws Exception {
		return (Integer)dao.findForObject("UserMapper.isExist", account) == 1;
	}
	
	public void insert(PageData pd) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 
	 * <pre>说明:更新用户登录信息</pre>
	 */
	public boolean updateLoginInfo(PageData pageData) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)dao.update("UserMapper.updateLastLoginInfo", pageData) == 1;
	}
	
	/**
	 * 
	 * <pre>说明:更新用户流水表</pre>
	 */
	public boolean insertFlowUser(FlowUser flowUser) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)dao.insert("UserMapper.insertFlowUser", flowUser) == 1;
	}
	
	/**
	 * 
	 * <pre>说明:登录时判断角色是否一致</pre>
	 */
	public boolean judeRole(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)dao.findForObject("UserMapper.judgRole", pd) == 1;
	}
	
	/**
	 * 
	 * <pre>说明:根据userId或account查询用户</pre>
	 */
	public PageData findByUId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (PageData) dao.findForObject("UserMapper.findByUId", pd);
	}

	/**
	 * @param Page
	 * @return List<PageData>
	 * <pre>说明:用户列表，分页查询用户</pre>
	 */
	public List<PageData> listPdPageUser(Page page)throws Exception{
		
		/**
		 * 1.处理检索条件
		 */
		PageData pd = page.getPd();
		log.info("关键字" + pd.getString("keyWord"));
		String keyWord = pd.getString("keyWord");
		if (null != keyWord && !"".equals(keyWord)) {
			// 关键字去空格
			keyWord = keyWord.trim();
			pd.put("keyWord", keyWord);
		}

		String lastLoginStart = pd.getString("lastLoginStart");
		String lastLoginEnd = pd.getString("lastLoginEnd");

		if (lastLoginStart != null && !"".equals(lastLoginStart)) {
			lastLoginStart = lastLoginStart + " 00:00:00";
			pd.put("lastLoginStart", lastLoginStart);
		}
		if (lastLoginEnd != null && !"".equals(lastLoginEnd)) {
			lastLoginEnd = lastLoginEnd + " 23:59:59";
			pd.put("lastLoginEnd", lastLoginEnd);
		}

		String exist = pd.getString("exist");
		if ("0".equals(exist) || "1".equals(exist)) {
			pd.put("exist", exist);
		} else {
			pd.put("exist", null);
		}

		page.setPd(pd);
		log.info("开始分页查询用户");
		
		/**
		 * 2.按条件检索用户并返回数据
		 */
		return (List<PageData>) dao.findForList("UserMapper.userlistPage", page);
	}

	/**
	 * 
	 * <pre>说明:根据userId和account查询用户</pre>
	 */
	public boolean findByUIdAndAccount(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)dao.findForObject("UserMapper.findByUIdAndAccount", pd) == 0;
	}

	/**
	 * 
	 * <pre>说明:修改用户信息，此方法仅限修改用户权限和密码或用户名</pre>
	 */
	public boolean editU(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.update("UserMapper.editU", pd) == 1;
	}

	/**
	 * 
	 * <pre>说明:删除一个用户信息</pre>
	 */
	public boolean deleteU(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.delete("UserMapper.deleteU", pd) == 1;
	}

	/**
	 * 
	 * <pre>说明:增加一个用户信息，但此用户没有学生信息或教师信息</pre>
	 */
	public boolean saveU(PageData pd) throws Exception {
		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		User user = (User) session.getAttribute(Const.NEW_USER);

		String passWord = new SimpleHash("SHA-1", pd.get("account"), pd.get("password")).toString();
		pd.put("password", passWord);
		pd.put("userId", "");
		pd.put("status", 0);
		pd.put("createdBy", user.getAccount());
		pd.put("creationDate", new Date());
		pd.put("exist", "1");
		pd.put("skin", "default");
		
		if (null == findByUId(pd)) {
			dao.insert("UserMapper.insertSelective", pd);
			return true;
		} 
		
		return false;
	}
	
	/**
	 * 
	 * <pre>说明:根据用户id批量删除用户</pre>
	 */
	public String deleteAllU(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<>();
		
		List<PageData> pdList = new ArrayList<PageData>();
		String userIds = pd.getString("userIds");

		if (null != userIds && !"".equals(userIds)) {
			String arrayUserId[] = userIds.split(",");
			log.info("开始批量删除");
			dao.delete("UserMapper.deleteAllU", arrayUserId);
			map.put("result", "success");
		} else {
			map.put("result", "failed");
		}
		log.info("批量删除操作成功");
		pd.put("exist", null);
		
		pdList.add(pd);
		map.put("list", pdList);
		
		return JSON.toJSONString(map);
		
	}

	/**
	 * 
	 * <pre>说明:查询用户的其他信息</pre>
	 */
	public List<PageData> findByMessage(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("UserMapper.findByMessage", pd);
	}

	public String savePw(PageData pd) throws Exception {
		String password = new SimpleHash("SHA-1", pd.getString("account"), pd.getString("oldpw")).toString();
		pd.put("password", password);
		Integer count = (Integer) dao.findForObject("UserMapper.validateUser", pd);
		if (count == 1) {
			password = new SimpleHash("SHA-1", pd.getString("account"), pd.getString("newpw")).toString();
			pd.put("password", password);
			dao.update("UserMapper.updatePw", pd);
			return "success";
		}
		return "密码不正确，修改失败";
	}

}
