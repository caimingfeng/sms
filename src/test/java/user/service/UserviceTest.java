package user.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sms.entity.Page;
import com.sms.service.user.impl.UserServiceImpl;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月26日 下午12:42:50
* <pre>
* 类说明:
* </pre>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ApplicationContext.xml"})
public class UserviceTest {
	private static Logger logger = Logger.getLogger(UserviceTest.class);
	
	@Autowired
	private UserServiceImpl service;
	
	//@Test
	public void insert() throws Exception {
		PageData pd = new PageData();
		pd.put("userId", 0);
		pd.put("account", "test2");
		pd.put("password", "123456");
		pd.put("createdBy", "sys");
		pd.put("creationDate", new Date());
		
		service.insert(pd);
	}
	
	//@Test
	public void validateUserTest() throws Exception {
		PageData pageData = new PageData();
		pageData.put("account", "admin");
		pageData.put("password", "223ce7b851123353479d85757fbbf4e320d1e251");
		if (service.validateUser(pageData)) {
			System.out.println("success");
		} else {
			System.out.println("false");
		}
	}
	
	
	@Test
	public void listPdPageUserTest() throws Exception {
		Page page = new Page();
		List<PageData> listUser = service.listPdPageUser(page);
		for (PageData pageData : listUser) {
			logger.info(pageData.get("account"));
		}
	}
	
	
	public static void main (String[] arges) {
		String str = "1 2";
		String[] str2 = str.split(",");
		
		for (String string : str2) {
			System.out.println(string);
			System.out.println(str.replace(" ", ""));
		}
		
	}
	
}
