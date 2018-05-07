package com.sms.service.majorteam.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.sms.entity.Major;
import com.sms.entity.Page;
import com.sms.entity.Team;
import com.sms.service.BaseService;
import com.sms.service.majorteam.MajorTeamService;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
 * @author 蔡名锋:
 * @version 创建时间：2018年4月22日 下午8:31:47
 * 
 *          <pre>
* 类说明:
 *          </pre>
 */
@Service("majorTeamService")
public class MajorTeamServiceImpl extends BaseService implements MajorTeamService {

	@Override
	public PageData findByMajorClassId(PageData pd) {
		// TODO Auto-generated method stub

		try {
			return (PageData) dao.findForObject("MajorTeamMapper.findByMajorClassId", pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		return null;
	}

	@Override
	public boolean insert(Team team) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByMajorClassId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.delete("MajorTeamMapper.deleteByMajorClassId", pd) == 1;
	}

	@Override
	public boolean update(Team team) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<PageData> listPageTeam(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("MajorTeamMapper.listPageTeam", page);
	}

	/**
	 * @return List<Major>
	 * 
	 *         <pre>
	 * 说明:查询所有专业
	 *         </pre>
	 */
	public List<Major> findMajorAll() {
		// TODO Auto-generated method stub
		try {
			return (List<Major>) dao.findForList("MajorMapper.findAll", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <pre>
	 * 说明:保存班级信息
	 * </pre>
	 */
	public boolean saveMajorTeam(PageData pd) {

		try {
			return (Integer) dao.update("MajorTeamMapper.saveMajorTeam", pd) == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String saveMajorTeamDo(PageData pd) throws Exception {
		
		String msg;
		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		pd.put("createdBy", session.getAttribute(Const.SESSION_USERNAME));
		pd.put("creationDate", new Date());

		Integer classId = valiteTeam(pd);
		if (classId == null) {
			dao.insert("TeamMapper.insertSelective", pd);
			classId = valiteTeam(pd);
		}
		pd.put("classId", classId);
		Integer majorClassId = (Integer) dao.findForObject("MajorTeamMapper.validateByClassIdAndMajorId", pd);
		if (majorClassId == null) {
			dao.insert("MajorTeamMapper.insertSelective", pd);
			msg = "success";
		} else {
			msg = "班级已存在";
		}

		return msg;

	}

	private Integer valiteTeam(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.findForObject("TeamMapper.validateTeam", pd);
	}

}
