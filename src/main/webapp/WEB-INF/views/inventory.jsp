<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device=width, initial-scale=1.0">
<link rel="stylesheet" href="https://showcases.yalco.kr/html-css/01-06/table.css">

<script src="https://code.jquery.com/jquery-3.7.1.js"
   integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
   crossorigin="anonymous"></script>
<link rel="stylesheet"
   href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<style>
th, td {
   padding: 5px;
   text-align: left;
}

#title {
   text-align: center;
   background-color: #49516b;
   color: white;
}

div {
   text-align: center;
   margin-top: 20px;
}

table {
   margin: 0 auto;
}
</style>



</head>

<body>

   <div>
      <h1>재고현황</h1>
   </div>

 <table class="table">
 	 <thead>
		  <tr>
		    <td>번호</td>
		  	<td>제품코드</td>
		  	<td>제목</td>
		  	<td>재고</td>
		  	<td>누적판매량</td>
		  </tr>
	</thead>
		  
		<tbody>
		 <c:forEach var="product" items="${list }" varStatus="status">
		 	<tr>
		 		<td>${totalRecord - status.index}</td>
		 		<td><a href="inventory_detail?product_id=${product.product_id}">${product.product_id}</a></td>
		 		<td><a href="product_detail?product_id=${product.product_id}"> ${product.product_name} </a></td>
		 		<td>${product.product_remain}</td>
		 		<td>${product.product_sales}</td>
		 	</tr>
		</c:forEach>
		 
		
		<tr>
			<td colspan="5" align="center">
				<input class="btn btn-success" type="button" value="메인으로"	id="main" />		
			</td>
		</tr>
		</tbody>
	</table>

</body>
</html>