package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeDetailService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> optNo = Optional.ofNullable(request.getParameter("freeNo"));
		long freeNo = Long.parseLong(optNo.orElse("0"));
			
		String referer = request.getHeader("referer");
		HttpSession session = request.getSession();
		if(referer.endsWith("list.do") && session.getAttribute("updateHit") == null) {
			FreeDAO.getInstance().updateHit(freeNo);
			session.setAttribute("updateHit", "done");
		}
		
		Free free = FreeDAO.getInstance().selectFreeBoardByNo(freeNo);
		
		 if(free == null) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('조회 결과가 없습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;

		} else {
			request.setAttribute("free", free);
			return new ActionForward("free/detail.jsp", false);
		}

	}
}
