package kr.or.ddit.lprod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.model.ProdVo;
import kr.or.ddit.util.model.PageVo;

@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao{
	
	/**
	 * Method : getAllLprod
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 lprod 리스트 조회
	 */
	@Override
	public List<LprodVo> getAllLprod(SqlSession sqlSession) {
		List<LprodVo> list = sqlSession.selectList("lprod.getAllLprod");
		
		return list;
	}

	/**
	 * Method : selectProd
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param lprod_gu
	 * @return
	 * Method 설명 : lprod에 해당하는 prod 리스트 조회
	 */
	@Override
	public List<ProdVo> selectProd(SqlSession sqlSession, String lprod_gu) {
		List<ProdVo> list = sqlSession.selectList("lprod.selectProd", lprod_gu);
		
		return list;
	}

	/**
	 * Method : selectLprodPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param pageVo
	 * @return
	 * Method 설명 : lprod 페이지 리스트 조회
	 */
	@Override
	public List<LprodVo> selectLprodPagingList(SqlSession sqlSession, PageVo pageVo) {
		List<LprodVo> lprodList = sqlSession.selectList("lprod.selectLprodPagingList", pageVo);
		
		return lprodList;
	}

	/**
	 * Method : getLprodCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 lprod수 조회
	 */
	@Override
	public int getLprodCnt(SqlSession sqlSession) {
		int lprodCnt = sqlSession.selectOne("lprod.getLprodCnt");
		
		return lprodCnt;
	}
	
}