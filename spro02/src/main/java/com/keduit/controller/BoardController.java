package com.keduit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.PageDTO;
import com.keduit.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	/* 페이지네이션 없는 list
	 * @GetMapping("/list") public void list(Model model) {
	 * log.info("--------- list --------- : "); model.addAttribute("list",
	 * service.getList()); }
	 */
	
//	페이지 네이션있는 list
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		log.info("--------- list --------- : ");
		model.addAttribute("list", service.getList(cri));
		int total = service.getTotal(cri);
		log.info("-------------- total : " + total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	//RedirectAttributes : 게시물 등록 후 추가되는 bno에 대한 정보를 전달하기 위해...
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("----------- register ---------" + board);
		
		long bno = service.register(board);
		rttr.addFlashAttribute("result", bno);
		
		// redirect: ===> 스프링MVC가 내부적으로 response.sendRedirect()를 처리
		
		return "redirect:/board/list";
	}
	
	// 두개 이상의 매핑을 할떄는 () 안에 {}를 사용하여 다중 처리
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("------------ /get or /modify --------------");
		model.addAttribute("board", service.get(bno));
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("----------- modify ---------" + board);
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, Criteria cri) {
		log.info("------------- remove -----------");
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
//		return "redirect:/board/list";
		
//		UriComponentsBuilder 를 이용해 위의 코드한줄로 줄인다
		return "redirect:/board/list" + cri.getListLink();
	}
	
}
