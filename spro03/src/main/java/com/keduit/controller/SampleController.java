package com.keduit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.SampleVO;
import com.keduit.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	// prroduces: return data type
	// value: 들어오는 경로
	@GetMapping(value="/getText", produces="text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		return  "안녕하세요";
	}
	
	@GetMapping(value="/getSample",
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		return new SampleVO(102, "스타", "로드");
	}
	
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(103, "로켓", "라쿤");
	}
	
	// 컬렉션 타입의 객체 반환
	@GetMapping("/getList")
	public List<SampleVO> getList() {
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + "Last"))
				.collect(Collectors.toList());
	}
	
	// MAP을 리턴
	@GetMapping("/getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("first", new SampleVO(104, "그루트", "주니어"));
		return map;
	}
	
	@GetMapping(value="/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(0, "" + height, "" + weight); 
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	
	// 파라미터 사용하기
	// @PathVariable : URL정보를 파라미터로 사용
	// @PathVariable의 데이터 자료형은 기본 자료형도 넣을 수 있다.
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
		@PathVariable("cat") String cat,
		@PathVariable("pid") Integer pid) {
			return new String[] {"category: " + cat, "prodictID: " + pid};
	}
	
	// @RequestBody : 전달된 요청을 객체 타입으로 변환해 줌
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("Convert.......... ticket.." + ticket);
		return ticket;
	}
	
}
