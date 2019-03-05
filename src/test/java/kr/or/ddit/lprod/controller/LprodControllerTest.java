package kr.or.ddit.lprod.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.model.ProdVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.test.WebTestConfig;

public class LprodControllerTest extends WebTestConfig{
	
	@Resource(name="lprodService")
	private ILprodService lprodService;

	@Test
	public void testGetAllLprod() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/lprodAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModel().get("lprodList");
		
		/***When***/

		/***Then***/
		assertEquals("lprod/lprodAllList", viewName);
		assertEquals(9, lprodList.size());
	}
	
	@Test
	public void testLprodPagingList() throws Exception{
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/lprodPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModel().get("lprodList");
		int lprodCnt = (int) mav.getModel().get("lprodCnt");
		int page = (int) mav.getModel().get("page");
		int pageSize = (int) mav.getModel().get("pageSize");
		
		/***When***/

		/***Then***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertEquals(9, lprodList.size());
		assertEquals(9, lprodCnt);
		assertEquals(1, page);
		assertEquals(10, pageSize);
	}
	
	@Test
	public void testProdList() throws Exception{
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/prodList").param("lprod_gu", "P101"))
																	.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<ProdVo> prodList = (List<ProdVo>) mav.getModel().get("prodList");
		
		/***When***/

		/***Then***/
		assertEquals("lprod/prodList", viewName);
		assertEquals(6, prodList.size());
	}

}