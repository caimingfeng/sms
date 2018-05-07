package user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sms.entity.SysMenu;
import com.sms.service.menu.impl.MenuServiceImpl;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月26日 下午12:42:50
* <pre>
* 类说明:
* </pre>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ApplicationContext.xml"})
public class MenuServiceTest {
	private static Logger logger = Logger.getLogger(MenuServiceTest.class);
	
	@Autowired
	private MenuServiceImpl service;
	//@Test
	public void findByRoleIdTest() throws Exception {
		List<Integer> menuIds = new ArrayList<>();
		menuIds.add(1);
		menuIds.add(4);
		List<SysMenu> menu = service.findMenuById(menuIds);
		
		for (SysMenu sysMenu : menu) {
			logger.info(sysMenu.getMenuName());
			List<SysMenu> subMenu = sysMenu.getSubMenu();
			for (SysMenu sysMenu2 : subMenu) {
				logger.info(sysMenu2.getMenuName());
			}
			
		}
	}
	
	//@Test
	public void listMenuByParentIdTest() throws Exception {
		List<SysMenu>topMenuList = service.listMenuByParentId(0);  //顶层菜单列
		
		for (SysMenu sysMenu : topMenuList) {
			logger.info(sysMenu.getMenuName());
		}
	}
	
	public static void main(String[] args) {
		String s1 = "1,21,6";
		String s2 = "2";
		System.out.println(s1.indexOf(s2));
		String[] temp = s1.split(",");
		System.out.println(temp.length);
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
		}
	}
	
}
