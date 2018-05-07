package util;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import com.sms.entity.Team;
/**
* @author 蔡名锋:
* @version 创建时间：2018年3月20日 下午4:53:43
* <pre>
* 类说明:
* </pre>
*/
public class MybatisTest {
	private Team team;
	private InputStream in;
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	private Logger log;
	
	@Before
	public void init() {
		// TODO Auto-generated method stub
		log = Logger.getRootLogger();
		log.info("连接数据库前读取mybatis配置文件...");
		try {
			in = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		session = sqlSessionFactory.openSession();
		
	}
	
	//@Test
	/*public void ClassMapper() {
		log.info("开始测试班级模型...");
		TeamMapper teamMapper = session.getMapper(TeamMapper.class);
		
		String grade = "14级";
		String classNo = "4班";
		String createdBy = "sys";
		Date creationDate = new Date();
		team = new Team(0, grade, classNo, createdBy, creationDate);
		
		teamMapper.insertSelective(team);
		session.commit();
		log.info("插入成功...");
	}*/
	
	@After
	public void close() {
		session.close();
		log.info("已关闭数据库连接...");
	}
}
