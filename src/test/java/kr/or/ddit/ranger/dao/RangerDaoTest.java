package kr.or.ddit.ranger.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import kr.or.ddit.test.LogicTestConfig;

//service와 dao 설정은 LogicTestConfig에서 작성후 상속받아 사용
public class RangerDaoTest extends LogicTestConfig{
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao;
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Before
	public void setup(){
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript(new ClassPathResource("kr/or/ddit/config/db/init.sql"));
		rdp.setContinueOnError(false);
		DatabasePopulatorUtils.execute(rdp, dataSource);
	}
	
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
	
	/**
	 * Method : testGetRangersDb
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 레인저 전체 조회
	 */
	@Test
	public void testGetRangersDb(){
		/***Given***/
		
		/***When***/
		List<Map<String, String>> rangerList = rangerDao.getRangersDb();
		
		/***Then***/
		assertEquals(5, rangerList.size());
	}
	
	/**
	 * Method : testGetRangerId
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 아이디로 레인저 조회 테스트
	 */
	@Test
	public void testGetRangerId(){
		/***Given***/
		
		/***When***/
		Map<String, String> ranger = rangerDao.getRanger("brown");
		
		/***Then***/
		assertNotNull(ranger);
		assertEquals("브라운", ranger.get("NAME")); //sql에서 *로 조회시 map객체에 대문자로 리턴되므로 조심!
	}
	
	/**
	 * Method : testInsertRanger
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 신규 레인저 등록 테스트
	 */
	@Test
	public void testInsertRanger(){
		/***Given***/
		Map<String, String> ranger = new HashMap<String, String>();
		ranger.put("id", "ryan");
		ranger.put("name", "라이언");
		
		/***When***/
		int insertCnt = rangerDao.insertRanger(ranger);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	 * Method : testDeleteRanger
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 레인저 삭제 테스트
	 */
	@Test
	public void testDeleteRanger(){
		/***Given***/
		rangerDao.deleteRangerDept("brown");
		
		/***When***/
		int deleteCnt = rangerDao.deleteRanger("brown");
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}