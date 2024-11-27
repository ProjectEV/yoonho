<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자마을</title>

<style>

</style>


</head>
<body>

  <%@ include file="header.jsp" %>

	<table>
         <thead>
            <tr>
               <th id="title" colspan="2"> 임시 홈 화면 </th>
            </tr>

         </thead>

         <tbody>
            <tr>
            	<th> 상품 리스트 </th>
           		<td> <a href="/project/product"> product.jsp </a> </td>
            </tr>

            <tr>
               <th> 로그인 </th>
               <td> <a href="/project/login"> login.jsp </a> </td>
            </tr>

            <tr>
               <th> 마이페이지 </th>
               <td> <a href="/project/mypage"> mypage.jsp </a> </td>
            </tr>

            <tr>
               <th> 회원가입 </th>
               <td> <a href="/project/join"> join.jsp </a> </td>
            </tr>
           
            <tr>
               <th> 재고관리 </th>
               <td> <a href="/project/inventory"> inventory.jsp </a> </td>
            </tr>
           
            <tr>
               <th> 장바구니 </th>
               <td> <a href="/project/cart"> cart.jsp </a> </td>
            </tr>
            
            <tr>
               <th> 공지사항 </th>
               <td> <a href="/project/notice"> notice.jsp </a> </td>
            </tr>
            
            
         </tbody>
      </table>




</body>
</html>