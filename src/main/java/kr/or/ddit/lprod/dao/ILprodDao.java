package kr.or.ddit.lprod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.model.ProdVo;
import kr.or.ddit.util.model.PageVo;

public interface ILprodDao {
	
	/**
	 * Method : getAllLprod
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 lprod 리스트 조회
	 */
	List<LprodVo> getAllLprod(SqlSession sqlSession);
	
	/**
	 * Method : selectProd
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param lprod_gu
	 * @return
	 * Method 설명 : lprod에 해당하는 prod 리스트 조회
	 */
	List<ProdVo> selectProd(SqlSession sqlSession, String lprod_gu);
	
	/**
	 * Method : selectLprodPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param pageVo
	 * @return
	 * Method 설명 : lprod 페이지 리스트 조회
	 */
	List<LprodVo> selectLprodPagingList(SqlSession sqlSession, PageVo pageVo);
	
	/**
	 * Method : getLprodCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 lprod수 조회
	 */
	int getLprodCnt(SqlSession sqlSession);
	
}