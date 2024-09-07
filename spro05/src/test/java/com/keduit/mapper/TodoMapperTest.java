package com.keduit.mapper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;
import com.keduit.domain.TodoVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TodoMapperTest {
	@Setter(onMethod_ = @Autowired)
	private TodoMapper mapper;
	
	@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		mapper.getListWithPage(cri).forEach(todo -> log.info(todo));
	}
	
	@Test
	public void testInsert() {
		TodoVO vo = new TodoVO();
		String dueDateString = "2024-09-15";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
	        java.util.Date parsedDate = format.parse(dueDateString);
	        vo.setDueDate(new Date(parsedDate.getTime())); // java.sql.Date로 변환
	    } catch (ParseException e) {
	        e.printStackTrace(); // 예외 처리
	    }
		vo.setTitle("test 에서 실행");
		vo.setTitle("test 제목");
		vo.setWriter("test 글쓴이");
		mapper.insert(vo);
	}
	
	@Test
	public void testRead() {
		TodoVO vo = mapper.read(1);
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		mapper.delete(1);
		
	}
	
	@Test
	public void testUpdate() {
		TodoVO vo = new TodoVO();
		String dueDateString = "2024-09-27";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
	        java.util.Date parsedDate = format.parse(dueDateString);
	        vo.setDueDate(new Date(parsedDate.getTime())); // java.sql.Date로 변환
	    } catch (ParseException e) {
	        e.printStackTrace(); // 예외 처리
	    }
		vo.setTno(4);
		vo.setTitle("test에서 수정");
		vo.setFinished(1);
		vo.setWriter("test에서 수정");
		log.info(mapper.update(vo));
		
	}
	
	@Test
	public void testTotalCount() {
		int count = mapper.getTotal(new Criteria());
		log.info("게시글 전체 갯수 : " + count);
	}
	
	
	
}
