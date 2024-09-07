package com.keduit.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info("service : " + service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("서비스에서 등록한 새 글");
		board.setContent("서비스에서 등록한 새 내용");
		board.setWriter("service01");
		
		long bno = service.register(board);
		log.info("생성된 게시물의 번호 : " + bno);
	}
	
	@Test
	public void testGetlist() {
		service.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testGet() {
		BoardVO board = service.get(15L);
		log.info("--------- testGet : " + board);
	}
	
	@Test
	public void testRemove() {
		log.info("REMOVE RESULT : " + service.remove(1L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(3L);
		if (board == null) {
			return;
		} else {
			board.setTitle("서비스에서 수정한글");
			board.setContent("서비스에서 수정한 내용");
			board.setWriter("service02");
			service.modify(board);
			log.info("수정완료");
			service.getList();
		}
	}
	
	@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		cri.setAmount(10);
		cri.setPageNum(5);
		service.getList(cri).forEach(board -> log.info(board));
	}
	
}
