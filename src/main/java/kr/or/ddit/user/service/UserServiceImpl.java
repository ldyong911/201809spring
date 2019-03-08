package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	/**
	 * Method : getAllUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 정보 조회
	 */
	@Override
	public List<UserVo> getAllUser() {
		List<UserVo> userList = userDao.getAllUser();
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
		UserVo userVO = userDao.selectUser(userId);
		return userVO;
	}

	/**
	 * Method : selectUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 조회
	 */
	@Override
	public Map<String, Object> selectUserPagingList(PageVo pageVo) {
		//결과값을 두개 담아서 return 하기위해 Map 객체 사용
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userDao.selectUserPagingList(pageVo));
		resultMap.put("userCnt", userDao.getUserCnt());
		
		return resultMap;
	}

	/**
	 * Method : insertUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(UserVo userVo) {
		int result = userDao.insertUser(userVo);
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
		int result = userDao.deleteUser(userId);
		return result;
	}

	/**
	 * Method : updateUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 사용자 수정
	 */
	@Override
	public int updateUser(UserVo userVo) {
		int result = userDao.updateUser(userVo);
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
		List<UserVo> userList = userDao.getAllUser();
		
		int result = 0;
		for(UserVo userVo : userList){
			String pass = userVo.getPass();
			String encryptPass = KISA_SHA256.encrypt(pass);
			userVo.setPass(encryptPass);
			
			result += userDao.encryptPass(userVo);
		}
		
		return result;
	}
}