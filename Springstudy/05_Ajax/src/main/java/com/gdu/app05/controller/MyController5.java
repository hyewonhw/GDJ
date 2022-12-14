package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.GalleryService;

@Controller
public class MyController5 {

	@GetMapping("gallery") // 반환값이 비어있으(void)면 mapping을 뷰(JSP)로 인식함
	public void gallery() {
		
	}
	
	
	@Autowired
	private GalleryService galleryService;
	
	
	@ResponseBody
	@GetMapping("image/display")
	public ResponseEntity<byte[]> display(@RequestParam String path, @RequestParam String filename) {
		return galleryService.imageDisplay(path, filename);
	}
	
}
