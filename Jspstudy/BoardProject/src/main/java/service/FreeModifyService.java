package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeModifyService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long freeNo = Long.parseLong(request.getParameter("freeNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Free free = new Free();
		free.setFreeNo(freeNo);
		free.setTitle(title);
		free.setContent(content);
		
		int result = FreeDAO.getInstance().updateFreeBoard(free);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글이 수정되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/detail.do?freeNo=" + freeNo + "'");
			out.println("</script>");
		}
		out.close();
		
		return null;
	}
}
