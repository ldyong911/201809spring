package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public class UserDaoImplTest extends LogicTestConfig{
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	@Before
	public void setup(){
		userDao.deleteUser("test1");
	}
	
	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVo> list = userDao.getAllUser();
		
		/***Then***/
		assertNotNull(list);
		assertEquals(105, list.size());
	}
	
	@Test
	public void testSelectUser(){
		/***Given***/
		
		/***When***/
		UserVo UserVo = userDao.selectUser("brown");
		
		/***Then***/
		assertNotNull(UserVo);

	}
	
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		
		/***When***/
		List<UserVo> userList = userDao.selectUserPagingList(pageVo);
		for(UserVo vo : userList){
			System.out.println(vo.toString());
		}

		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
	}
	
	@Test
	public void testGetUserCnt(){
		/***Given***/
		
		/***When***/
		int userCnt = userDao.getUserCnt();
		
		/***Then***/
		assertEquals(105, userCnt);
	}
	
	@Test
	public void testPagination(){
		/***Given***/
		int userCnt = 105;
		int pageSize = 10;
	
		/***When***/
		System.out.println(Math.ceil((userCnt*1.0/pageSize)));
		int lastPage = (int)Math.ceil((userCnt*1.0/pageSize));
		System.out.println(lastPage);
		
		/***Then***/
		assertEquals(11, lastPage);
	}
	
	@Test
	public void testInsertUser(){
		/***Given***/
		UserVo userVo = new UserVo();
		userVo.setUserId("test1");
		userVo.setUserNm("테스트");
		userVo.setAlias("테스트별명");
		userVo.setAddr1("대전 중구 대흥로 76");
		userVo.setAddr2("2층 ddit");
		userVo.setZipcode("34942");
		userVo.setPass("testPass");
		
		/***When***/
		int result = userDao.insertUser(userVo);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdateUser(){
		/***Given***/
		UserVo userVo = new UserVo();
		userVo.setUserId("brown2");
		userVo.setUserNm("테스트");
		userVo.setAlias("테스트별명");
		userVo.setAddr1("대전 중구 대흥로 76");
		userVo.setAddr2("2층 ddit");
		userVo.setZipcode("34942");
		userVo.setPass("testPass");
		
		/***When***/
		int result = userDao.updateUser(userVo);

		/***Then***/
		assertEquals(1, result);
	}
	
}