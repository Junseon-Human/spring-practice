package com.keduit.service;

import java.util.List;

import com.keduit.domain.Criteria;
import com.keduit.domain.TodoVO;

public interface TodoService {
	
	public int register(TodoVO todo);
	
	public TodoVO get(int tno);
	
	public boolean modify(TodoVO todo);
	
	public boolean remove(int tno);
	
	public List<TodoVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
}
