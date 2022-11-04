package com.gdu.app07.service;

import java.util.List;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;


/*
	@Service
	Service에 추가하는 @Component
	servlet-context.xml에 등록된 <context:component-scan> 태그에 의해서 bean으로 검색됨.
	root-context.xml이나 @Configuration에 @Bean으로 등록하지 않아도 컨테이너에 만들어 짐.
*/

/*************************************************/
 // @Service  // Service가 사용하는 @Component
/*************************************************/

// @AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	// Service는 DAO를 사용함
	// @Autowired // 컨테이너에 생성된 bean중에서 BoardDAO 타입의 bean을 가져오시오(DAO에서 @Repository로 맹근거 / repository없으면 여기서 최초오류발생)
	
	/**********************************************************************************************************/
	// @Autowired를 안쓰고 getBean해줌 (비추)
	// 메소드 이름 = bean이름(ID)
	// AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BoardAppContext.class);
	/*********************************************************************************************************/
	private BoardDAO dao;
	
	// 생성자의 매개변수 BoardDAO dao로 new BoardDAO()가 주입되고 있다.
	// BoardAppContext.java를 참고
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	public List<BoardDTO> findAllBoards() {
		/*****************************************/
		// 이거 메소드마다 다 넣어줘야함;
		// dao = ctx.getBean("dao", BoardDAO.class);
		/*****************************************/
		return dao.selectAllBoards();
	}

	@Override
	public BoardDTO findBoardByNo(int board_no) {
		return dao.selectBoardByNo(board_no);
	}

	@Override
	public int saveBoard(BoardDTO board) {
		return dao.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		return dao.updateBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}

}
