package com.gdu.app13.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;



@PropertySource(value = {"classpath:email.properties"})
@Service
public class UserServiceImpl implements UserService {

	// 이메일을 보내는 사용자 정보
	@Value(value = "${mail.username}")
	private String username;  // 본인 지메일 주소
	
	@Value(value="${mail.password}")
	private String password;  // 발급 받은 앱 비밀번호
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Override
	public Map<String, Object> isReduceId(String id) {
		
		// selectUserByMap 추가 부분
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		// selectUserByMap 수정 부분
		// result.put("isUser", userMapper.selectUserById(id) != null);
		result.put("isUser", userMapper.selectUserByMap(map) != null);  // isUser에 true/false로 판별하기 위해 null이 아니면 true
		
		result.put("isRetireUser", userMapper.selectRetireUserById(id) != null);
		return result;
	}

	@Override
	public Map<String, Object> isReduceEmail(String email) {
		
		// selectUserByMap 추가 부분
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		// selectUserByMap 수정 부분
		// result.put("isUser", userMapper.selectUserByEmail(email) != null);
		result.put("isUser", userMapper.selectUserByMap(map) != null);
		return result;
		
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
		
		// 인증코드 만들기
		//String authCode = securityUtil.generateRandomString(6);  // 디펜던시(스프링)가 해주는거
		String authCode = securityUtil.getAuthCode(6);  // 직접 만든거(직접작성했음)
		System.out.println("발송된 인증코드 : " + authCode);
		
		// 이메일 전송을 위한 필수 속성을 Properties 객체로 생성
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");  // 구글 메일로 보냄(보내는 메일은 구글 메일만 가능)
		properties.put("mail.smtp.port", "587");             // 구글 메일로 보내는 포트 번호
		properties.put("mail.smtp.auth", "true");            // 인증된 메일
		properties.put("mail.smtp.starttls.enable", "true"); // TLS 허용
		
		/*
			이메일 보내기 API 사용을 위한 사전 작업
			
			1. 구글 로그인
			2. Google 계정 - 보안
			    1) [2단계 인증]  - [사용]
			    2) [앱 비밀번호]
			        (1) 앱 선택 : 기타
			        (2) 기기 선택 : Windows 컴퓨터
			        (3) 생성 버튼 : 16자리 앱 비밀번호를 생성해 줌(이 비밀번호를 이메일 보낼 때 사용)
		 */
		
		// 사용자 정보를 javax.mail.Session에 저장
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		// 이메일 작성 및 전송
		try {
			
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(username, "인증코드관리자"));            // 보내는사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));  // 받는사람
			message.setSubject("[Application] 인증 요청 메일입니다.");					 // 제목
			message.setContent("인증번호는 <strong>" + authCode + "</strong> 입니다.", "text/html; charset=UTF-8"); // 내용
			
			Transport.send(message);  // 이메일 전송
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// join.jsp로 반환할 데이터 (ajax라서 반환 필요)
		// 생성한 인증코드를 보내줘야 함
		// 그래야 사용자가 입력한 인증코드와 비교를 할 수 있음
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("authCode", authCode);
		return result;
	}
	
