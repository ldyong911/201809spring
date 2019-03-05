package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends WebTestConfig{
	
	private static final String USER_INSERT_TEST_ID = "realTest";
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void initData(){
		userService.deleteUser(USER_INSERT_TEST_ID);
	}

	/**
	 * Method : testUserAllList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 전체 조회 테스트
	 */
	@Test
	public void testUserAllList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<UserVo> userList = (List<UserVo>)mav.getModel().get("userList");
		
		/***Then***/
		assertEquals("user/userAllList", viewName);
		assertNotNull(userList);
		assertTrue(userList.size() > 100);
	}
	
	/**
	 * Method : testUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	@Test
	public void testUserPagingList() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<UserVo> userList = (List<UserVo>) mav.getModel().get("userList");
		int page = (int) mav.getModel().get("page");
		int pageSize = (int) mav.getModel().get("pageSize");
		
		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertNotNull(userList);
		assertEquals(1, page);
		assertEquals(10, pageSize);
	}
	
	/**
	 * Method : testUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 조회 테스트
	 */
	@Test
	public void testUser() throws Exception{
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModel().get("userVo");

		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("brown", userVo.getUserId());
	}
	
	/**
	 * Method : testUserForm
	 * 작성자 : pc11
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록 요청 뷰 테스트
	 */
	@Test
	public void testUserForm() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("user/userForm", viewName);
	}
	
	/**
	 * Method : testUserForm_post_success
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserForm_post_success() throws Exception{
		/***Given***/
		File profileFile = new File("D:\\레인저스사진\\real.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "real.jpg", "image/jpg", fis);
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/user/userForm").file(file)
															.param("userId", USER_INSERT_TEST_ID)
															.param("userNm", USER_INSERT_TEST_ID)
															.param("alias", "레알")
															.param("addr1", "대전시 중구 대흥로 76")
															.param("addr2", "2층 DDIT")
															.param("zipcode", "34942")
															.param("pass", USER_INSERT_TEST_ID))
														.andExpect(view().name("redirect:/user/userPagingList"))
														.andReturn();
														
		
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
		assertEquals("정상 등록 되었습니다.", session.getAttribute("msg"));
	}
	
	/**
	 * Method : testUserForm_post_fail_duplicateUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록요청(중복 사용자로 인한 등록 실패 케이스) 테스트
	 */
	@Test
	public void testUserForm_post_fail_duplicateUser() throws Exception{
		/***Given***/
		File profileFile = new File("D:\\레인저스사진\\real.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "real.jpg", "image/jpg", fis);
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/user/userForm").file(file)
															.param("userId", "brown")
															.param("userNm", "realTest")
															.param("alias", "레알")
															.param("addr1", "대전시 중구 대흥로 76")
															.param("addr2", "2층 DDIT")
															.param("zipcode", "34942")
															.param("pass", USER_INSERT_TEST_ID))
														.andExpect(view().name("user/userForm"))
														.andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String msg = (String) mav.getModel().get("msg");
		
		/***Then***/
		assertEquals("등록에 실패 했습니다.", msg);
	}
	
	/**
	 * Method : testUserForm_post_fail_insertError
	 * 작성자 : pc11
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록 (zipcode 사이즈 sql에러) 테스트
	 */
	@Test
	public void testUserForm_post_fail_insertError() throws Exception{
		/***Given***/
		File profileFile = new File("D:\\레인저스사진\\real.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "real.jpg", "image/jpg", fis);
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/user/userForm").file(file)
															.param("userId", USER_INSERT_TEST_ID)
															.param("userNm", USER_INSERT_TEST_ID)
															.param("alias", "레알")
															.param("addr1", "대전시 중구 대흥로 76")
															.param("addr2", "2층 DDIT")
															.param("zipcode", "3494234942")
															.param("pass", USER_INSERT_TEST_ID))
														.andExpect(view().name("user/userForm"))
														.andReturn();
														
		
		/***When***/
		
		/***Then***/
	}
	
	/**
	 * Method : testUserModifyForm
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 사용자 수정 요청 뷰 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserModifyForm() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userModifyForm").param("userId", "brown2")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("user/userModifyForm", viewName);
	}
	
	/**
	 * Method : testUserModifyForm_post
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 사용자 수정 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserModifyForm_post() throws Exception{
		/***Given***/
		File profileFile = new File("D:\\레인저스사진\\real.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "real.jpg", "image/jpg", fis);
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/user/userModifyForm").file(file)
															.param("userId", "brown2")
															.param("userNm", "brown2")
															.param("alias", "레알")
															.param("addr1", "대전시 중구 대흥로 76")
															.param("addr2", "2층 DDIT")
															.param("zipcode", "34942")
															.param("pass", "brown2"))
														.andExpect(view().name("redirect:/user/user?userId=brown2"))
														.andReturn();
		
		/***When***/
		
		/***Then***/

	}
	
}