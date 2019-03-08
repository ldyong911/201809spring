package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.model.ProdVo;
import kr.or.ddit.util.model.PageVo;

@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * Method : getAllLprod
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 lprod 리스트 조회
	 */
	@Override
	public List<LprodVo> getAllLprod() {
		List<LprodVo> list = sqlSessionTemplate.selectList("lprod.getAllLprod");
		
		return list;
	}

	/**
	 * Method : selectProd
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param lprod_gu
	 * @return
	 * Method 설명 : lprod에 해당하는 prod 리스트 조회
	 */
	@Override
	public List<ProdVo> selectProd(String lprod_gu) {
		List<ProdVo> list = sqlSessionTemplate.selectList("lprod.selectProd", lprod_gu);
		
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
	public List<LprodVo> selectLprodPagingList(PageVo pageVo) {
		List<LprodVo> lprodList = sqlSessionTemplate.selectList("lprod.selectLprodPagingList", pageVo);
		
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
	public int getLprodCnt() {
		int lprodCnt = sqlSessionTemplate.selectOne("lprod.getLprodCnt");
		
		return lprodCnt;
	}
	
}