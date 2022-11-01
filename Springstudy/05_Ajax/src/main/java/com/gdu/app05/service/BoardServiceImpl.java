package com.gdu.app05.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.gdu.app05.domain.Board;

public class BoardServiceImpl implements BoardService {

	/*
		ResponseEntity<T> 클래스
		Ajax 응답 데이터를 만들기 위한 전용 클래스
	
		new ResponseEntity<T>(T body, HttpHeaders header, HttpStatus status)
		1) T body 			  : 실제 응답 데이터
		2) HttpHeaders header : 응답 헤더
		3) HttpStatus status  : 응답 코드(200, 404, 500  등)
	*/
	
	
	@Override
	public ResponseEntity<Board> execute1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board board = null;
		
		ResponseEntity<Board> entity = null;
		if(title.isEmpty()) {
			entity = new ResponseEntity<Board>(board, HttpStatus.INTERNAL_SERVER_ERROR);  // $.ajax()의 error에서 처리
			// 보내는데이터, header, INTERNAL_SERVER_ERROR : 자바코드가 잘못됐을 때 500번 반환 
		} else {
			board = new Board(title, content);
			entity = new ResponseEntity<Board>(board, HttpStatus.OK);  // $.ajax()의 success에서 처리
		}
		
		return entity;
	}

	@Override
	public ResponseEntity<Board> execute2(String title, String content) {
		
		ResponseEntity<Board> entity = null;
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		if(title.isEmpty()) {
			entity = new ResponseEntity<Board>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			entity = new ResponseEntity<Board>(new Board(title, content), header, HttpStatus.OK);
		}
		
		return entity;
	}

	@Override
	public ResponseEntity<Board> execute3(Board board) {
		ResponseEntity<Board> entity = null;
		
	if(board.getTitle().isEmpty()) {
		entity = new ResponseEntity<Board> (HttpStatus.INTERNAL_SERVER_ERROR);
	}else {
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		entity = new ResponseEntity<Board>(board, header, HttpStatus.OK);
	}
		
		return entity;
	}

}
