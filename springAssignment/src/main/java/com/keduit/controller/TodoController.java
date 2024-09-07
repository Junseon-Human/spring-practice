package com.keduit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.Criteria;
import com.keduit.domain.PageDTO;
import com.keduit.domain.TodoVO;
import com.keduit.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/todo/*")
@Log4j
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService service;
	
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		log.info("------------ list -------------");
		model.addAttribute("list", service.getList(cri));
		int total = service.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
	}
	
	@GetMapping("/register")
	public void register() {
	}
	
	@PostMapping("/register")
	public String register(TodoVO todo, RedirectAttributes rttr) {
		int tno = service.register(todo);
		rttr.addFlashAttribute("result", tno);
		
		return "redirect:/todo/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("tno") int tno, @ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("todo", service.get(tno));
	}
	
	@PostMapping("/modify")
	public String modify(TodoVO todo, Criteria cri, RedirectAttributes rttr) {
		if (service.modify(todo)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/todo/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("tno") int tno, RedirectAttributes rttr, Criteria cri) {
		if (service.remove(tno)) {
			rttr.addFlashAttribute("result", "success!");
		}
		return "redirect:/todo/list" + cri.getListLink();
	}
	
}
