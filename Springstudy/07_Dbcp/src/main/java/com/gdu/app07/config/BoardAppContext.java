package com.gdu.app07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app07.repository.BoardDAO;
import com.gdu.app07.service.BoardService;
import com.gdu.app07.service.BoardServiceImpl;

@Configuration
public class BoardAppContext {

	// DAO에서 @Repository쓰지않고 따로 Bean을 만들어줌
	// @Repository있어도 돌아가긴함(둘중하나만하면됨)
	// 06_Jdbc의 @Repository 대신 추가한 Bean
	@Bean
	public BoardDAO dao() {
		return new BoardDAO();
	}
	
	// 06_Jdbc의 @Service 대신 추가한 Bean
	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl(dao());
	}
	
	
}
