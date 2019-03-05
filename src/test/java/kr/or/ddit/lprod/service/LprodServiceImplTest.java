package kr.or.ddit.lprod.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.lprod.dao.LprodDaoImplTest;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodServiceImplTest extends LogicTestConfig{

	private Logger logger = LoggerFactory.getLogger(LprodDaoImplTest.class);
	private SqlSession sqlSession;
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@After
	public void tearDown(){
		sqlSession.close();
	}

	/**
	 * Method : testGetAllLprod
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 전체 lprod 리스트 조회
	 */
	@Test
	public void testGetAllLprod() {
		/***Given***/
		
		/***When***/
		List<LprodVo> lprodList = lprodService.getAllLprod();
		
		/***Then***/
		assertNotNull(lprodList);
		assertEquals(9, lprodList.size());
	}
	
	/**
	 * Method : testSelectProd
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : prod 리스트 조회
	 */
	@Test
	public void testSelectProd(){
		/***Given***/
		String lprod_gu = "P101";
		
		/***When***/
		List<ProdVo> prodList = lprodService.selectProd(lprod_gu);
		
		/***Then***/
		assertNotNull(prodList);
		assertEquals(6, prodList.size());
	}
	
	
	/**
	 * Method : testSelectUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : lprod 페이징 리스트 조회
	 */
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		
		/***When***/
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int lprodCnt = (int) resultMap.get("lprodCnt");
		for(LprodVo vo : lprodList){
			logger.debug("lprodVo : {}", vo);
		}

		/***Then***/
		assertNotNull(lprodList);
		assertEquals(9, lprodList.size());
		assertEquals(9, lprodCnt);
	}
}