package com.keduit.service;

import java.util.List;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardService {
	
	// 게시글 등록하기
	public Long register(BoardVO board);
	
	// 게시글 한건 상세보기
	public BoardVO get(Long bno);
	
	// 수정하기
	public boolean modify(BoardVO board);
	
	// 삭제하기
	public boolean remove(long bno);
	
	// 목록보기
	public List<BoardVO> getList();
	
	// 목록보기(페이지네이션)
	public List<BoardVO> getList(Criteria cri);
	
}
