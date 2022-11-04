package com.gdu.app07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.service.BoardService;

import lombok.AllArgsConstructor;

/**************************************************************************
	필드를 이용한 생성자를 만들어 두면, 
	생성자의 매개변수로 컨테이너의 Bean이 자동 주입(@Autowired)되므로 
	필드에 @Autowired처리할 필요 없음
***************************************************************************/ 
@AllArgsConstructor  // @Autowired없이 생성자 통해서 Bean 가져오기


@Controller
public class BoardCotroller {

	// Controller는 Service를 사용함
	// @Autowired  // 컨테이너에 생성된 bean중에서 BoardService 타입의 bean을 가져오시오.
	private BoardService boardService;  
	
	/**********************************************************
	// @Autowired 없이 생성자를 통해 Bean가져오기
	// @AllargsContsructor쓰면 똑같이 생성자써서 가져옴
	// 직접 생성자 작성 안해도됨
	public BoardCotroller(BoardService boardService) {
		super();
		this.boardService = boardService;
	 }
	**********************************************************/

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("brd/list")
	public String list(Model model) {  // Model은 forward할 속성(Attribute)을 저장할 떄 사용한다.
		model.addAttribute("boards", boardService.findAllBoards());
		return "board/list";  // board 폴더의 list.jsp로 forward
	}
	
	
	@GetMapping("brd/write")
	public String write() {
		return "board/write"; // board폴더의 write.jsp로 forward
	}
	
	
	@PostMapping("brd/add")
	public String add(BoardDTO board) { // serviceimpl(saveBoard)에 bean반환해야하니까 편하게 bean으로받음(관계확인하고 정하기)
		boardService.saveBoard(board);  // seveBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list";  // 삽입 후 목록보기로 이동
	}
	
	
	// defaultValue: null일수도 있, null이면 0으로 처리
	@GetMapping("brd/detail")
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0")int board_no
				  	   , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));
		return "board/detail";
	}
	
	
	@PostMapping("brd/edit")
	public String edit(int board_no, Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));
		return "board/edit";  // board폴더의 edit.jsp로 forward
		
	}
	
	
	@PostMapping("brd/modify")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);  // modifyBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/detail?board_no=" + board.getBoard_no();
	}
	
	
	@PostMapping("brd/remove")
	public String remove(int board_no) {
		boardService.removeBoard(board_no);  // removeBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list";
	}
	
	
}
