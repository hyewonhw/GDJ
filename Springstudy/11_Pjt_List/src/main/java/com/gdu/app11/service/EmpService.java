package com.gdu.app11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmpService {
	/*
		Service반환타입List ----> 컨트롤러(model.addAttribute) 
		service가 반환타입이없으면 model.addAttribute()도 스스로함
		request. response, session. model 모두 선언은 controller에서 가능
		아래 request도 controller로부터 받아온거임
	*/
	public void findAllEmployees(HttpServletRequest request, Model model); 
}
