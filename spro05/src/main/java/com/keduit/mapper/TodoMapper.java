package com.keduit.mapper;

import java.util.List;

import com.keduit.domain.Criteria;
import com.keduit.domain.TodoVO;

public interface TodoMapper {
	
	public List<TodoVO> getListWithPage(Criteria cri);
	
	public void insert(TodoVO todo);
	
	public TodoVO read(int tno);
	
	public int delete(int tno);
	
	public int update(TodoVO todo);
	
	public int getTotal(Criteria cri);
	
}
