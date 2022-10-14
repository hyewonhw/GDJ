<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 요청 파라미터
	request.setCharacterEncoding("UTF-8");
	String item = request.getParameter("item");
	int amount = Integer.parseInt(request.getParameter("amount"));
	
	// 제품 + 구매수량 -> Map (장바구니에 담을 Map)
	// 맵에 저장할 때 put
	Map<String, Object> product = new HashMap<>();
	product.put("item", item);     
	product.put("amount", amount);
	
	// session에 장바구니를 cart 속성으로 저장한 상황이다. (장바구니는 session에 보관)
	// 1. session에 cart 속성이 있는지 확인한다.
	// 2. cart 속성이 없으면 새로 만들어서 저장한다.
	// List<Map>사용 -> List<Map<String, Object>>으로 캐스팅 
	List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart");  // 기존에 있던카트 (처음 아닐 때)
	if(cart == null) {
		cart = new ArrayList<>();  // 새 카트 (처음 장바구니에 넣을 때)
		session.setAttribute("cart", cart);
	}
	
	// session의 cart에 product 저장하기
	// ArrayList는 add()로 저장
	cart.add(product);
%>

<script>
	alert('<%=product.get("item")%>제품을 장바구니에 추가했습니다.');
	if(confirm('장바구니를 확인하려면 "확인", 계속 쇼핑하려면 "취소"를 누르세요.')) {
		location.href="03_cart_list.jsp";
	} else {
		location.href = "01_form.jsp";
	}
</script>
