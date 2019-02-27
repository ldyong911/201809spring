package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;
import kr.or.ddit.ranger.service.IRangerService;
import kr.or.ddit.ranger.service.RangerServiceImpl;

//해당 자바파일이 스프링 빈 설정 클래스임을 알려주는 어노테이션
@Configuration
public class SpringJavaConfig {
	
	//기본값은 <bean name="메서드이름">으로 생성이됨
	@Bean
	public IRangerDao getRangerDao() {
		IRangerDao rangerDao = new RangerDaoImpl();
		return rangerDao;
	}
	
	@Bean
	public IRangerService getRangerService() {
		//rangerService에 주입해야하는 rangerDao 객체는 스프링에서 관리되는 스프링 빈을 주입해야한다.
		// *** new 연산자를 통해서 생성하면 안된다
		IRangerService rangerService = new RangerServiceImpl(getRangerDao());
		return rangerService;
	}
}