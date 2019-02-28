package kr.or.ddit.login;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class LoginControllerTest extends WebTestConfig{

	/**
	 * Method : testLoginView
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 로그인 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testLoginView() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("login/login", viewName);
	}
	
	/**
	 * Method : testLoginProcess_success
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 사용자 로그인 요청 성공 테스트
	 * @throws Exception 
	 */
	@Test
	public void testLoginProcess_success() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login")
									.param("userId", "brown")
									.param("pass", "brown1234")).andReturn();
		
		//session userVo
		//main
		MockHttpServletRequest req = mvcResult.getRequest();
		HttpSession session = req.getSession();
		UserVo userVo = (UserVo)session.getAttribute("userVo");
		
		//view name
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("main", viewName);
		assertEquals("브라운", userVo.getUserNm());

	}
	
	@Test
	public void testLoginProcess_fail() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login")
									.param("userId", "brown")
									.param("pass", "brown1235")).andReturn();
		
		//view name
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("login/login", viewName);
	}

}