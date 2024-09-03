package com.keduit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.SampleDTO;
import com.keduit.domain.SampleDTOList;
import com.keduit.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@GetMapping("/{statusCode}")
	public String getErrorPage(@PathVariable String statusCode) {
		log.info("-----------statusCode : " + statusCode);
		return "custom404";
	}
	
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
	// SampleDTO(name=moon, age=300)]
	
	// http://localhost:9090/sample/ex03?title=lalabla&dueDate=2024-08-29
	// 위 주소의 경우 dueDate=값이 날짜인지 알수 없으므로 변환이 필요함
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	// @InitBinder 로 처리 이후에는 /ex03 의 값을 변환하여 잘 받아옴
	// TodoDTO의 필드 확인 @DateTimeFormat이 더 간편하고 많이쓰인다고함
	
	// 날짜 처리 : 자동 바인딩이 되지 않는 파라미터의 변환 예
	@GetMapping("/ex03")
	public void ex03 (TodoDTO dto) {
		log.info("todo : " + dto);
	}
	
	// 파라미터로 전달 되지않은 데이터를 화면으로 보내는 @ModelAttribute
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto : " + dto);
		log.info("page : " + page);
		return "/sample/ex04";
	}
	
	// RedirectAttribute : Model 과 더불어 MVC가 자동으로 전달하는 타입
	// addFlashAttribute(이름, 값) : 화면에서 한번 뿌려주고 소실
	@GetMapping("/ex05")
	public String ex05(RedirectAttributes rttr) {
		rttr.addAttribute("name","java");
		rttr.addFlashAttribute("result", "success");
		return "redirect:/sample/ex10";
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06............");
		SampleDTO dto = new SampleDTO();
		dto.setAge(26);
		dto.setName("문성현");
		return dto;
		
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("ex07..........");
		String msg = "{\"name\" : \"홍길동\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("---------- / exUpload------");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("--------------------------------");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
			log.info("contentType : " + file.getContentType());
		});
	}
	
}
