package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.model.ProdVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	private Logger logger = LoggerFactory.getLogger(LprodController.class);
	
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	@RequestMapping(path="/lprodAllList", method=RequestMethod.GET)
	public String lprodAllList(Model model){
		List<LprodVo> lprodList = lprodService.getAllLprod();
		model.addAttribute("lprodList", lprodList);
		
		return "lprod/lprodAllList";
	}
	
	@RequestMapping(path="/lprodPagingList", method=RequestMethod.GET)
	public String lprodPagingList(PageVo pageVo, Model model){
		int page = pageVo.getPage();
		int pageSize = pageVo.getPageSize();
		
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);
		List<LprodVo> lprodList = (List<LprodVo>)resultMap.get("lprodList");
		int lprodCnt = (Integer)resultMap.get("lprodCnt");
		
		int lastPage = lprodCnt/pageSize + (lprodCnt%pageSize > 0 ? 1 : 0);
		int lastPageStartPage = ((lastPage - 1) / 10) * 10 + 1;
		int startPage = ((page - 1) / 10) * 10 + 1; 
		int endPage = startPage + 10 - 1;
		
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("lprodCnt", lprodCnt);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("lastPageStartPage", lastPageStartPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "lprod/lprodPagingList";
	}
	
	@RequestMapping(path="/prodList", method=RequestMethod.GET)
	public String prodList(@RequestParam("lprod_gu")String lprod_gu, Model model){
		List<ProdVo> prodList = lprodService.selectProd(lprod_gu);
		model.addAttribute("prodList", prodList);
		
		return "lprod/prodList";
	}
}