package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.validator.UserVoValidator;

@Controller
public class MvcController {
	
	private Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	/**
	 * Method : view
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : part를 테스트할 view 요청
	 */
	@RequestMapping("/mvc/view")
	public String view(){
		return "mvc/view";
	}
	
	/**
	 * Method : fileupload
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : fileupload 처리 요청 테스트
	 */
	@RequestMapping("/mvc/fileupload")
	public String fileupload(@RequestParam("userId")String userId, 
							@RequestPart("profile")MultipartFile multipartFile){
		
		logger.debug("userId : {}", userId);
		logger.debug("OriginalFilename() : {}", multipartFile.getOriginalFilename());
		logger.debug("Name() : {}", multipartFile.getName());
		logger.debug("Size() : {}", multipartFile.getSize());
		
		String filename = multipartFile.getOriginalFilename() + "_" + UUID.randomUUID().toString();
		File profile = new File("d:\\picture\\" + filename);
		try {
			multipartFile.transferTo(profile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "mvc/view";
	}
	
	@RequestMapping("/mvc/textView")
	public String textView(){
		return "mvc/textView";
	}
	
	//@RequestParam 어노테이션을 적용하지 않아도
	//인스턴스명이랑 동일하면 바인딩을 자동으로 해줌
	//파라미터명이랑 인스턴스 명이랑 다를경우 --> @RequestParam을 사용
	@RequestMapping("/mvc/textReq2")
	public String textReq2(String userId, String pass, Model model){
		logger.debug("userId : {}", userId);
		logger.debug("pass : {}", pass);
		
		//pass : 8자리 이상
		if(pass.length() < 8){
			model.addAttribute("passwordLengthMsg", "비밀번호는 8자리 이상이어야 합니다.");
		}
		return "mvc/textView";
	}
	
	//BindingResult객체는 command객체(vo)에 바인딩 과정에서 발생한 결과를 담는 객체로
	//반드시 command객체 메소드 인자 뒤에 위치 해야한다
	// O : UserVo userVo, BindingResult result, Model model
	// X : UserVo userVo, Model model, BindingResult result
	@RequestMapping("/mvc/textReq")
	public String textReq(UserVo userVo, BindingResult result, Model model){
		new UserVoValidator().validate(userVo, result);
		
		logger.debug("userId : {}", userVo.getUserId());
		logger.debug("pass : {}", userVo.getPass());
		
		if(result.hasErrors()){
			logger.debug("has error");
			return "mvc/textView";
		}
		
		return "mvc/textView";
	}
	
	@RequestMapping("/mvc/textReqJsr303")
	public String textReqJsr303(@Valid UserVo userVo, BindingResult result){
		logger.debug("has error : {}", result.hasErrors());
		return "mvc/textView";
	}
	
}