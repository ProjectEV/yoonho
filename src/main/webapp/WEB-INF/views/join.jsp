<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
      <h1>회원가입</h1>
   </div>

   <form method="post" action="/project/join" onsubmit="return join();"
      name=join_form>

      <table>
         <thead>
            <tr>
               <th id="title" colspan="2">회원 기본 정보</th>
            </tr>

         </thead>

         <tbody>
            <tr>
               <th>아이디</th>
               <td><input id="id" name="id" type="text" required /> * 4-12자의
                  영문 대소문자와 숫자로만 입력</td>

            </tr>

            <tr>
               <th>비밀번호</th>
               <td><input id="pw" name="pw" type="password" required /> *
                  4-12자의 영문 대소문자와 숫자로만 입력</td>
            </tr>

            <tr>
               <th>비밀번호 확인</th>
               <td><input id="pwch" name="pwch" type="password" required /></td>
            </tr>

            <tr>
               <th>이름</th>
               <td><input id="name" name="name" type="text" required /></td>
            </tr>

            <tr>
               <th>전화번호</th>
               <td><select name="phone1" size="1">
                     <option value="010" selected>010</option>
                     <option value="011">011</option>
                     <option value="012">012</option>
                     <option value="013">013</option>
               </select> - <input id="phone2" name="phone2" type="number" required /> - <input
                  id="phone3" name="phone3" type="number" required /></td>
            </tr>

            <tr>
               <th>주소</th>
               <td><input id="address" name="address" type="text" required /></td>
            </tr>

            <tr>
               <th>생일</th>
               <td><input id="birth" name="birth" type="text" required /></td>
            </tr>

            <tr>
               <th>이메일</th>
               <td><input id="email" name="email" type="text" required />@ <select
                  name="email2" size="1">
                     <option value="1" selected>naver.com</option>
                     <option value="2">gmail.com</option>
                     <option value="3">nate.com</option>
                     <option value="4">daum.net</option>
                     <option value="5">yahoo.com</option>
               </select></td>
            </tr>
         </tbody>
      </table>

      <div>
         <input type="submit" value="회원가입" onclick="login_2"> 
         <input type="reset" value="다시작성">
      </div>

   </form>

</body>
</html>