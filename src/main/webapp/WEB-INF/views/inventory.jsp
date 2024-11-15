<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 재고 현황 </title>

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


</script>

</head>

<body>

	<script>
		$(function(){
			//메인 버튼을 눌렀을 때 처리
			$(".btn-success").click(function(){
				location.href="../";
			});
			$(".btn-success").click(function(){
				location.href="../";
			});
		})
	</script>


   <div>
      <h1>재고현황</h1>
   </div>

      <table>
         <tr>
		  	<td>번호</td>
		  	<td>제품코드</td>
		  	<td>제품명</td>
		  	<td>재고</td>
		  	<td>누적판매량</td>
		  </tr>
		  
		 <c:forEach var="product" items="${list }" varStatus="status">
		 	<tr>
		 	<td>${totalRecord - status.index}</td>
		 	<td><a href="inventory_detail?product_id=${product.product_id}">${product.product_id}</a></td>
		 	<td>${product.product_name}</td>
		 	<td>${product.product_remain}</td>
		 	<td>${product.product_sales}</td>
		 	</tr>
		</c:forEach>
		 
		<tr>
			<td colspan="5" align="center">
				<input class="btn btn-success" type="button" value="메인으로"	id="main" />
				<a href="/project/product_register"> 제품 등록 </a>		
			</td>
		</tr>
      </table>


</body>
</html>