package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	public UserServiceImpl(){
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	}
	
	/**
	 * Method : getAllUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 정보 조회
	 */
	@Override
	public List<UserVo> getAllUser() {
		sqlSession = sqlSessionFactory.openSession();
		List<UserVo> userList = userDao.getAllUser(sqlSession);
		sqlSession.close();
		
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
	public UserVo selectUser(String userId) {
		sqlSession = sqlSessionFactory.openSession();
		UserVo userVO = userDao.selectUser(sqlSession, userId);
		sqlSession.close();
		
		return userVO;
	}

	/**
	 * Method : selectUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVO
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 조회
	 */
	@Override
	public Map<String, Object> selectUserPagingList(PageVo pageVo) {
		sqlSession = sqlSessionFactory.openSession();
		
		//결과값을 두개 담아서 return 하기위해 Map 객체 사용
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userDao.selectUserPagingList(sqlSession, pageVo));
		resultMap.put("userCnt", userDao.getUserCnt(sqlSession));
		
		sqlSession.close();
		
		return resultMap;
	}

	/**
	 * Method : insertUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(UserVo userVo) {
		sqlSession = sqlSessionFactory.openSession();
		int result = userDao.insertUser(sqlSession, userVo);
		sqlSession.commit(); //트랜잭션이 발생되는 쿼리이기때문에 commit해줘야함
		sqlSession.close();
		
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
	public int deleteUser(String userId) {
		sqlSession = sqlSessionFactory.openSession();
		int result = userDao.deleteUser(sqlSession, userId);
		sqlSession.commit(); //트랜잭션이 발생되는 쿼리이기때문에 commit해줘야함
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : updateUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userVO
	 * @return
	 * Method 설명 : 사용자 수정
	 */
	@Override
	public int updateUser(UserVo userVo) {
		sqlSession = sqlSessionFactory.openSession();
		int result = userDao.updateUser(sqlSession, userVo);
		sqlSession.commit(); //트랜잭션이 발생되는 쿼리이기때문에 commit해줘야함
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : encryptPass
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 비밀번호 수정
	 */
	@Override
	public int encryptPass() {
		sqlSession = sqlSessionFactory.openSession();
		
		List<UserVo> userList = userDao.getAllUser(sqlSession);
		
		int result = 0;
		for(UserVo userVo : userList){
			String pass = userVo.getPass();
			String encryptPass = KISA_SHA256.encrypt(pass);
			userVo.setPass(encryptPass);
			
			result += userDao.encryptPass(sqlSession, userVo);
		}
		
		sqlSession.commit(); //트랜잭션이 발생되는 쿼리이기때문에 commit해줘야함
		sqlSession.close();
		
		return result;
	}
}