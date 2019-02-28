package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{
	
	/**
	 * Method : getAllUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	@Override
	public List<UserVo> getAllUser(SqlSession sqlSession){
		List<UserVo> userList = sqlSession.selectList("user.getAllUser");
		
		return userList;
	}
	
	/**
	 * Method : selectUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	@Override
	public UserVo selectUser(SqlSession sqlSession, String userId){
		UserVo userVO = sqlSession.selectOne("user.selectUser", userId);
		
		return userVO;
	}

	/**
	 * Method : selectUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVO
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public List<UserVo> selectUserPagingList(SqlSession sqlSession, PageVo pageVO) {
		List<UserVo> userList = sqlSession.selectList("user.selectUserPagingList", pageVO);
		
		return userList;
	}

	/**
	 * Method : getUserCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수를 조회
	 */
	@Override
	public int getUserCnt(SqlSession sqlSession) {
		int userCnt = sqlSession.selectOne("user.getUserCnt");
		
		return userCnt;
	}

	/**
	 * Method : insertUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userVO
	 * Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(SqlSession sqlSession, UserVo userVO) {
		int result = sqlSession.insert("user.insertUser", userVO);
		
		return result;
	}

	/**
	 * Method : deleteUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 삭제
	 */
	@Override
	public int deleteUser(SqlSession sqlSession, String userId) {
		int result = sqlSession.delete("user.deleteUser", userId);
		
		return result;
	}

	/**
	 * Method : updateUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 수정
	 */
	@Override
	public int updateUser(SqlSession sqlSession, UserVo userVO) {
		int result = sqlSession.update("user.updateUser", userVO);
		
		return result;
	}

	/**
	 * Method : encryptPass
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화
	 */
	@Override
	public int encryptPass(SqlSession sqlSession, UserVo userVO) {
		int result = sqlSession.update("user.updatePass", userVO);
		
		return result;
	}
}