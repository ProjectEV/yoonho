<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>

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
    
    
<script>
	function windowOpen() { 
		var url = "/project/address_select"
		window.open(url, "a", "width=1000, height=800, left=100, top=50"); 
		}
</script>  

<style>
	.top__text__center {
        font-size: 16px;
        color: $heading-color;
        font-weight: 600;
        float: center;
      }
</style>
    
</head>

<body>
    

    <%@ include file="header.jsp" %>

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/"><i class="fa fa-home"></i> 홈 </a>
                        <span> 결제 </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h6 class="coupon__link"><span class="icon_tag_alt"></span> <a href="#">Have a coupon?</a> Click
                    here to enter your code.</h6>
                </div>
            </div>
            <form action="/project/pay" method="post" class="checkout__form">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>Billing detail</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Name <span>*</span></p>
                                    <input type="text" value="${user.user_name}" readonly>
                                </div>
                            </div>
                            
                            
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>Phone <span>*</span></p>
                                    <input type="text" value="${user.user_phone }" readonly>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Email <span>*</span></p>
                                    <input type="text" value="${user.user_email }">
                                </div>
                                <div class="checkout__form__input">
                                    <p> Receiver <span>*</span></p>
                                    <input type="text" id="buy_receive" name="buy_receive" value="${user.user_name}">
                                </div>
                                
                                <div class="checkout__form__input">
                                    <p>Address <span>*</span></p>
                                    <input type="text" id="buy_address" name="buy_address" placeholder="Street Address" value="${address.address_content }" readonly>
                                    <button type="button" style="padding: 7px 15px;" class="site-btn" onclick="windowOpen()">배송지 선택</button>
                                </div><br><br><br>
                            </div>
                            
                            
                            <div class="col-lg-12">
                                <div class="checkout__form__checkbox">
                                    <label for="acc">
                                        Create an acount?
                                        <input type="checkbox" id="acc">
                                        <span class="checkmark"></span>
                                    </label>
                                    <p>Create am acount by entering the information below. If you are a returing
                                        customer login at the <br />top of the page</p>
                                    </div>
                                    <div class="checkout__form__input">
                                        <p>Account Password <span>*</span></p>
                                        <input type="text">
                                    </div>
                                    <div class="checkout__form__checkbox">
                                        <label for="note">
                                            Note about your order, e.g, special noe for delivery
                                            <input type="checkbox" id="note">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <div class="checkout__form__input">
                                        <p>Oder notes <span>*</span></p>
                                        <input type="text"
                                        placeholder="Note about your order, e.g, special noe for delivery">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="checkout__order">
                                <h5>Your order</h5>
                                <div class="checkout__order__product">
                                    <ul>
                                        <li>
                                            <span class="top__text">Product</span>
                                            <span class="top__text__right">Total</span>
                                        </li>
                                        
                                        <!-- 
                                        <li>01. Chain buck bag <span>$ 300.0</span></li>
                                        <li>02. Zip-pockets pebbled<br /> tote briefcase <span>$ 170.0</span></li>
                                        <li>03. Black jean <span>$ 170.0</span></li>
                                        <li>04. Cotton shirt <span>$ 110.0</span></li>
                                         -->
                                        
                                        
                                        <c:set var = "total" value = "0" />
                                        
                                        <c:forEach var="cart" items="${list }" varStatus="status">
                                        <li>
                                        	${cart.cart_productname} &nbsp;&nbsp;/&nbsp;&nbsp; ${cart.cart_amount }개
                                        	<span>&#8361; <fmt:formatNumber value="${cart.product_price * cart.cart_amount}" pattern="#,###" /></span>
                                        </li>
                                        
                                        <c:set var= "total" value="${total + cart.product_price * cart.cart_amount}"/>
                                        
                                        </c:forEach>
                                        
                                        
                                    </ul>
                                </div>
                                <div class="checkout__order__total">
                                    <ul>
                                        <li>Subtotal <span>&#8361; <fmt:formatNumber value="${total}" pattern="#,###" /></span></li>
                                        <li>Discount ( ${grade.grade_discount}&#37; ) <span style="color: gray;">- &#8361; <fmt:formatNumber value="${(grade.grade_discount*total)/100 }" pattern="#,###" /></span></li>
                                        <li>Total <span>&#8361; <fmt:formatNumber value="${total-(grade.grade_discount*total)/100}" pattern="#,###" /></span></li>
                                    </ul>
                                </div>
                                <div class="checkout__order__widget">
                                	<p> 회원등급 ${grade.grade_name }로 ${grade.grade_discount }&#37; 할인 혜택을 받았습니다.</p><br>
                                    <label for="o-acc">
                                        Create an acount?
                                        <input type="checkbox" id="o-acc">
                                        <span class="checkmark"></span>
                                    </label>
                                    <p>Create am acount by entering the information below. If you are a returing customer
                                    login at the top of the page.</p>
                                    <label for="check-payment">
                                        Cheque payment
                                        <input type="checkbox" id="check-payment">
                                        <span class="checkmark"></span>
                                    </label>
                                    <label for="paypal">
                                        PayPal
                                        <input type="checkbox" id="paypal">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <button type="submit" class="site-btn">구매</button>
                                <br><br>
                                <button type="button" onclick="location.href='/project/cart'" class="site-btn"> 돌아가기 </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- Checkout Section End -->

        <%@ include file="instagram.jsp" %>

        <%@ include file="footer.jsp" %>

        <!-- Search Begin -->
        <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>
        <!-- Search End -->

        <!-- Js Plugins -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.countdown.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.nicescroll.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
        
    </body>

    </html>