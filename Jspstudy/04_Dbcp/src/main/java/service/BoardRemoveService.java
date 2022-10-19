package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardRemoveService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));  // String을 감싸는 Optional / null값이 올수 있는
		int board_no = Integer.parseInt(opt.orElse("0"));     // null값 이면 0을 꺼냄 -> 0을 Dao로 전달 -> 0인 넘버 없음 -> 삭제안됨 / null아니면 board_no 꺼냄
		
		// DB로 board_no 보내서 삭제
		int result = BoardDao.getInstance().deleteBoard(board_no);
		
		// 삭제 성공/실패 여부 콘솔에 출력
		System.out.println("삭제 여부 : " + result);
		
		// 어디로 / 어떻게
		ActionForward af = new ActionForward();
		af.setView(request.getContextPath() + "/board/list.do");  // Redirect 할 때는 매핑으로 이동
		af.setRedirect(true); 									  // DELETE 이후에는 Redirect
		return af;
	}

}
