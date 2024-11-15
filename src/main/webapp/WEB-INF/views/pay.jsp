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
<section class="content container-fluid">
		<div class="box-header">
			<h3 class="box-title">구매화면</h3>
		</div>
	
		<form method="post">
			<div class="box-body">
				<div class="form-group">
					<label>구매자 정보</label>
					<h4>${user.user_name }, ${user.user_phone }</h4>
				</div>
				<div class="form-group">
					<label>받는사람 정보</label>
					<h4>수령인</h4>
					<textarea class="form-control" name="content" rows="1"
						placeholder="내용을 입력하세요" id="content"></textarea><br>
					<h4>주소</h4>
					<table>
					<c:forEach var="address" items="${address }">
		 				<tr>
		 				<td>${address.address_name}</td>
		 				<td>${address.address_content}</td>
		 				</tr>
					</c:forEach>
					</table>
				</div>
	
				<div class="form-group">
					<label>결제 정보</label>
					<h4>${product.product_id }</h4><br>
					<h4>${product.product_price }</h4>
				</div>
				<div>
				  <input type="file" name="file1" id="file1"> 
				</div>
			</div>
	
			<div class="box-footer">
				<button type="submit" class="btn btn-primary">작성완료</button>
			</div>
		</form>
    </section>
</body>
</html>