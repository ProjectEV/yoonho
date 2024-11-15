<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> (관리자) 제품 수정 </title>

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

<script>
   function join() {
      var idReg = /^[a-zA-Z0-9]{4,12}$/;
      var phoneReg = /^(010|011|017|018|019)$/;
      var emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

      var id = document.getElementById("id");
      var pw = document.getElementById("pw");
      var pwch = document.getElementById("pwch");
      var phone1 = document.getElementById("phone1");
      var email = document.getElementById("email");

      if (!idReg.test(id.value)) {
         alert("아이디 형식을 확인하세요");
         return false;
      }

      if (!idReg.test(pw.value)) {
         alert("비밀번호 형식을 확인하세요");
         return false;
      }

      if (pw.value != pwch.value) {
         alert("비밀번호가 일치하는지 확인하세요");
         return false;
      }

      if (!phoneReg.test(phone1.value)) {
         alert("전화번호 앞자리를 확인하세요");
         return false;
      }

      if (!emailReg.test(email.value)) {
         alert("이메일 형식을 확인하세요");
         return false;
      }
   }

   //제이쿼리
   $(document).ready(function() {
      $('#birth').datepicker();

      $('input[type="radio"]').on('click', function() {
         if (this.id !== 'etc') {
            $("#etc_detail").prop("disabled", true);
         } else {
            $("#etc_detail").prop("disabled", false);
         }
      });
   });
</script>

</head>

<body>

   <div>
      <h1>제품 수정</h1>
   </div>

   <form method="post" action="/project/product_update">

      <table>
         <thead>
          
         </thead>

         <tbody>
         	<tr>
               <th>* 제품코드</th>
               <td><input id="product_id" name="product_id" type="text" value="${product.product_id }" readonly /> </td>
            </tr>
         <!-- 이미 존재하는 제품코드면 신규등록을 이용할 수 없게 하는 스크립트 필요 -->
            <tr>
               <th>* 제품명</th>
               <td><input id="product_name" name="product_name" type="text" value="${product.product_name}"  required /> </td>
            </tr>

            <tr>
               <th>* 제품 가격</th>
               <td><input id="product_price" name="product_price" type="text" value="${product.product_price}" required /> </td>
            </tr>

            <tr>
               <th>* 제품 수량</th>
               <td><input id="product_remain" name="product_remain" type="text" value="${product.product_remain}"  readonly /></td>
            </tr>
			
			<tr>
               <th>* 제품 카테고리 </th>
               <td><input id="product_category" name="product_category" type="text" value="${product.product_category }"   required /> </td>
            </tr>
			
            <tr>
               <th>* 제품 설명</th>
               <td><textarea name="product_content" rows="8" value="${product.product_content }" id="product_content"></textarea></td>
            </tr>

            
         </tbody>
      </table>

      <div>
         <button type="reset"> 다시 작성 </button>
         <button type="submit"> 수정완료 </button>
      </div>

   </form>

</body>
</html>