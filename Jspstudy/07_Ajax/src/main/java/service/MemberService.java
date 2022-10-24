package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {
	// ajax는 페이지가 안바뀌는 통신(페이지내에서 통신)이기 때문에 forward도 redirect도 안함
	// 때문에 반환타입이 ActionForward가 아닌 void 
	// ajax : 페이지 이동 없는 통신
	// jsp  : 페이지 이동이 있는 통신
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
