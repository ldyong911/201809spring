package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.model.RangerVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-conversion.xml")
public class StringDateConverterTest {

	@Resource(name="rangerVo")
	private RangerVo rangerVo;
	
	@Test
	public void testRangerVo() {
		/***Given***/
		
		/***When***/
		String userId = rangerVo.getUserId();
		Date birth = rangerVo.getBirth();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String birthStr = sdf.format(birth);
		
		/***Then***/
		assertNotNull(rangerVo);
		assertEquals("brown", userId);
//		assertEquals("2018-08-08", birthStr);
		assertEquals("08-08-2018", birthStr);
	}
}