<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
   <div>
      <h1>${product.product_name }</h1>
      
      <!-- <a href="/project/product_delete"> 제품 삭제 </a>&nbsp; -->
      
      <form action="/project/product_delete" method="GET">
      	<input id="product_id" name="product_id" value="${product.product_id}" type="hidden">
      	<button type="submit"> 제품 삭제 </button>
      </form>
      
      <!-- <a href="/project/product_update"> 제품 수정 </a><br> -->
      
      
      <form action="/project/product_update" method="GET">
      	<input id="product_id" name="product_id" value="${product.product_id}" type="hidden">
      	<button type="submit"> 제품 수정 </button>
      </form>
      
      
      <form action="/project/productRemainPlus" method="POST">
      	<input id="product_plus" name="product_plus" type="text">
      	<input id="product_id" name="product_id" value="${product.product_id}" type="hidden">
      	<button type="submit"> 수량 추가 </button>
      </form>
      
      
   </div>

      <table>
         <tr>
		  	<td>제품코드</td> <td> ${product.product_id}</td><br>
		  	<td>제품명</td> <td> ${product.product_name} </td><br>
		  	<td>가격</td> <td> ${product.product_price} </td><br>
		  	<td>수량</td> <td> ${product.product_remain} </td><br>
		  	<td>제품설명</td> <td> ${product.product_content} </td><br>
		  </tr>
		 
		 
		<tr>
			<td colspan="5" align="center">
					<a href="/project/inventory"> 뒤로가기 </a>
			</td>
		</tr>
      </table>


</body>
</html>