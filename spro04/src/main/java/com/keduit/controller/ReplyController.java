package com.keduit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies/")
@Log4j
@AllArgsConstructor
public class ReplyController {

	private ReplyService service;
	
	// consumes : 들어오는 데이터 타입(client->server)
	// produces : 반환하는 ㅔ이터 타입(server->client)
	@PostMapping(value="/new", consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		log.info("ReplyVO : " + vo); 	
		int insertCount = service.register(vo);
		log.info("Reply Insert Count : " + insertCount);
		return insertCount == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	// 특정 게시물의 댓글 목록 가져오기
//	@GetMapping(value="/pages/{bno}/{page}",
//			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//	public ResponseEntity<List<ReplyVO>> getList(
//			@PathVariable("bno") Long bno, @PathVariable("page") int page) {
//		log.info("............ getList ..............");
//		Criteria cri = new Criteria(page, 10);
//		log.info("................. cri : " + cri);
//		
//		return new ResponseEntity<List<ReplyVO>>(service.getList(cri, bno), HttpStatus.OK);
//	}
	
	// 댓글 번호로 읽기
	@GetMapping(value="/{rno}", 
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyVO> get(
			@PathVariable("rno") Long rno) {
		log.info("......... get : " + rno);
		return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	// 댓글 한 건 삭제하기
	@DeleteMapping(value="/{rno}",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("............ remove : " + rno);
		
		return service.remove(rno) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 수정하기
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH}, value="/{rno}",
			consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(
			@PathVariable("rno") Long rno, @RequestBody ReplyVO vo) {
		vo.setRno(rno);
		
		log.info("............ rno : " + rno);
		log.info("................. modify : " + vo);
		
		return service.modify(vo) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 게시물 댓글 목록의 페이징 처리하기
	@GetMapping(value="/pages/{bno}/{page}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyPageDTO> getListWithPaging(
			@PathVariable("bno") Long bno, @PathVariable("page") int page) {
		Criteria cri = new Criteria(page, 5);
		log.info("get reply : " + bno + " & cri : " + cri);
		
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	
	
	
}
