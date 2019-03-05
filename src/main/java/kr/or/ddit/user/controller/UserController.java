package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
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
	
	/**
	 * Method : userPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVo
	 * @param model
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
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
	
	/**
	 * Method : user
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @param model
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId, Model model){
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "user/user";
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletResponse resp,
							HttpServletRequest req,
							@RequestParam("userId")String userId) throws Exception{
		resp.setContentType("image");
		
		UserVo userVo = userService.selectUser(userId);
		
		FileInputStream fis;
		if(userVo != null && userVo.getRealFilename() != null){
			fis = new FileInputStream(new File(userVo.getRealFilename()));
		}else{
			ServletContext application = req.getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		ServletOutputStream sos = resp.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff)) > -1){
			sos.write(buff);
		}
		
		sos.close();
		fis.close();
	}
	
	/**
	 * Method : userForm
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록폼 요청
	 */
	@RequestMapping(path="/userForm", method=RequestMethod.GET)
	public String userForm(){
		return "user/userForm";
	}
	
	@RequestMapping(path="/userForm", method=RequestMethod.POST)
	public String userForm(UserVo userVo,
						@RequestPart("profile")MultipartFile profile,
						HttpSession session,
						Model model) throws Exception{
		
		UserVo duplicateUserVo =  userService.selectUser(userVo.getUserId());
		
		//중복체크 통과(신규등록)
		if(duplicateUserVo == null){
			//사용자 프로파일을 업로드 한경우
			String filename = "";
			String realFilename = "";
			if(profile.getSize() > 0){
				filename = profile.getOriginalFilename();
				realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
				
				profile.transferTo(new File(realFilename));
				
				userVo.setFilename(filename);
				userVo.setRealFilename(realFilename);
			}
			
			int insertCnt = 0;
			try {
				insertCnt = userService.insertUser(userVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//정상입력(성공)
			if(insertCnt == 1){
				session.setAttribute("msg", "정상 등록 되었습니다.");
				return "redirect:/user/userPagingList";  //contextPath 작업 필요
			}else{
				return "user/userForm"; //검증 필요
			}
		}
		//중복체크 실패
		else{
			model.addAttribute("msg", "등록에 실패 했습니다.");
			return "user/userForm";
		}
	}
	
	
	/**
	 * Method : userModifyForm
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @param model
	 * @return
	 * Method 설명 : 사용자 수정폼 요청
	 */
	@RequestMapping(path="/userModifyForm", method=RequestMethod.GET)
	public String userModifyForm(@RequestParam("userId")String userId, Model model){
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "user/userModifyForm";
	}
	
	@RequestMapping(path="/userModifyForm", method=RequestMethod.POST)
	public String userModifyForm(UserVo userVo,
								@RequestPart("profile")MultipartFile profile) throws Exception{
//		request.setCharacterEncoding("UTF-8");
		
		logger.debug("userVo : {}", userVo);
		
		//기존 사진 가져오기
		String filename = "";
		String realFilename = "";
		
		//사진 수정
		if(profile.getSize() > 0){
			filename = profile.getOriginalFilename();
			realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
			
			profile.transferTo(new File(realFilename));
		}
		userVo.setFilename(filename);
		userVo.setRealFilename(realFilename);
		
		//사용자 수정
		userVo.setPass(KISA_SHA256.encrypt(userVo.getPass())); //패스워드 암호화
		int updateCnt = userService.updateUser(userVo);
		
		//정상 수정(성공)
		if(updateCnt == 1){
//			response.sendRedirect(request.getContextPath() + "/user?userId=" + userId);
			return "redirect:/user/user?userId=" + userVo.getUserId();
		}
		//정상 수정(실패)
		else{
			return "user/userModifyForm";
		}
	}
}