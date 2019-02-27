package kr.or.ddit.ranger.service;

import java.util.List;

import kr.or.ddit.ranger.dao.IRangerDao;

public interface IRangerService {
	/**
	 * Method : getRangers
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 조회
	 */
	List<String> getRangers();
	
	IRangerDao getRangerDao();
	
	/**
	 * Method : getRanger
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param listIndex
	 * @return
	 * Method 설명 : listIndex에서 해당 이름을 반환
	 */
	String getRanger(int listIndex);
}