package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.BoardMapper;
import com.keduit.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
//mapper 생성자 주입을 받기 위한 @
@RequiredArgsConstructor
@ToString
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyMapper mapper;

	@Override
	public int register(ReplyVO vo) {
		log.info(".......register" + vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("............get" + rno);
		return mapper.selectOne(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("...............modify" + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info(".............remove" + rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info(".................get Reply List of Board : " + bno);
		return mapper.selectAll(cri, bno);
	}

}
