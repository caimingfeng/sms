package com.sms.service.excel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sms.service.BaseService;
import com.sms.service.excel.UserExportService;
import com.sms.util.PageData;

/**
 * @author 蔡名锋:
 * @version 创建时间：2018年4月15日 下午6:19:02
 * 
 *          <pre>
* 类说明:
 *          </pre>
 */
@Service("userExportService")
public class UserExportServiceImpl extends BaseService implements UserExportService {

	/**
	 * @param PageData
	 * @return Map
	 * 
	 *         <pre>
	 * 说明：按条件导出用户信息
	 *         </pre>
	 */
	@Override
	public Map<String, Object> UserExport(PageData pd) {
		Map<String, Object> dataMap = new HashMap<>();
		/*
		 * 1.处理检索条件
		 */
		// 关键字
		String keyWord = pd.getString("keyWord");
		if (null != keyWord && !"".equals(keyWord.replace(" ", ""))) {
			// 关键字去空格
			keyWord = keyWord.replace(" ", "");
			pd.put("keyWord", keyWord);
		}
		// 登录时间
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

		// 数据有效性
		String exist = pd.getString("exist");
		log.info("删除标识为:" + exist);
		if (exist == null || !"0".equals(exist)) {
			pd.put("exist", "1");
		}

		/*
		 * 2.按条件查询出所有的用户
		 */
		List<PageData> userList = null;
		try {
			userList = findByMessage(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userList.isEmpty()) {
			log.info("查询不到数据");
			return null;
		}

		/*
		 * 3.设置Excel每行对应列的值
		 */
		List<PageData> varList = new ArrayList<PageData>();
		for (int i = 0; i < userList.size(); i++) {
			PageData vpd = new PageData();
			PageData pdTemp = userList.get(i);
			vpd.put("var1", pdTemp.get("userId").toString());
			vpd.put("var2", pdTemp.getString("account"));
			vpd.put("var3", pdTemp.getString("roleName") == null ? "" : pdTemp.getString("roleName"));
			vpd.put("var4", pdTemp.get("lastLogin") == null ? "" : pdTemp.get("lastLogin").toString());
			vpd.put("var5", pdTemp.getString("ip") == null ? "" : pdTemp.getString("ip").toString());
			vpd.put("var6", pdTemp.getString("bz") == null ? "" : pdTemp.getString("bz"));
			varList.add(vpd);
		}

		dataMap.put("varList", varList);

		/*
		 * 4.设置Excel每一列的列名
		 */

		List<String> titles = new ArrayList<String>();

		titles.add("编号"); // 1
		titles.add("账号"); // 2
		titles.add("用户身份"); // 3
		titles.add("最近登录"); // 4
		titles.add("上次登录IP"); // 5
		titles.add("备注"); // 6
		dataMap.put("titles", titles);

		/*
		 * 5.返回需要导出的数据
		 */
		return dataMap;
	}

	private List<PageData> findByMessage(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("UserMapper.findByMessage", pd);
	}

}
