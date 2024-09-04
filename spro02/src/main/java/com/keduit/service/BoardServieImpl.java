package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
@ToString
public class BoardServieImpl implements BoardService {
	
	private final BoardMapper mapper;

	@Override
	public Long register(BoardVO board) {
		log.info("------- regiseter : " + board);
		mapper.insertSelectKey(board);
		return board.getBno();
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("------------- get ---------------");
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("-------- modify ---------");
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(long bno) {
		log.info("---------- remove ----------");
		int result = mapper.delete(bno);
		return result == 1;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("-------- getList : ");
		
		return mapper.getList();
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("------------ getList With Creteria : " + cri);
		return mapper.getListWithPage(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("--------------- get Total count : " + cri);
		return mapper.getTotal(cri);
	}

}
