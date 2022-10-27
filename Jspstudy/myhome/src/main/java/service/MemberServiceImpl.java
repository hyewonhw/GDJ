package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Member;
import repository.MemberDao;

public class MemberServiceImpl implements MemberService {

	@Override
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member member = Member.builder()
				.id(id)
				.pw(pw)
				.build();
		
		Member login = MemberDao.getInstance().login(member);

		if(login != null) {
			// 성공했을때
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			return new ActionForward("/index.jsp", false);
		} else {
			// 실패했을때
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 실패')");
				out.println("history.back()");
				out.println("</script>");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} 
		
	}

	@Override
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		return new ActionForward(request.getContextPath(), true);

	}

	@Override
	public void register(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Member member = Member.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.email(email)
				.build();
		
		int result = MemberDao.getInstance().insertMember(member);
		
		// dao에서 불러올때 둘다 insert 둘중 하나 실패하면 둘다 뽀록나니까 트랜젝션 필요함
		// 지금경우는 위는 insert 아래는 select이기 때문에 ㄱㅊ
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				// 회원가입하면 로그인 처리
				// 회원가입한 회원의 정보를 DB에서 가져온 뒤 session에 login이라는 이름으로 올리기
				HttpSession session = request.getSession();
				session.setAttribute("login", MemberDao.getInstance().login(member)); // 그냥 멤버로 하면 date가 누락되기때문에 db에서 꺼내오기
				out.println("alert('환영합니다');");
				out.println("location.href='" + request.getContextPath() + "';");
			} else {
				out.println("alert('회원 가입에 실패했습니다.)");
				out.println("histoty.back();");
			}
			out.println("</script>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void cancle(HttpServletRequest request, HttpServletResponse response) {
		// session에 있는 login에 다 들어있기때문에 요청(파라미터)을 받지않아도됨
		// ㄴ> 여기있는건 지워지지 않는 정보임

		// session에 저장된 login 정보에서 탈퇴할 회원의 정보를 추출(memberNo로 삭제할거임)
		HttpSession session = request.getSession();
		Member login = (Member)session.getAttribute("login");   // 반드시 캐스팅
		int memberNo = login.getMemberNo();
		
		int result = MemberDao.getInstance().deleteMember(memberNo);

		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				// 탈퇴 성공하면 session 초기화
				session.invalidate();
				out.println("alert('이용해 주셔서 감사합니다.');");
				out.println("location.href='" + request.getContextPath() + "';");
			} else {
				out.println("alert('회원 탈퇴에 실패했습니다.)");
				out.println("histoty.back();");
			}
			out.println("</script>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
