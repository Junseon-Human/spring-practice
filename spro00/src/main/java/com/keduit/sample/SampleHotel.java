package com.keduit.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.ToString;

@Component
@ToString
@Getter
public class SampleHotel {
	
	private Chef chef;
	
//	단일 생성자는 객체를 자동으로 주입가능함
	public SampleHotel(Chef chef) {
		this.chef = chef;
	}

}
