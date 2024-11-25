<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송지 선택</title>

<link rel="stylesheet" href="https://showcases.yalco.kr/html-css/01-06/table.css">
  <style>
  
   #btn1 {
	  width: 120px;
	  margin: 0 100px;
	  color: rgb(0, 64, 110);
	  background: rgb(199, 232, 255);
	  font-size: 10px;
	  padding: 5px 10px;
	  margin: 0;
	  transition: 0.3s;
	  border-width: thin;
	}
	
	#btn1:hover,
	#btn1:focus:hover {
	  color: white;
	  background:  rgb(0, 64, 110);
	}

</style>

<script>
function inventoryUpdate() {
	// opener.document.getElementById("buy_address").value = document.frm.address.value;
	document.frm.submit();
	window.open("about:blank", "_self").close();
	
}
</script>



</head>
<body>

<h2 style="text-align: center;"> 제품 수량 추가 </h2>

	<div style="">
		<div style="margin: 0 auto; width: 700px;">
		
		
		<!-- 
		<c:forEach var="address" items="${list}">
			 	<div style="margin: 20px 10px; padding: 10px; border: 1px solid rgb(235, 235, 235); ">
					  <input type="radio" name="${address.address_no}" value="${address.address_no}">
					  <label style="font-weight: bold;">${address.address_name}</label><br>
		  			  <label style="margin: 10px 0 0 0; font-size: 13px">${address.address_content}</label><br>
			 	</div>
			</c:forEach>
			
			 	<div style="margin: 20px 10px; padding: 10px; border: 1px solid rgb(235, 235, 235); ">
					  <input type="radio" name="${address.address_no}" value="${address.address_no}">
					  <label style="font-weight: bold;">${address.address_name}주소</label><br>
		  			  <label style="margin: 10px 0 0 0; font-size: 13px">${address.address_content}서울특별시 땡땡구 땡땡동</label><br>
			 	</div>
		 -->
		
		
		
		<form action="/project/productRemainPlus" method="post" name="frm">
		
			 	<div style="margin: 20px 10px; padding: 10px; border: 1px solid rgb(235, 235, 235);">
			 		  <label style="font-weight: bold;">추가할 수량 : </label>
					  <input type="number" name="product_add" value="1" min="0">
		  			  <label style="margin: 10px 0 0 0; font-size: 13px; align: right;">현재 수량 : ${product.product_remain}</label><br>
			 	</div>
		
			
			<button style="margin: 10px 300px;" id="btn1" onclick="inventoryUpdate()"> 추가 </button>
		</form>





			
			
		</div>	
	</div>

</body>
</html>