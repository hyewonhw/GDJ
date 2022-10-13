package ex08_binding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/BindingServlet2")


public class BindingServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ServletContext
		ServletContext ctx = getServletContext();
		int a = (int)ctx.getAttribute("a");   // int 캐스팅
		
		// HttpSession
		HttpSession session = request.getSession();
		int b = (int)session.getAttribute("b");
		
		// HttpServletRequest
		Object c = request.getAttribute("c");   // 이동을 누르면 새로운 사이클이기 때문에 c는 null값
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>a=" + a + ",b=" + b + ",c=" + c + "</h1>");
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
