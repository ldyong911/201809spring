package kr.or.ddit.ranger.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml"})
public class RangerDaoImplTest {
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao;
	
	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index = -1;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("brown", ranger);
	}
	
	@Test
	public void testGetRanger_overflowIndex() {
		/***Given***/
		int index = 5;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("james", ranger);
	}
	
	@Test
	public void testGetRanger() {
		/***Given***/
		int index = 2;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("sally", ranger);
	}

}