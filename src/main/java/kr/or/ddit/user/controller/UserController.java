package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
//		return "user/userAllList";
		return "userAllListTiles"; //tiles 설정파일의 definition 이름(name)과 동일함
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
		
//		return "user/userPagingList";
		return "userPagingListTiles"; //tiles 설정파일의 definition 이름(name)과 동일함
	}
	
	/**
	 * Method : userPagingListAjaxView
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 뷰
	 */
	@RequestMapping("/userPagingListAjaxView")
	public String userPagingListAjaxView(){
		return "userPagingListAjaxTiles"; //tiles 설정파일의 definition 이름(name)과 동일함
	}
	
	/**
	 * Method : userPagingListAjax
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVo
	 * @param model
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 ajax 요청 처리
	 */
	@RequestMapping("/userPagingListAjax")
	public String userPagingListAjax(PageVo pageVo, Model model){
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
		
		return "jsonView"; //bean 객체의 이름과 동일함
	}
	
	@RequestMapping("/userPagingListAjaxHtml")
	public String userPagingListAjaxHtml(PageVo pageVo, Model model){
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
		
		return "user/userPagingListAjaxHtml";
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
		
//		return "user/user";
		return "userTiles"; //tiles 설정파일의 definition 이름(name)과 동일함
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
						Model model,
						HttpServletRequest req) throws Exception{
		
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
			}
			userVo.setFilename(filename);
			userVo.setRealFilename(realFilename);
			
			//사용자 비밀번호 암호화로직
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			
			int insertCnt = 0;
			try {
				insertCnt = userService.insertUser(userVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//정상입력(성공)
			if(insertCnt == 1){
				session.setAttribute("msg", "정상 등록 되었습니다.");
				return "redirect:" + "/user/userPagingList"; //루트path 지정해야하지만 contextPath에 관한 리스너 등록시 자동으로 붙여진다!!!
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
								@RequestPart("profile")MultipartFile profile,
								RedirectAttributes ra,
								HttpServletRequest req) throws Exception{
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
		
		//비밀번호 수정 요청여부
		//사용자가 값을 입력하지 않은 경우 => 기존 비밀번호 유지
		if(userVo.getPass().equals("")){
			UserVo userVoForPass = userService.selectUser(userVo.getUserId());
			userVo.setPass(userVoForPass.getPass());
		}
		//사용자가 비밀번호를 신규 등록한 경우
		else{
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass())); //패스워드 암호화
		}
		
		//사용자 수정
		int updateCnt = 0;
		try {
			updateCnt = userService.updateUser(userVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//정상 수정(성공)
		if(updateCnt == 1){
			//redirect 파라미터를 보내는 방법
			// 1.url로 작성
			//   return "redirect:/user/user?userId=" + userVo.getUserId();
			// 2.model객체의 속성 : 저장된 속성을 자동을 파라미터 변환
			//   model.addAttribute("userId", userVo.getUserId());
			//   return "redirect:/user/user";
			// 3.RedirectAttributes(ra) 객체를 이용
			//   ra.addAttribute("userId", userVo.getUserId());
			//   return "redirect:/user/user";
			
			ra.addAttribute("userId", userVo.getUserId());
			ra.addFlashAttribute("msg", "정상 등록 되었습니다."); //RedirectAttributes에만 있는 기능으로 일시적으로 속성이 존재(한번읽고 삭제됨)
			return "redirect:" + "/user/user"; //루트path 지정해야하지만 contextPath에 관한 리스너 등록시 자동으로 붙여진다!!!
		}
		//정상 수정(실패)
		else{
			return "user/userModifyForm";
		}
	}
}