	@Transactional // INSERT,UPDATE,DELETE 중 2개 이상이 호출되는 서비스에 필요함
	@Override
	public void join(HttpServletRequest request, HttpServletResponse response) {
			
		// 파라미터
		// join.jsp에서 name속성 참고해서 받아오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthdate = request.getParameter("birthdate");
		String postcode = request.getParameter("postcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String promotion = request.getParameter("promotion");
		
		// 일부 파라미터는 DB에 넣을 수 있도록 가공
		// pw암호화
		pw = securityUtil.sha256(pw);  
		
		// 정규식 안하고 공백검사만해서<script>공격 받을 수 있기 때문에 막아줘야함
		name = securityUtil.preventXSS(name);
		
		// DB에 BIRTHDAY는 MONTH + DATE이기 때문에 재구성해줘야함
		String birthday = birthmonth + birthdate;
		
		// 상세주소 암호화
		detailAddress = securityUtil.preventXSS(detailAddress);
		
		// 동의 여부 0,1,2,3으로 넣어줘야함
		int agreeCode = 0; // 필수 동의
		if(!location.isEmpty() && promotion.isEmpty()) {
			agreeCode = 1; // 필수 + 위치
		} else if(location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 2; // 필수 + 프로모션
		} else if(!location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 3; // 필수 + 위치 + 프로모션
		}
	
		// DB로 보낼 UserDTO 만들기
		UserDTO user = UserDTO.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.gender(gender)
				.email(email)
				.mobile(mobile)
				.birthyear(birthyear)
				.birthday(birthday)
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.extraAddress(extraAddress)
				.agreeCode(agreeCode)
				.build();
				
		// 회원가입처리
		int result = userMapper.insertUser(user);
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				
				// selectUserByMap 추가 부분
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				
				// selectUserByMap 수정 부분
				// request.getSession().setAttribute("loginUser", userMapper.selectUserById(id));
				
				// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠
				request.getSession().setAttribute("loginUser", userMapper.selectUserByMap(map));  // selectUserById실행함
				
				// 로그인 기록 남기기
				int updateResult = userMapper.updateAccessLog(id);
				if(updateResult == 0) {
					userMapper.insertAccessLog(id);
				}
				
				out.println("<script>");
				out.println("alert('회원 가입되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 가입에 실패했습니다.');");
				out.println("history.go(-2)");  // 두칸 전으로 보냄 history.back은 -1이랑 같음
				out.println("</script>");
			}
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Transactional	// INSERT,UPDATE,DELETE 중 2개 이상이 호출되는 서비스에 필요함
	@Override
	public void retire(HttpServletRequest request, HttpServletResponse response) {
		
		// 탈퇴할 회원의 userNo, id, joinDate는 session의 loginUser에 저장되어 있다.
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		// 탈퇴할 회원 RetireUserDTO 생성
		RetireUserDTO retireUser = RetireUserDTO.builder()
				.userNo(loginUser.getUserNo())
				.id(loginUser.getId())
				.joinDate(loginUser.getJoinDate())
				.build();
		
		// 탈퇴처리
		int deleteResult = userMapper.deleteUser(loginUser.getUserNo());
		int insertResult = userMapper.insertRetireUser(retireUser);
		
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(deleteResult > 0 && insertResult > 0) {
				
				// session에 올려둔 로그인된 사용자 정보를 삭제해야함
				// session 초기화(로그인 사용자 loginUser 삭제를 위해서)
				session.invalidate();
				
				out.println("<script>");
				out.println("alert('회원 탈퇴되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 탈퇴에 실패했습니다.');");
				out.println("history.back();");  // history.back은 -1이랑 같음
				out.println("</script>");
			}
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {
	// 스스로 이동할 수 있는 코드가(redirect) 있기 때문에 void
		
		// 파라미터
		String url = request.getParameter("url");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// pw는 DB에 저장된 데이터와 동일한 형태로 가공
		pw = securityUtil.sha256(pw);
		
		// DB로 보낼 UserDTO 생성 (Map으로 대체된 부분)
				// UserDTO user = UserDTO.builder()
				// 		.id(id)
				//		.pw(pw)
				//		.build();
		
		// selectUserByMap 추가 부분
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		// id, pw가 일치하는 회원을 DB에서 조회하기
		UserDTO loginUser = userMapper.selectUserByMap(map);
		
		// id, pw가 일치하는 회원이 있다 : 로그인 기록 남기기 + session에 loginUser 저장하기
		if(loginUser != null) {
			
			// 로그인 유지 처리는 keepLogin 메소드가 따로 처리함
			// 로그아웃을 누르면 로그인 유지 풀려야함
			keepLogin(request, response);
			
			// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠
			request.getSession().setAttribute("loginUser", loginUser);
			
			// 로그인 기록 남기기
			int updateResult = userMapper.updateAccessLog(id);
			if(updateResult == 0) {
				userMapper.insertAccessLog(id);
			}
			
			// 이동 (로그인페이지 이전 페이지로 되돌아가기) redirect
			try {
				response.sendRedirect(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// id, pw가 일치하는 회원이 없다 : 로그인 페이지로 돌려 보내기
		else {
			
			// 응답
			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('일치하는 회원 정보가 없습니다.');");
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
				out.close();
	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
		
	
	@Override
	public void keepLogin(HttpServletRequest request, HttpServletResponse response) {
		
		/*
			로그인 유지를 체크한 경우
			
			1. session_id를 쿠키에 저장해 둔다.
				(쿠키명 : keepLogin)
			2. session_id를 DB에 저장해 둔다.
				(SESSION_ID에 칼럼에 session_id를 저장하고, SESSION_LIMIT_DATE 칼럼에 15일 후 날짜를 저장한다.)	
		*/
	
		/*
			로그인 유지를 체크하지 않은 경우
			
			1. 쿠키 또는 DB에 저장된 정보를 삭제한다.
			   편의상 쿠키명 keepLogin을 제거하는 것으로 처리한다.
		*/
		
		// 파라미터
		String id = request.getParameter("id");
		String keepLogin = request.getParameter("keepLogin");
		
		// 로그인 유지를 체크한 경우
		if(keepLogin != null) {
			
			// session_id
			String sessionId = request.getSession().getId();
			
			// session_id를 쿠키에 저장하기
			Cookie cookie = new Cookie("keepLogin", request.getSession().getId());
			cookie.setMaxAge(60 * 60 * 24 * 15); // 60초 * 60분 * 24시간 * 15
			cookie.setPath(request.getContextPath());  // contextPath경로에 저장해두기
			response.addCookie(cookie);  // 클라이언트에게 보내기(응답)
			
			// session_id를 DB에 저장하기
			UserDTO user = UserDTO.builder()
					.id(id)
					.sessionId(sessionId)
					.sessionLimitDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))  // 현재타임스탬프 + 15일에 해당하는 타임스탬프(밀리초로 나타내기위해 *1000)
					.build();
			
			// 저장
			userMapper.updateSessionInfo(user);
			
		}
		
		// 로그인 유지를 체크하지 않은 경우
		else {
			
			// keepLogin 쿠키 제거하기
			Cookie cookie = new Cookie("keepLogin", "");
			cookie.setMaxAge(0);  // 쿠키 유지 시간이 0이면 삭제를 의미함
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);  // 기존의 쿠키에 덮어쓰기되면서 삭제됨
			
		}
		
	}
	
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		
		// 로그아웃 처리 (=세션 초기화) 
		// request.getSession().invalidate();
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			session.invalidate();			
		}
		
		// 로그인 유지 풀기
		Cookie cookie = new Cookie("keepLogin", "");
		cookie.setMaxAge(0);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
	}
	
	
	@Override
	public UserDTO getUserBySessionId(Map<String, Object> map) {
		return userMapper.selectUserByMap(map);
	}
	
	
	@Override
	public Map<String, Object> confirmPassword(HttpServletRequest request) {
		
		// 파라미터 pw + SHA-256 처리
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		// id
		HttpSession session = request.getSession();
		String id = ((UserDTO)session.getAttribute("loginUser")).getId();
		
		// 조회 조건으로 사용할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		// id, pw 일치하는 회원 조회
		UserDTO user = userMapper.selectUserByMap(map);
		
		// 결과 반환
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", user != null);  // isUser가 true(=유저가 있음)
		return result;
	}
	
	
	@Override
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response) {
		
		// 현재 로그인 된 사용자 
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		// 파라미터
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		// 동일한 비밀번호로 변경 금지
		if(pw.equals(loginUser.getPw())) {
			try {
				
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("alert('현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
		}
		
		// 사용자 번호
		int userNo = loginUser.getUserNo();
		
		// DB로 보낼 UserDTO
		UserDTO user = UserDTO.builder()
				.userNo(userNo)
				.pw(pw)
				.build();
		
		// 비밀번호 수정
		int result = userMapper.updateUserPassword(user);
		
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				
				// session에 저장된 loginUser 업데이트
				loginUser.setPw(pw);
				
				out.println("<script>");
				out.println("alert('비밀번호가 수정되었습니다');");
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
				
			} else {
				
				out.println("<script>");
				out.println("alert('비밀번호가 수정되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
				
			}
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Transactional  // insert와 delete가 동시에 진행되기때문에 필요
	@Override
	public void sleepUserHandle() {
		int insertCount = userMapper.insertSleepUser();
		if(insertCount > 0) {
			userMapper.deleteUserForSleep();
		}
		
	}
	
	
	@Override
	public SleepUserDTO getSleepUserById(String id) {
		return userMapper.selectSleepUserById(id);
	}
	
	
	@Transactional
	@Override
	public void restoreUser(HttpServletRequest request, HttpServletResponse response) {
		
		// 계정 복원을 원하는 사용자의 아이디
		HttpSession session = request.getSession();
		SleepUserDTO sleepUser = (SleepUserDTO)session.getAttribute("sleepUser");
		String id = sleepUser.getId();
		
		// pw가 일치하는지 확인
		int insertCount = userMapper.insertRestoreUser(id);
		int deleteCount = 0;
		if(insertCount > 0) {
			deleteCount = userMapper.deleteSleepUser(id);
		}
		
		// 응답
		try {

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(insertCount > 0 && deleteCount > 0) {
				out.println("<script>");
				out.println("alert('휴면 계정이 복구되었습니다. 휴면 계정 활성화를 위해 곧바로 로그인을 해 주세요.');");
				out.println("location.href='" + request.getContextPath() + "/user/login/form';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('휴면 계정이 복구되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
	
	@Override
	public String getNaverLoginApiURL(HttpServletRequest request) {
		
		String apiURL = null;
		
		try {
			String clientId = "tq2feeDwAFErLbNnPmUu";
		    String redirectURI = URLEncoder.encode("http://localhost:9090/" + request.getContextPath() + "/user/naver/login", "UTF-8");
		    SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		    apiURL += "&client_id=" + clientId;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&state=" + state;
		    HttpSession session = request.getSession();
		    session.setAttribute("state", state);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return apiURL;
	}	
	
	
	@Override
	public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request) {
		
		// access_token 받기
		
		String clientId = "tq2feeDwAFErLbNnPmUu";
	    String clientSecret = "w1FLigKs5G";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    
	    String redirectURI = null;
	    try {
	    	redirectURI = URLEncoder.encode("http://localhost:9090" + request.getContextPath() , "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
		}
	    
	    String access_token = "";
	    
	    StringBuffer res = new StringBuffer();  // StringBuffer는 StringBuilder과 동일한 역할 수행
	   
	    try {
	    	
	    	String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    
	    	URL url = new URL(apiURL);
	    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
	    	con.setRequestMethod("GET");
	    	int responseCode = con.getResponseCode();
	    	BufferedReader br;
	    	if(responseCode==200) { // 정상 호출
	    		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    	} else {  // 에러 발생
	    		br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	    	}
	    	String inputLine;
	    	while ((inputLine = br.readLine()) != null) {
	    		res.append(inputLine);
	    	}
	    	br.close();
	    	con.disconnect();
	    	/***********************************************************************여기까지 토큰받기****/
    	} catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    JSONObject obj = new JSONObject(res.toString());
	    access_token = obj.getString("access_token");
	    
	    /************************************************************************토큰받아옴*********/
	    
	    // 프로필 받아오기
	    String header = "Bearer " + access_token;
	    	
	    StringBuffer sb = new StringBuffer();
	    	
	    try {
				
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();
			con.disconnect();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// 받아온 profile을 UserDTO로 만들어서 반환
			
		return null;
	}
		
	
	
}
