<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <div class="content-wrapper">
<section class="content container-fluid">
		 <div>${msg}</div>
		<div class="table-responsive">
		<table class="table">
		  <tr>
		  	<td>제품코드</td>
		  	<td>제품명</td>
		  	<td>가격</td>
		  	<td>카테고리</td>
		  	<td>재고</td>
		  </tr>
		  
		  
		
		 <c:forEach var="product" items="${list }">
		 	<tr>
		 	<td>${product.product_id}</td>
		 	<td><a href="product_detail?product_id=${product.product_id}"> ${product.product_name} </a></td>
		 	<td>${product.product_price}</td>
		 	<td>${product.product_category}</td>
		 	<td><span class="badge">${product.product_remain }</span></td>
		 	</tr>
		</c:forEach>
		 
		
		<tr>
			<td colspan="5" align="center">
				<input class="btn btn-success" type="button" value="메인으로"	id="main" />		
			</td>
		</tr>
</table>

 		  <c:if test="${pageListNUM>1}">
		  <a href="product?pageListNUM=${pageListNUM-1}&pageNUM=${pageListNUM*10-10}"> 이전 </a>
		  </c:if>
		  	
		  <c:forEach var="i" begin="${startPage }" end="${endPage }">
				<a href="product?pageListNUM=${pageListNUM }&pageNUM=${i }"> ${i } </a>
		  </c:forEach>
		  
		  <c:if test="${pageListNUM<(totalPage/10)}">
		  <a href="product?pageListNUM=${pageListNUM+1}&pageNUM=${pageListNUM*10+1}"> 다음 </a>
		  </c:if>



		</div>
    </section>
</div>
</div>


</body>
</html>