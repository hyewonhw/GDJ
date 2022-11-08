package com.gdu.app10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app10.domain.BoardDTO;
import com.gdu.app10.service.BoardService;

@Controller
public class BoardController {
	
	
	// Controller는 Service를 사용
	@Autowired	  // 컨테이너에 생성된 bean 중에서 BoardService 타입의 bean을 가져오시오.
	private BoardService boardService; 
	
	// public BoardController(BoardService boardService) {
	// 	super();
	// 	this.boardService = boardService;
	// }


	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("brd/list")
	public String list(Model model) {	// Model은 forward할 속성(Attribute)을 저장할 때 사용한다.
		model.addAttribute("boards", boardService.findAllBoards());
		return "board/list"; 	// board 폴더의 list.jsp로 forward
	}
	
	
	@GetMapping("brd/write")
	public String write() {
		return "board/write";	// board 폴더의 write.jsp로 forward
	}
	
	
	@PostMapping("brd/add")
	public String add(BoardDTO board) {
		boardService.saveBoard(board);	 // saveBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list"; 	// insert 후 redirect(뒤에는 /mapping 값 기입) 처리    // 새글 작성(성공/실패 유무 상관없이) 후, 목록보기(매핑값)으로 이동
	}
	
	
	@GetMapping("brd/detail")
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int boardNo
					   , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(boardNo));
		return "board/detail";	// board 폴더의 detail.jsp로 forward
	}
	
	
	@PostMapping("brd/edit")
	public String edit(int boardNo
			         , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(boardNo));
		return "board/edit";	// board 폴더의 edit.jsp로 forward
	}
	
	
	@PostMapping("brd/modify")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);	 // modifyBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/detail?board_no=" + board.getboardNo();
	}
	
	
	@PostMapping("brd/remove")
	public String remove(int boardNo) {
		boardService.removeBoard(boardNo);	 // removeBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list";
	}

}
