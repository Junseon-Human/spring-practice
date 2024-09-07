package com.keduit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TodoServiceTest {
	
	@Autowired
	public TodoService service;
	
	@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		cri.setAmount(10);
		cri.setPageNum(5);
		service.getList(cri).forEach(board -> log.info(board));
	}
}
