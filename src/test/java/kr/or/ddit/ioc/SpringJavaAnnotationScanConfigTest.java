package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {SpringJavaAnnotationScanConfig.class})
public class SpringJavaAnnotationScanConfigTest {
	private Logger logger = LoggerFactory.getLogger(SpringJavaAnnotationScanConfigTest.class);
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao;
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@Test
	public void testJavaAnnotationScan() {
		/***Given***/
		
		/***When***/
		logger.debug("rangers : {}", rangerService.getRangers());
		
		/***Then***/
		assertNotNull(rangerDao);
		assertNotNull(rangerService);
	}

}