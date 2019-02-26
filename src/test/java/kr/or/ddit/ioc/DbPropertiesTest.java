package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-placeholder.xml")
public class DbPropertiesTest {
	
	private Logger logger = LoggerFactory.getLogger(DbPropertiesTest.class);
	
	@Resource(name="dbProperties")
	private DbProperties dbProperties;
	
	@Test
	public void testPlaceholder() {
		/***Given***/
		
		/***When***/
		String url = dbProperties.getUrl();
		String username = dbProperties.getUsername();
		String password = dbProperties.getPassword();
		String driver = dbProperties.getDriverClassName();
		logger.debug("url : {}", url);
		logger.debug("username : {}", username);
		logger.debug("password : {}", password);
		logger.debug("driver : {}", driver);
		
		/***Then***/
		assertNotNull(dbProperties);
		assertEquals("pc_11", username);
		
	}

}