package com.keduit.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keduit.domain.SampleDTO;
import com.keduit.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("............basic................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("..........basic get only get .................");
	}
	
	// 스프링 4.03 이전
	// 현재 스프링 5버전 에서는 Get, Post 방식을 쓸때는 각각 GetMapping, PostMapping 으로 쓰면되고 
	// 두개 다 쓰려면 RequestMapping 으로 쓰면됨 스프링 이전 버전 방식은 몰라도 됨
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("..............basic get ...............");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("--------------" + dto);
		log.info(dto.getName());
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		return "ex02";
	}
	
	// 리스트, 배열 처리
	
	@GetMapping("/ex02List")
	public String ex02List (@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids : " + ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public void ex02Array (@RequestParam("ids") String[] ids) {
		log.info("array ids : " + Arrays.toString(ids));
	}
	
	// 객체 리스트 처리
	
	@GetMapping("/ex02Bean")
	public void ex02Bean (SampleDTOList list) {
		log.info("list dtos : " + list);
	}
	// 주소창에 다음과 같이 입력 list[0] 대신 list%5B0%5D 로 적음
	// http://localhost:9090/sample/ex02Bean?list%5B0%5D.name=hong&list%5B1%5D.name=moon
	// &list%5B0%5D.age=25&list%5B1%5D.age=300
	// 결과 : list dtos : SampleDTOList(list=[SampleDTO(name=hong, age=25),
	//                  SampleDTO(name=moon, age=300)]
	
}
