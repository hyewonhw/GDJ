package com.gdu.app11.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app11.service.EmpService;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/emp/list")
	public String list(HttpServletRequest request, Model model) {
		empService.findAllEmployees(request, model);
		return "employee/list";  // 폴더이름앞에 / 붙,말 상관없
	}

	@GetMapping("/emp/search")
	public String search(HttpServletRequest request, Model model) {
		empService.findEmployees(request, model);
		return "employee/list";
	}
	
	// ajax 동작 하기 위해 empService.findAutoCompleteList(request)가 jsp가 아니라는걸 @ResponseBody로 알려줌
	@ResponseBody
	// json임을 알려주기위해 produces
	@GetMapping(value="/emp/autoComplete", produces="application/json")
	public Map<String, Object> autoComplete(HttpServletRequest request) {
		return empService.findAutoCompleteList(request);
	}

}
