package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextPathListener implements ServletContextListener {
	
	private Logger logger = LoggerFactory.getLogger(ContextPathListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.debug("ContextPathListener Initialized");
		// contextPath 값을 짧은 이름으로 application scope에 저장
		ServletContext appContext = sce.getServletContext();
		String contextPath = appContext.getContextPath();
		// 경로 통일화
		// ${cp}
		// ${cp}
		appContext.setAttribute("cp", contextPath);

		logger.debug("컨텍스트패스 : {}", contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}