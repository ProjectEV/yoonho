<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> mypage </title>
</head>
<body>
<table class="table">
			<tr><td> 주문 / 배송 조회 </td></tr>
		  <tr>
		  	<td>주문날짜</td>
		  	<td>제품명</td>
		  	<td>가격</td>
		  	<td>수량</td>
		  </tr>
		
		 <c:forEach var="buy" items="${list }">
		 	<tr>
		 	<td>${buy.buy_regdate}</td>
		 	<td>${buy.buy_name}</td>
		 	<td>${buy.product_price}</td>
		 	<td>${product.product_category}</td>
		 	</tr>
		</c:forEach>
		
		<tr>
			<td colspan="5" align="center">
				<input class="btn btn-success" type="button" value="메인으로"	id="main" />		
			</td>
		</tr>
</table>
</body>
</html>