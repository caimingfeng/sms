package com.sms.service.excel.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sms.dao.impl.DaoSupport;
import com.sms.entity.MajorTeam;
import com.sms.entity.SysRole;
import com.sms.service.BaseService;
import com.sms.service.excel.UserImportService;
import com.sms.util.Const;
import com.sms.util.DateUtil;
import com.sms.util.FileDownload;
import com.sms.util.FileUpload;
import com.sms.util.ObjectExcelRead;
import com.sms.util.PageData;
import com.sms.util.PathUtil;
import com.sms.util.exception.SmsException;

/**
 * @author 蔡名锋:
 * @version 创建时间：2018年4月15日 下午2:50:46
 * 
 *          <pre>
* 类说明:
 *          </pre>
 */
@Service("userImportService")
public class UserImportServiceImpl extends BaseService implements UserImportService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * @param MultipartFile
	 * @return boolean
	 * 
	 *         <pre>
	 * 说明:执行导入用户业务
	 *         </pre>
	 * 
	 *         注意:导入操作未完成
	 */
	@Override
	public boolean UserImport(MultipartFile file, PageData pd) {

		boolean flag = false;
		List<PageData> listPd = null;

		try {
			/*
			 * 1.从Excel获取数据保存到list集合
			 */
			listPd = readExcel(file);
		} catch (Exception e) {
			throw new SmsException("服务器异常,读取Excel表失败", e);
		}

		// 判断是否获取到Excel表数据
		if (listPd.isEmpty()) {
			log.info("没有从Excel读出数据");
			return flag;
		}

		/*
		 * 2. 判断是导入学生还是教师
		 */
		// 获取用户类型
		String userType = listPd.get(0).getString("var2");

		try {
			// 查询所有角色(不包含admin)
			List<SysRole> roleList = listAllERRoles();
			if (!roleList.isEmpty()) {
				// 设置用户权限
				for (SysRole sysRole : roleList) {
					if (userType.equals(sysRole.getRoleName())) {
						pd.put("roleId", sysRole.getRoleId());
					}
				}
			}
		} catch (SQLException e) {
			throw new SmsException("查询角色时，数据库异常", e);
		} catch (Exception e) {
			throw new SmsException("其他异常", e);
		}

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		// 设置用户公共信息
		pd.put("createdBy", session.getAttribute(Const.SESSION_USERNAME));
		pd.put("creationDate", new Date());
		pd.put("exist", "1");// 激活
		pd.put("skin", "default");

		/*
		 * 3.执行导入操作
		 */
		switch ((Integer) pd.get("roleId")) {
		case 2:
			// 执行导入教师业务逻辑
			try {
				flag = teacherImport(pd, listPd);
			} catch (SQLException e) {
				throw new SmsException("导入教师时，数据库异常", e);
			} catch (Exception e) {
				throw new SmsException("其他异常", e);
			}
			
			break;
		case 3:
			// 执行导入学生业务逻辑
			try {
				flag = studentImport(pd, listPd);
			} catch (SQLException e) {
				throw new SmsException("导入学生时，数据库异常", e);
			} catch (Exception e) {
				throw new SmsException("其他异常", e);
			}
			break;
		default:
			break;
		}

		/*
		 * 4.返回
		 */
		return flag;

	}

	/**
	 * @param MultipartFile
	 * @return Map
	 * 
	 *         <pre>
	 * 说明:从Excel读出数据
	 *         </pre>
	 */
	@SuppressWarnings("rawtypes")
	private List<PageData> readExcel(MultipartFile file) {

		List<PageData> listPd = null;

		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE; // 文件上传路径
		String fileName = FileUpload.fileUp(file, filePath, "userexcel" + System.currentTimeMillis()); // 执行上传
		log.info("上传的文件名：" + fileName);

		listPd = (List) ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0); // 执行读EXCEL操作,读出的数据导入List
																				// 2:从第3行开始；0:从第A列开始；0:第0个sheet
		return listPd;
	}

	/**
	 * 
	 * @param PageData,List<PageData>
	 * @return boolean
	 * 
	 *         <pre>
	 * 说明:保存学生信息导数据库，并使用学号帮学生注册成系统用户
	 *         </pre>
	 */
	private boolean studentImport(PageData pd, List<PageData> listPd) throws Exception {
		// TODO Auto-generated method stub

		boolean flag = false;
		// 保存回馈异常的信息
		StringBuffer bufftMsg = new StringBuffer();
		// 用于保存已通过验证的专业名(即在数据库存在这些专业)
		List<String> majorNames = new ArrayList<>();
		//保存查询过的专业信息
		Map<String, Integer> majorMsg = new HashMap<>();
		/**
		 * var0 :序号 var1 :学号 var2 :用户类型 var3 :姓名 var4 :性别 var5 :学院 var6 :专业 var7
		 * :年级 var8 :班级 var9 :手机 var10 :身份证 var11 :出生日期 var12 :地址 var13 :备注
		 */
		log.info("数据的条数：" + listPd.size());

		for (PageData pageData : listPd) {
			String num = pageData.getString("var0").replace(" ", "");
			String sno = pageData.getString("var1").replace(" ", "");
			String majorName = pageData.getString("var6").replace(" ", "");
			String grade = pageData.getString("var7").replace(" ", "");
			String classNumber = pageData.getString("var8").replace(" ", "");
			
			if ("".equals(num) || "".equals(sno) || "".equals(majorName) || "".equals(grade) || "".equals(classNumber)) {
				bufftMsg.delete(0, bufftMsg.length());
				bufftMsg.append("Excel表异常：序号、学号、专业、年级、班级不能为空!\\n");
				break;
			}

			StringBuffer strBuffer = new StringBuffer();
			// 判断学号是否重复(判断用户表和学生表)
			if (!validateAccountUq(sno) || !validateSnoUq(sno)) {
				bufftMsg.append("\\t序号 " + num + " 异常：学号或账号已在数据库中存在;\\n");
				continue;
			}
			log.info("账号：" + sno + "通过唯一性验证");

			// 判断专业是否存在
			// log.info("保存专业名的数组的长度为：" + majorNames.size());
			int count = 0;
			int length = majorNames.size();
			for (int i = length - 1; i >= 0; i--) {
				if (majorName.equals(majorNames.get(i))) {
					break;
				}
				count++;
			}
			if (count == length) {
				// 验证专业名
				
				Integer majorId = validateMajor(majorName);
				
				if (majorId != null && majorId != 0) {
					majorNames.add(majorName);
					majorMsg.put(majorName, majorId);
				} else {
					bufftMsg.append("\\t序号 " + num + " 异常：数据库没有此专业信息;\\n");
					continue;
				}

			}
			
			// 继续验证该专业下的班级的存在性
			PageData newPd = new PageData();
			newPd.put("majorId", majorMsg.get(majorName));
			newPd.put("classNumber", classNumber);
			newPd.put("grade", grade);
			MajorTeam mTeam = validateMajorTeam(newPd);
			
			if (mTeam != null) {
				pd.put("majorClassId", mTeam.getMajorClassId());
			} else {
				strBuffer.append("该专业下的班级信息有误;");
				bufftMsg.append("\\t序号 " + num + " 异常：该专业下的班级信息有误;\\n");
			}
		}

		if (bufftMsg.length() > 0) {
			pd.put("message", bufftMsg.toString());
			return false;
		}

		log.info("开始执行插入数据库操作...");
		flag = saveStudent(pd, listPd);
		if (flag) {
			return true;
		}

		return false;
	}

	private boolean saveStudent(PageData pd, List<PageData> listPd){
		
		for (PageData pageData : listPd) {
			String num = pageData.getString("var0").replace(" ", "");
			String sno = pageData.getString("var1").replace(" ", "");
			String stuName = pageData.getString("var3").replace(" ", "");
			String sex = pageData.getString("var4").replace(" ", "");
			String department = pageData.getString("var5").replace(" ", "");
			String majorName = pageData.getString("var6").replace(" ", "");
			String grade = pageData.getString("var7").replace(" ", "");
			String classNumber = pageData.getString("var8").replace(" ", "");
			String phone = pageData.getString("var9").replace(" ", "");
			String idCard = pageData.getString("var10").replace(" ", "");
			String birth = pageData.getString("var11").replace(" ", "");
			String address = pageData.getString("var12").replace(" ", "");
			String bz = pageData.getString("var13").replace(" ", "");
			
			//账号和学号
			pd.put("account", sno);
			pd.put("sno", sno);
			// 姓名
			pd.put("stuName", stuName);
			// 性别
			pd.put("sex", sex);
			// 学院
			pd.put("department", department);
			// 专业名
			pd.put("majorName", majorName);
			// 年级
			pd.put("grade", grade);
			// 班级
			pd.put("classNumber", classNumber);
			// 手机
			pd.put("phone", phone);
			// 身份证
			pd.put("idCard", idCard);
			// 出生日期
			pd.put("birth", DateUtil.fomatDate2(birth));
			// 住址
			pd.put("address", address);
			// 备注
			pd.put("bz",bz);
			pd.put("password", new SimpleHash("SHA-1", sno, "123456").toString()); // 默认密码123
			
			try {
				dao.insert("StudentMapper.insertSelective", pd); //保存学生信息到数据库
				dao.insert("UserMapper.insertSelective", pd); //注册学生为系统用户
			} catch (SQLException e) {
				throw new SmsException("执行序号 "+ num + "时出现数据库异常", e);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private MajorTeam validateMajorTeam(PageData newPd) throws Exception {
		// TODO Auto-generated method stub
		return (MajorTeam)dao.findForObject("MajorTeamMapper.validateMajorTeam", newPd);
	}

	/**
	 * @param String
	 * 
	 *            <pre>
	 * 说明:验证专业的存在性
	 *            </pre>
	 */
	private Integer validateMajor(String majorName) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.findForObject("MajorMapper.validateMajor", majorName);
	}

	/**
	 * @param PageData,List<PageData>
	 * @return boolean
	 * 
	 *         <pre>
	 * 说明:保存教师信息导数据库，并使用教工号号帮教师注册成系统用户
	 *         </pre>
	 */
	private boolean teacherImport(PageData pd, List<PageData> listPd) throws Exception{
		// TODO Auto-generated method stub
		boolean flag = false;
		// 保存回馈异常的信息
		StringBuffer bufftMsg = new StringBuffer();
		
		/**
		 * var0 :序号 var1 :工号 var2 :用户类型 var3 :姓名 var4 :性别 var5 :职称 var6 :学位 var7
		 * :手机 var8 :身份证 var9 :地址 var10 :备注 
		 */
		log.info("数据的条数：" + listPd.size());
		
		/**
		 * 1.数据校验（如账号唯一性等）
		 */
		for (PageData pageData : listPd) {
			String num = pageData.getString("var0").replace(" ", "");
			String tno = pageData.getString("var1").replace(" ", "");
			
			
			if (!validateAccountUq(tno) && !validateTnoUq(tno)) {
				bufftMsg.append("\\t序号 " + num + " 异常：工号或账号已在数据库中存在;\\n");
				continue;
			}
			log.info("账号：" + tno + "通过唯一性验证");
		}
		
		if (bufftMsg.length() > 0) {
			pd.put("message", bufftMsg.toString());
			return false;
		}
		
		log.info("开始执行插入数据库操作...");
		flag = saveTeacher(pd, listPd);
		if (flag) {
			return true;
		}
		return false;
	}

	private boolean saveTeacher(PageData pd, List<PageData> listPd) {
		for (PageData pageData : listPd) {
			String num = pageData.getString("var0").replace(" ", "");
			String tno = pageData.getString("var1").replace(" ", "");
			String teaName = pageData.getString("var3").replace(" ", "");
			String sex = pageData.getString("var4").replace(" ", "");
			String professional = pageData.getString("var5").replace(" ", "");
			String degree = pageData.getString("var6").replace(" ", "");
			String phone = pageData.getString("var7").replace(" ", "");
			String idCard = pageData.getString("var8").replace(" ", "");
			String address = pageData.getString("var9").replace(" ", "");
			String bz = pageData.getString("var10").replace(" ", "");
			
			//账号和工号
			pd.put("account", tno);
			pd.put("tno", tno);
			// 姓名
			pd.put("teaName", teaName);
			// 性别
			pd.put("sex", sex);
			// 职称
			pd.put("professional", professional);
			// 学位
			pd.put("degree", degree);
			// 手机
			pd.put("phone", phone);
			// 身份证
			pd.put("idCard", idCard);
			// 地址
			pd.put("address", address);
			// 备注
			pd.put("bz", bz);
			
			pd.put("password", new SimpleHash("SHA-1", tno, "123456").toString()); // 默认密码123
			
			try {
				dao.insert("TeacherMapper.insertSelective", pd); //保存教师信息到数据库
				dao.insert("UserMapper.insertSelective", pd); //注册教师为系统用户
			} catch (SQLException e) {
				throw new SmsException("执行序号 "+ num + "时出现数据库异常", e);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private boolean validateTnoUq(String tno) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)dao.findForObject("TeacherMapper.validateTnoUq", tno) == 0;
	}

	/**
	 * 
	 * <pre>
	 * 说明:验证用户账号的唯一性
	 * </pre>
	 */
	private boolean validateAccountUq(String account) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.findForObject("UserMapper.validateAccountUq", account) == 0;
	}

	/**
	 * 
	 * <pre>
	 * 说明:验证学生学号的唯一性
	 * </pre>
	 */
	private boolean validateSnoUq(String sno) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.findForObject("StudentMapper.validateSnoUq", sno) == 0;
	}

	private List<SysRole> listAllERRoles() throws Exception {
		// TODO Auto-generated method stub
		return (List<SysRole>) dao.findForList("SysRoleMapper.listAllERRoles", null);
	}

	/**
	 * @param HttpServletResponse,PageData
	 * 
	 *            <pre>
	 * 说明:下载Excel模板业务
	 *            </pre>
	 */
	public void downExcel(HttpServletResponse response, PageData pd) throws Exception {
		String status = pd.getString("status");

		if ("stu".equals(status)) {
			FileDownload.fileDownload(response,
					PathUtil.getClasspath() + Const.FILEPATHFILE + "download/" + "StudentModel.xls",
					"StudentModel.xls");
		} else if ("tea".equals(status)) {
			FileDownload.fileDownload(response,
					PathUtil.getClasspath() + Const.FILEPATHFILE + "download/" + "TeacherModel.xls",
					"TeacherModel.xls");
		} else {
			return;
		}
	}

}
