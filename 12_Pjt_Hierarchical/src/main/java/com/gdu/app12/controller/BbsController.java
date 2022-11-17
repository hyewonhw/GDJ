package com.gdu.app12.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app12.service.BbsService;
import com.gdu.app12.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class BbsController {
	
	// 필드 여러개여서 Autowired안쓰고 @AllArgsConstructor로 자동주입함
	private BbsService bbsService;
	private PageUtil pageUtil;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {
		bbsService.findAllBbsList(request, model);
		return "bbs/list";  // bbs폴더 아래 list
	}
	
	@GetMapping("/bbs/write")
	public String write() {
		return "bbs/write";
	}
	
	@PostMapping("/bbs/add")
	public String add(HttpServletRequest request) {
		bbsService.addBbs(request);
		return "redirect:/bbs/list";
	}

	@PostMapping("/bbs/remove")
	public String remove(@RequestParam("bbsNo") int bbsNo) {
		bbsService.removeBbs(bbsNo);
		return "redirect:/bbs/list";
	}
	
}
