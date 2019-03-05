package kr.or.ddit.mvc.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.test.WebTestConfig;

public class MvcControllerTest extends WebTestConfig{

	/**
	 * Method : testView
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 파일 업로드 테스트뷰 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testView() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/mvc/view")).andExpect(status().isOk()) //static import로 인해 메소드명으로만 사용가능(status)
										.andExpect(view().name("mvc/view"));
		/***Then***/
	}
	
	/**
	 * Method : testFileupload
	 * 작성자 : pc11
	 * 변경이력 :
	 * Method 설명 : 파일 업로드 테스트
	 * @throws Exception 
	 */
	@Test
	public void testFileupload() throws Exception{
		/***Given***/
		File profileFile = new File("D:\\레인저스사진\\real.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "real.jpg", "image/jpg", fis);
		mockMvc.perform(fileUpload("/mvc/fileupload").file(file)
													.param("userId", "brown"))
													.andExpect(status().isOk())
													.andExpect(view().name("mvc/view"));
		/***When***/
		
		/***Then***/
	}

}