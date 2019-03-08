package kr.or.ddit.lprod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodDaoImplTest extends LogicTestConfig{
	
	private Logger logger = LoggerFactory.getLogger(LprodDaoImplTest.class);
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	@Before
	public void setup(){
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
		List<LprodVo> lprodList = lprodDao.getAllLprod();
		
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
		List<ProdVo> prodList = lprodDao.selectProd(lprod_gu);
		
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
		List<LprodVo> lprodList = lprodDao.selectLprodPagingList(pageVo);
		for(LprodVo vo : lprodList){
			logger.debug("lprodVo : {}", vo);
		}

		/***Then***/
		assertNotNull(lprodList);
		assertEquals(9, lprodList.size());
	}
	
	/**
	 * Method : testLprodCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : lprod 수 조회
	 */
	@Test
	public void testLprodCnt(){
		/***Given***/
		
		/***When***/
		int lprodCnt = lprodDao.getLprodCnt();

		/***Then***/
		assertEquals(9, lprodCnt);
	}

}