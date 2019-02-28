package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public class UserServiceImplTest extends LogicTestConfig{
	private SqlSession sqlSession;
	
	@Resource(name="userService")
	private IUserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		
		userService.deleteUser("test1");
	}
	
	@After
	public void tearDown(){
		sqlSession.close();
	}

	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVo> list = userService.getAllUser();
		
		/***Then***/
		assertNotNull(list);
		assertEquals(105, list.size());
	}
	
	@Test
	public void testSelectUser(){
		/***Given***/
		
		/***When***/
		UserVo userVo = userService.selectUser("brown");

		/***Then***/
		assertNotNull(userVo);
	}
	
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVo pageVO = new PageVo(1, 10);
		
		/***When***/
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVO);
		List<UserVo> userList = (List<UserVo>)resultMap.get("userList");
		int userCnt = (Integer)resultMap.get("userCnt");
		
		for(UserVo vo : userList){
			System.out.println(vo.toString());
		}
		
		System.out.println("userCnt : " + userCnt);

		/***Then***/
		//userList
		assertNotNull(userList);
		assertEquals(10, userList.size());
		
		//userCnt
		assertEquals(105, userCnt);
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
		int result = userService.insertUser(userVo);
		
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
		int result = userService.updateUser(userVo);
		
		/***Then***/
		assertEquals(1, result);
	}
	
//	@Test //한번 암호화하고 실행되지않아야함
	public void testEncryptPass(){
		/***Given***/
		List<UserVo> userList = userService.getAllUser();
		
		/***When***/
		int result = userService.encryptPass();
		
		logger.debug("result : {}", result);
		
		/***Then***/
		
	}
	
}