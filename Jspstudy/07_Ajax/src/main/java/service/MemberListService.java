package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDao;

public class MemberListService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 목록달라는 요청은 있지만 파라미터가 없음
		// Dao에서 가져온 목록을 포워딩할수없기때문에 할수있는건 json을 맹글어서 response해줌
		// request는 파라미터있을때만쓰고 없으면 안쓸거임 HttpServletResponse response 만 이용

		// 응답 데이터 형식(JSON)
		// contentType다시 잡아야함 controller의 contentType에 덮어씌움
		response.setContentType("application/json; charset=UTF-8"); 
		
		// 응답 데이터 만들기
		/*
		  {
		  	"count": 3,
		  	"members": [
		  		{
		  			"memberNo": 1,
		  			"id": "user1",
		  			"name": "회원1",
		  			"gender": "F",
		  			"grade": "gold",
		  			"address": "jeju"
		  		},
		  		{
		  			"memberNo": 2,
		  			"id": "user2",
		  			"name": "회원2",
		  			"gender": "M",
		  			"grade": "silver",
		  			"address": "seoul"
		  		},
		  		{
		  			"memberNo": 3,
		  			"id": "user3",
		  			"name": "회원3",
		  			"gender": "F",
		  			"grade": "bronze",
		  			"address": "yeosu"
		  		},
		  	]
		  }
		*/
		JSONObject obj = new JSONObject();
		obj.put("count", MemberDao.getInstance().selectAllMembersCount());  	// Member~Count에서 3이 넘어옴
		obj.put("members", MemberDao.getInstance().selectAllMembers());
		
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());  // JSON 문자열 응답
		out.close();
		
		
		
		
		
		
		
	}

}
