package com.gdu.app13.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.service.UserService;

public class KeepLoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	// 컨트롤러의 모든 요청 이전에 KeepLoginInterceptor가 개입함
	// 컨트롤러의 모든 요청 이전에 개입한다는 코드를 servlet-context.xml에 작성해둔다
	// bean : servlet-context.xml에 만들어둠 확인하긔
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {  // controller로 가는 request, response 가로채서 처리하는거임
		// preHandle  : 이전에 처리하겠다 (get이던 post던 매핑전에 처리해야하므로)
		// postHandel : 이후에 처리
		
		// 로그인 되어 있지 않은 경우 + 쿠키에 keepLogin이 있는 경우 => 로그인 유지 동작(자동 로그인)
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			
			// 특정쿠키 가져오는게 불가능하지만 spring에서는 가져올 수 있음
			Cookie cookie = WebUtils.getCookie(request, "keepLogin");
			// 있는지 없는지 확인
			if(cookie != null) {
				// 여기서 자동로그인 수행				
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sessionId", cookie.getValue());
				
				UserDTO loginUser = userService.getUserBySessionId(map);
				if(loginUser != null)  {
					session.setAttribute("loginUser", loginUser);
				}
				
			}
			
		}
		
		return true;  // 컨트롤러의 요청을 처리하는 메소드가 수행된다.
		
	}
	
}
