package com.gdu.app07;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.gdu.app07.config.BoardAppContext;
import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

// JUnit4를 사용한다
// 커스터마이징한 MySpringJUnit4ClassRunner를 사용한다.
@RunWith(MySpringJUnit4ClassRunner.class)

// Bean은 BoardAppContext에 정의되어 있다.
@ContextConfiguration(classes= {BoardAppContext.class})

public class BoardUnitTest {

	@Autowired
	private BoardDAO dao;
	
	@Test
	// context.xml은 tomcat이 읽고 junit은 tomcat을 사용하지 않기 때문에 오류가 남
	public void 삽입테스트() {
		BoardDTO board = new BoardDTO(0, "테스트제목", "테스트내용", "테스트작성자", null, null);
		assertEquals(1, dao.insertBoard(board));
	}
	
}
