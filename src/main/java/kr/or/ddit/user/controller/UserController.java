package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/user")
@Controller
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * Method : userAllList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 전체 리스트 조회
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model){
		List<UserVo> userList = userService.getAllUser();
		model.addAttribute("userList", userList);
		
		return "user/userAllList";
	}
	
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVo pageVo, Model model){
		int page = pageVo.getPage();
		int pageSize = pageVo.getPageSize();
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		
		int userCnt = (int) resultMap.get("userCnt");
		int lastPage = userCnt/pageSize + (userCnt%pageSize > 0 ? 1 : 0);
		int lastPageStartPage = ((lastPage - 1) / 10) * 10 + 1;
		int startPage = ((page - 1) / 10) * 10 + 1; 
		int endPage = startPage + 10 - 1;
		
		model.addAttribute("userList", userList);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("lastPageStartPage", lastPageStartPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "user/userPagingList";
	}
}