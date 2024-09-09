package com.keduit.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTest {
	
	// 테스트용 게시물 번호
	private Long[] bnoArr = {589866L, 589865L, 589864L, 589863L,589862L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("작성자 " + i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testSelectOne() {
		Long targetRno = 4L;
		
		ReplyVO vo = mapper.selectOne(targetRno);
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		Long targgetRno = 5L;
		
		int result = mapper.delete(targgetRno);
		log.info(result);
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 1L;
		ReplyVO vo = mapper.selectOne(targetRno);
		vo.setReply("update test 댓글...");
		
		log.info("update count : " + mapper.update(vo));
	}
	
	@Test
	public void testSelectAll() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.selectAll(cri, bnoArr[1]);
		replies.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testReplyList() {
		Criteria cri = new Criteria(2, 5);
		
		// 읽을 Bno 지정하기
		List<ReplyVO> replies = mapper.selectAll(cri, 589865L);
		replies.forEach(reply -> log.info(reply));
		}
	
	@Test
	public void testReplyCount() {
		log.info(mapper.getCountByBno(589865L));
	}
	
}
