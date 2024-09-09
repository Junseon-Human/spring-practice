package com.keduit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class ReplyServiceTest {
	
	private ReplyService service;
	
	@Test
	public void test() {
		Criteria cri = new Criteria(1,5);
		
		service.getListPage(cri, 589865L);
	}
}
