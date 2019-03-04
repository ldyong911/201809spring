package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
}