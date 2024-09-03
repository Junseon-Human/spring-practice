package com.keduit.mapper;

import java.util.List;


import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardMapper {
	
//	@Select("select * from t_board where bno > 0")
//	public List<BoardVO> getList();
	
	// XML과 연동함으로 sql 실행 BoardMapper.xml 확인
	public List<BoardVO> getList();
	
	// 페이지처리 리스트
	public List<BoardVO> getListWithPage(Criteria cri);

	public void insert(BoardVO board);

	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);

}
