package com.keduit.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
//	@DateTimeFormat(pattern = "yyyy/MM/dd")
//	intibinder 를 귀찮게 사용하지 않아도 dto에서 @DateTimeFormat 으로 패턴을 지정해주면 해결 가능하다
	private Date dueDate;
	
}
