package com.keduit.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Restaurant {
	
	@Autowired
//	레스토랑 클래스의 필드에 셰프 클래스 를 추가함
	private Chef chef;
	
}
