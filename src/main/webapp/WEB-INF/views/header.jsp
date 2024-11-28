<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <title>TechNova</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
    rel="stylesheet">
    
    <!-- Css Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
       $(document).ready(function() {
           $.ajax({
               url: '/project/cartRecord',
               method: 'GET',
               success: function(count) {
                   $('#cartItemCount').text(count);
               },
               error: function() {
                   $('#cartItemCount').text('0'); // 오류 시 0으로 표시
               }
           });
       });
   </script>


</head>

<body>

   <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
        <ul class="offcanvas__widget">
            <li><span class="icon_search search-switch"></span></li>
            <li><a href="#"><span class="icon_heart_alt"></span>
                <div class="tip">2</div>
            </a></li>
            <li><a href="#"><span class="icon_bag_alt"></span>
                <div class="tip">2</div>
            </a></li>
        </ul>
        <div class="offcanvas__logo">
            <a href="./index.html"><img src="${pageContext.request.contextPath}/resources/img/logo.png" alt=""></a>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__auth">
            <a href="#">Login</a>
            <a href="#">Register</a>
        </div>
    </div>
    <!-- Offcanvas Menu End -->
    
   <!-- Header Section Begin -->
   <header class="header">
       <div class="container-fluid">
           <div class="row">
           
              <div class="col-sm-2">
                   <nav class="header__menu" style="height: 88px;">             
                      <ul>
                           <li><a href="/"><img style="width: 25px; height: 25px; margin: 0 0 0 20px" src="${pageContext.request.contextPath}/resources/images/header_category.png" alt=""></a>
                              <ul class="dropdown" style="width: 1400px; height: 400px; margin: 33px 0 0 0; padding: 30px;">
                           
                           <ul style="display: inline-block; margin: 0 40px 0 10px;">
                              <li style="width: 150px; font-size: 20px; color: white; border-bottom: 1px solid var(--bs-gray-300); padding: 0 20px 10px 0; maring: 0 0 10px 0;">
                                 <img style="width: 25px; height: 25px;" src="${pageContext.request.contextPath}/resources/images/nav_pc.png" alt="">
                                 컴퓨터
                              </li>
                              <li><a href="/" style="padding: 8px 0;">저장장치</a></li>
                                      <li><a href="/" style="padding: 8px 0;">그래픽카드</a></li>
                                      <li><a href="/" style="padding: 8px 0;">CPU</a></li>
                                      <li><a href="/" style="padding: 8px 0;">메인보드</a></li>
                           </ul>
                           <ul style="display: inline-block; margin: 0 40px 0 0;">
                              <li style="width: 150px; font-size: 20px; color: white; border-bottom: 1px solid var(--bs-gray-300); padding: 0 20px 10px 0; maring: 0 0 10px 0;">
                                 <img style="width: 25px; height: 25px; " src="${pageContext.request.contextPath}/resources/images/nav_note.png" alt="">
                                 노트북
                              </li>
                              <li><a href="/" style="padding: 8px 0;">저장장치</a></li>
                                      <li><a href="/" style="padding: 8px 0;">그래픽카드</a></li>
                                      <li><a href="/" style="padding: 8px 0;">CPU</a></li>
                                      <li><a href="/" style="padding: 8px 0;">메인보드</a></li>
                           </ul>
                           <ul style="display: inline-block; margin: 0 40px 0 0;">
                              <li style=" width: 150px; font-size: 20px; color: white; border-bottom: 1px solid var(--bs-gray-300); padding: 0 20px 10px 0; maring: 0 0 10px 0;">
                                 <img style="width: 25px; height: 25px;" src="${pageContext.request.contextPath}/resources/images/nav_phone.png" alt="">
                                 핸드폰
                              </li>
                              <li><a href="/" style="padding: 8px 0;">저장장치</a></li>
                                      <li><a href="/" style="padding: 8px 0;">그래픽카드</a></li>
                                      <li><a href="/" style="padding: 8px 0;">CPU</a></li>
                                      <li><a href="/" style="padding: 8px 0;">메인보드</a></li>
                           </ul>                           
                           <ul style="display: inline-block; margin: 0 40px 0 0;">
                              <li style=" width: 150px; font-size: 20px; color: white; border-bottom: 1px solid var(--bs-gray-300); padding: 0 20px 10px 0; maring: 0 0 10px 0;">
                                 <img style="width: 25px; height: 25px;" src="${pageContext.request.contextPath}/resources/images/nav_tablet.png" alt="">
                                 태블릿
                              </li>
                              <li><a href="/" style="padding: 8px 0;">저장장치</a></li>
                                      <li><a href="/" style="padding: 8px 0;">그래픽카드</a></li>
                                      <li><a href="/" style="padding: 8px 0;">CPU</a></li>
                                      <li><a href="/" style="padding: 8px 0;">메인보드</a></li>
                           </ul>
                           <ul style="display: inline-block; margin: 0 40px 0 0;">
                              <li style="width: 150px; font-size: 20px; color: white; border-bottom: 1px solid var(--bs-gray-300); padding: 0 20px 10px 0; maring: 0 0 10px 0;">
                                 <img style="width: 25px; height: 25px;" src="${pageContext.request.contextPath}/resources/images/nav_cpu.png" alt="">
                                 컴퓨터 부품
                              </li>
                              <li><a href="/" style="padding: 8px 0;">저장장치</a></li>
                                      <li><a href="/" style="padding: 8px 0;">그래픽카드</a></li>
                                      <li><a href="/" style="padding: 8px 0;">CPU</a></li>
                                      <li><a href="/" style="padding: 8px 0;">메인보드</a></li>
                           </ul>                           
                           <ul style="display: inline-block; margin: 0 40px 0 0;">
                              <li style="width: 150px; font-size: 20px; color: white; border-bottom: 1px solid var(--bs-gray-300); padding: 0 20px 10px 0; maring: 0 0 10px 0;">
                                 <img style="width: 25px; height: 25px;" src="${pageContext.request.contextPath}/resources/images/nav_key.png" alt="">
                                 키보드
                              </li>
                              <li><a href="/" style="padding: 8px 0;">저장장치</a></li>
                                      <li><a href="/" style="padding: 8px 0;">그래픽카드</a></li>
                                      <li><a href="/" style="padding: 8px 0;">CPU</a></li>
                                      <li><a href="/" style="padding: 8px 0;">메인보드</a></li>
                           </ul>
                           <ul style="display: inline-block; margin: 0 0 0 0;">
                              <li style="width: 150px; font-size: 20px; color: white; border-bottom: 1px solid var(--bs-gray-300); padding: 0 20px 10px 0; maring: 0 0 10px 0;">
                                 <img style="width: 25px; height: 25px;" src="${pageContext.request.contextPath}/resources/images/nav_mouse.png" alt="">
                                 마우스
                              </li>
                              <li><a href="/" style="padding: 8px 0;">저장장치</a></li>
                                      <li><a href="/" style="padding: 8px 0;">그래픽카드</a></li>
                                      <li><a href="/" style="padding: 8px 0;">CPU</a></li>
                                      <li><a href="/" style="padding: 8px 0;">메인보드</a></li>
                           </ul>                                                      
                               </ul>
                           </li>
                        </ul>
                    </nav>
               </div>
               
               <div class="col-sm-2">
                   <div class="header__logo">
                       <a href="/"><img style="width: 200px; height: 30px; margin: 0 0 0 30px" src="${pageContext.request.contextPath}/resources/images/logo4.JPG" alt=""></a>
                   </div>
               </div>

               
               <div class="col-sm-4" style="padding: 17px 0 0 0; height: 90px;">
                  <div class="footer__newslatter">
                       <form action="#">
                        <input type="text" id="search-input" placeholder="검색어를 입력하세요." onkeypress="show_name(event)">
                           <button style="background: #000000;" class="site-btn" onclick="show_name(event)"><img style="height: 20px; width: 20px;    background: #000000;" src="${pageContext.request.contextPath}/resources/images/search_white.png" alt=""></button>
                       </form>
                   </div>       
               </div>
               
                
               <div class="col-sm-3" style="padding: 0 10px 0 0; margin: 0 0 0 125px;">
                   <div class="header__right">
                       <div class="header__right__auth">
                             <c:if test="${user==null }">
                           로그인이 필요합니다. <a style="padding: 0 0 0 10px;" href="/project/login">Login</a>
                        </c:if>
                        <c:if test="${user!=null }">
                           ${user.user_name }님 환영합니다. <a style="padding: 0 0 0 10px;" href="/project/logout">Logout</a>
                        </c:if>
                           
                           <a href="/project/join">Register</a>
                       </div>
                       <ul class="header__right__widget">
                           <li><a href="/project/mypage"><img style="width: 18px; height: 18px; padding: 0 0 2px 0;" src="${pageContext.request.contextPath}/resources/images/mypage.png" alt=""></span>
                           </a></li>
                           <li><a href="/project/cart"><span class="icon_bag_alt"></span>
                               <div class="tip" id="cartItemCount"></div>
                           </a></li>
                       </ul>
                   </div>
               </div>
           </div>
           <div class="canvas__open">
               <i class="fa fa-bars"></i>
           </div>
       </div>
   </header>
   <!-- Header Section End -->
   
   <script type="text/javascript">
        function show_name(e){
           e.preventDefault();
            var keyword = document.getElementById("search-input").value;
           const code = e.code;
           location.href = "/product/list?keyword="+keyword;
        }
    </script>
    
    </body>
</html>
    