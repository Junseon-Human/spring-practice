package com.keduit.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 글 내용");
		board.setWriter("user01");
		
		mapper.insert(board);
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("select key를 이용한 글 작성");
		board.setContent("select key를 이용한 글 내용");
		board.setWriter("user01");
		mapper.insertSelectKey(board);
		log.info(board);
	}
	
	@Test
	public void testRead() {
		BoardVO board = mapper.read(5L);
		log.info(board);
	}
	
	@Test
	public void testDelete() {
		log.info("DELETE COUNT : " + mapper.delete(2L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(1L);
		board.setTitle("수정한 제목");
		board.setContent("수정한 글 내용");
		board.setWriter("수정한 글쓴이");
		log.info("UPDATE CONUT : " + mapper.update(board));
		log.info(mapper.getList());
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		List<BoardVO> list = mapper.getListWithPage(cri);
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testPaging2() {
		Criteria cri = new Criteria();
		cri.setAmount(10);
		cri.setPageNum(5);
		
		List<BoardVO> list = mapper.getListWithPage(cri); 	
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testTotalCount() {
		int count = mapper.getTotal(new Criteria());
		log.info("게시글 전체 갯수 : " + count);
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setType("TC");
		cri.setKeyword("한글");
		List<BoardVO> list = mapper.getListWithPage(cri);
		list.forEach(board -> log.info(board));
	}
	
	
}
