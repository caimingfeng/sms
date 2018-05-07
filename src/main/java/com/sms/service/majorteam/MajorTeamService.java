package com.sms.service.majorteam;

import com.sms.entity.Team;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年4月22日 下午8:30:45
* <pre>
* 类说明:
* </pre>
*/
public interface MajorTeamService {
	Object findByMajorClassId(PageData pd);
	boolean insert( Team team);
	boolean deleteByMajorClassId(PageData pd) throws Exception;
	boolean update(Team team);
}
