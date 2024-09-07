package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.Criteria;
import com.keduit.domain.TodoVO;
import com.keduit.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Service
@RequiredArgsConstructor
@ToString
public class TodoServiceImpl implements TodoService {
	
	private final TodoMapper mapper;

	@Override
	public int register(TodoVO todo) {
		mapper.insert(todo);
		return todo.getTno();
	}

	@Override
	public TodoVO get(int tno) {
		return mapper.read(tno);
	}

	@Override
	public boolean modify(TodoVO todo) {
		return mapper.update(todo) == 1;
	}

	@Override
	public boolean remove(int tno) {
		return mapper.delete(tno) == 1;
	}

	@Override
	public List<TodoVO> getList(Criteria cri) {
		return mapper.getListWithPage(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}

}
