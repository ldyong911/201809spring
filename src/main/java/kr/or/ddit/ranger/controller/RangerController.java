package kr.or.ddit.ranger.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ranger.service.IRangerService;

@RequestMapping("/ranger")
@Controller
public class RangerController {
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	/**
	 * Method : getRangers
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 리스트를 조회
	 */
	// localhost/ranger/getRangers 라고 요청시 밑의 메소드에서 요청을 처리
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		List<String> rangers = rangerService.getRangers();
		
		//request.setAttribute("rangers", rangers); 와 같은 의미
		model.addAttribute("rangers", rangers);
		
		/*
		   <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		   	<property name="prefix" value="/WEB-INF/views/"/>
			<property name="suffix" value=".jsp"/>
		   </bean>
		   
		    설정파일에서 설정한 값으로인해
		   return 되는 view의 이름인 ranger/rangerList가 
		   /WEB-INF/views/ranger/rangerList.jsp 형태가 됨
		  
		 */
		
		return "ranger/rangerList";
	}
	
	// localhost/ranger/getRanger?listIndex=2 라고 요청시 밑의 메소드에서 요청을 처리
	@RequestMapping("/getRanger")
	public String getRanger(HttpServletRequest req, Model model) {
		int listIndex = Integer.parseInt(req.getParameter("listIndex"));
		String ranger = rangerService.getRanger(listIndex);
		
		model.addAttribute("ranger", ranger);
		
		return "ranger/ranger";
	}
	
}