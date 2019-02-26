package kr.or.ddit.ioc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

public class IocMain {
	
	private static Logger logger = LoggerFactory.getLogger(IocMain.class);
	
	public static void main(String[] args) {
		//스프링 컨테이너 생성
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/application-context.xml");
		
		//DL(Dependency Lookup) : spring container로 부터 객체를 요청
		IRangerDao rangerDao = (IRangerDao) context.getBean("rangerDaoSpringBean");
		List<String> rangers = rangerDao.getRangers();
	
		for(String ranger : rangers) {
			logger.debug("ranger : {}", ranger);
		}
		logger.debug("================================================");
		
		//rangerService DL(두번째 인자에 해당클래스 정보를 넣어주면 형변환을 명시적으로 안해도됨)
		IRangerService rangerService = context.getBean("rangerService", IRangerService.class);
		List<String> rangers2 = rangerService.getRangers();
		
		for(String ranger : rangers2) {
			logger.debug("ranger : {}", ranger);
		}
		logger.debug("================================================");
		
		//rangerService 생성자 주입(rangerDao)
		IRangerService rangerServiceConstructor = context.getBean("rangerServiceConstructor", IRangerService.class);
		List<String> rangers3 = rangerServiceConstructor.getRangers();
		
		for(String ranger : rangers3) {
			logger.debug("ranger : {}", ranger);
		}
	}
}