package com.keduit.domain;




import java.sql.Date;

import lombok.Data;

@Data
public class TodoVO {
	
	private int tno;
	private String title;
	private Date dueDate;
	private String writer;
	private int finished;
}
