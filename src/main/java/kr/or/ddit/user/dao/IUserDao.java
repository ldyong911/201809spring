package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface IUserDao {
	/**
	 * Method : getAllUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	List<UserVo> getAllUser(SqlSession sqlSession);
	
	/**
	 * Method : selectUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	UserVo selectUser(SqlSession sqlSession, String userId);
	
	/**
	 * Method : selectUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVO
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 조회
	 */
	List<UserVo> selectUserPagingList(SqlSession sqlSession, PageVo pageVO);
	
	/**
	 * Method : getUserCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수를 조회
	 */
	int getUserCnt(SqlSession sqlSession);
	
	/**
	 * Method : insertUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userVO
	 * Method 설명 : 사용자 등록
	 */
	int insertUser(SqlSession sqlSession, UserVo userVO);
	
	/**
	 * Method : deleteUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 삭제
	 */
	int deleteUser(SqlSession sqlSession, String userId);
	
	/**
	 * Method : updateUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 수정
	 */
	int updateUser(SqlSession sqlSession, UserVo userVO);
	
	/**
	 * Method : encryptPass
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화
	 */
	int encryptPass(SqlSession sqlSession, UserVo userVO);
}