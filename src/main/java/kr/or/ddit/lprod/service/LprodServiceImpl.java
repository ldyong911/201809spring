package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.model.ProdVo;
import kr.or.ddit.util.model.PageVo;

@Service("lprodService")
public class LprodServiceImpl implements ILprodService{
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	public LprodServiceImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	}
	
	/**
	 * Method : getAllLprod
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 lprod 리스트 조회
	 */
	@Override
	public List<LprodVo> getAllLprod() {
		sqlSession = sqlSessionFactory.openSession();
		List<LprodVo> lprodList = lprodDao.getAllLprod(sqlSession);
		sqlSession.close();
		
		return lprodList;
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
		sqlSession = sqlSessionFactory.openSession();
		List<ProdVo> prodList = lprodDao.selectProd(sqlSession, lprod_gu);
		sqlSession.close();
		
		return prodList;
	}

	/**
	 * Method : selectLprodPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : lprod 페이지 리스트 조회
	 */
	@Override
	public Map<String, Object> selectLprodPagingList(PageVo pageVo) {
		sqlSession = sqlSessionFactory.openSession();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("lprodList", lprodDao.selectLprodPagingList(sqlSession, pageVo));
		resultMap.put("lprodCnt", lprodDao.getLprodCnt(sqlSession));
		
		sqlSession.close();
		
		return resultMap;
	}
	
}