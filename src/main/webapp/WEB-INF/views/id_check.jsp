<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>회원 관리</title>
</head>
<body>
	<h2>아이디 중복확인</h2>
	<form method="post" action="/project/id_check" name="id_checkform">
		아이디 <input type="text" name="user_id" value="${user_id}">
		
		<input type="submit" value="중복 체크">
		<br>
		
		
		<c:if test = "${r == 1}">
			<script type="text/javascript">
			opener.document.join_form.user_id.value="";
			</script>
			${user_id}는 이미 사용중인 아이디 입니다.
		</c:if >
		
		<c:if test = "${r == 0}">
			${user_id}는 사용 가능한 아이디 입니다.
			<button type="button" value="사용" onclick="idok()">사용</button>
		</c:if>		
	</form>
	
	<script type="text/javascript">
	document.id_checkform.user_id.value = user_id;
	//아이디 중복확인 완료한 값 join으로 넘겨주기
	function idok() {
		opener.join_form.user_id.value = document.id_checkform.user_id.value;
		opener.join_form.reid.value = document.id_checkform.user_id.value;
		self.close();
	}
	</script>
	



</body>
</html>