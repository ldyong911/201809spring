package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class ProfileImgView implements View{
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Override
	public String getContentType() {
		return "image";
	}

	//개발자는 이미지 경로를 model객체에 "path"라는 속성으로 설정한다
	//개발자는 사용자 아이디를 model객체에 userId라는 속성으로 설정한다
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//response.setHeader("Content-Disposition", "attachment; filename=profile.png");
		//response.setContentType("application/octet-stream");
		response.setContentType("image");
		
		//1.사용자 아이디 파라미터 확인
		String userId = (String) model.get("userId");
		
		//2.해당 사용자 아이디로 사용자 정보 조회(realFilename)
		UserVo userVo = userService.selectUser(userId);
		
		//3-1.realFilename이 존재할 경우
		//3-1-1.해당 경로의 파일을 FileInputStream으로 읽는다
		FileInputStream fis;
		if(userVo != null && userVo.getRealFilename() != null){
			fis = new FileInputStream(new File(userVo.getRealFilename()));
		}
		
		//3-2.realFilename이 존재하지 않을 경우
		//3-2-1. /upload/noimg.png (application.getRealPath())
		else{
			ServletContext application = request.getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		//4.FileInputStream을 response객체의 outputStream 객체에 write
		ServletOutputStream sos = response.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff)) > -1){
			sos.write(buff);
		}
		
		sos.close();
		fis.close();
	}

}