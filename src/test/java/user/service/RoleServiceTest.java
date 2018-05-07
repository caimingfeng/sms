package user.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sms.entity.SysRole;
import com.sms.service.role.impl.RoleServiceImpl;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月26日 下午12:42:50
* <pre>
* 类说明:
* </pre>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ApplicationContext.xml"})
public class RoleServiceTest {
	private static Logger logger = Logger.getLogger(RoleServiceTest.class);
	
	@Autowired
	private RoleServiceImpl service;
	
	//@Test
	public void findByRoleIdTest() throws Exception {
		Integer roleId = new Integer(1);
		logger.info("开始......");
		SysRole role = service.findByRoleId(roleId);
		logger.info("角色名字"+":"+role.getRoleName());
	}
	
	//@Test
	public void findAllRoleTest() throws Exception {
		logger.info("开始......");
		List<SysRole> role = service.findAllRole();
		
		for (SysRole sysRole : role) {
			logger.info("角色名字"+":"+sysRole.getRoleName());
		}
	}
}
