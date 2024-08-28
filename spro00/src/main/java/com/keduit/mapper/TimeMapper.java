package com.keduit.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	// dual = 오라클 안의 더미테이블
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
	
}
