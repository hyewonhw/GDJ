package com.gdu.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.contact.domain.ContactDTO;

@Controller
public class ContactController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("cnt/list")
	public String list(Model model) {
		return "contact/list";
	}
	
	
	@GetMapping("cnt/register")
	public String register() {
		return "contact/register";
	}
	
	
	@PostMapping("cnt/add")
	public String add(ContactDTO contact) {
		return "redirect:/cnt/list";
	}
	
	
	
	
}
