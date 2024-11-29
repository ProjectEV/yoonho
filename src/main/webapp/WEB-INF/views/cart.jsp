<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="zxx">
<meta charset="UTF-8">
<title>Ashion | Template</title>
<head>
    
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    

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
        $(document).ready(function () {
            // 수량 변경 이벤트 핸들러
            $(".quantity-input").on("input", function () {
                const quantity = $(this).val(); // 입력된 수량
                const row = $(this).closest(".cart-row"); // 현재 상품의 행
                const productId = row.data("product-id"); // 상품 ID
                const price = row.data("product-price"); // 상품 단가
                const remain = row.data("product-remain");
                const discount = row.data("grade-discount");

                if (quantity < 0 || isNaN(quantity)) {
                    alert("수량은 0 이상의 숫자만 가능합니다.");
                    $(this).val(0); // 잘못된 입력은 0으로 리셋
                    return;
                }
                if (quantity > remain) {
                    alert("재고가 부족합니다.");
                    $(this).val(remain);
                    return;
                }
                
                
                // 개별 상품의 총 가격 업데이트
                const totalPrice = quantity * price;
                row.find(".product-total").text("₩ "+totalPrice.toLocaleString());
				
                // 서버에 Ajax 요청으로 총합 계산 요청
				let total = 0;
                
                $(".cart-row").each(function () {
                    const quantity = $(this).find(".quantity-input").val();
                    const price = $(this).data("product-price");
                    total += quantity * price;
                });
				
                // 합계 표시
                $("#cart-total").text("₩ "+total.toLocaleString());
                
                const priceDiscount = Math.round(discount * total / 100);
            	$("#price-discount").text("- ₩ "+priceDiscount.toLocaleString());
            	
            	var finalPrice = total - priceDiscount;
            	$("#final-price").text("₩ "+finalPrice.toLocaleString());
            });
            
        });
    </script>
    
              
    
</head>

<body>
    

    <%@ include file="header.jsp" %>

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/"><i class="fa fa-home"></i> Home</a>
                        <span>Shopping cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                    
                    <form action="/project/pay" method="get" id="cartInfo" name="cartInfo">
                    
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            
                            
                            <c:set var = "total" value = "0" />
                            
                            
                            
                            <c:forEach var="cart" items="${list }" varStatus="status">
		 						<tr class="cart-row" data-product-id="${cart.cart_productid}" data-product-price="${cart.product_price}" 
		 						data-product-remain="${cart.product_remain }" data-grade-discount="${grade.grade_discount }">
                                    <td class="cart__product__item">
                                        <img src="${pageContext.request.contextPath}/resources/img/shop-cart/cp-1.jpg" alt="">
                                        <div class="cart__product__item__title">
                                            <h6><a href="product_detail?product_id=${cart.cart_productid}">${cart.cart_productname}</a></h6>
                                            <div class="rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="cart__price">&#8361; <fmt:formatNumber value="${cart.product_price}" pattern="#,###" /></td>
                                    <td>
                                    
                                    <!-- <div class="pro-qty">
                                            <input type="number" class="quantity-input" value="${cart.cart_amount}" min="0">
                                        </div> -->
                                        <input type="hidden" name="update[${status.index}].cart_productid" value="${cart.cart_productid}" />
                                        <input type="number" class="quantity-input pro-qty" name="update[${status.index}].cart_amount"  value="${cart.cart_amount}" min="1">
                                        
                                        
                                        
                                    </td>
                                    <td class="product-total">&#8361; <fmt:formatNumber value="${cart.product_price * cart.cart_amount}" pattern="#,###" /></td>
                                    <td class="cart__close"><a href="cart_delete?product_id=${cart.cart_productid}&user_id=${user.user_id}"><span class="icon_close"></span></a></td>
                                </tr>
                                
                                
                                <c:set var= "total" value="${total + cart.product_price * cart.cart_amount}"/>
                                
                                
							</c:forEach>
                            
                            
                               
                            </tbody>
                        </table>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="/project/product">Continue Shopping</a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn" align="right">
                        <a href="#"> Update cart</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">Apply</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <h6>Cart total</h6>
                        
                       
                        
                        
                        <ul>
                            <li>Subtotal <span id="cart-total">&#8361; <fmt:formatNumber value="${total}" pattern="#,###" /></span></li>
                            <li>Discount ( ${grade.grade_discount}&#37; ) <span style="color: gray;" id="price-discount">- &#8361; <fmt:formatNumber value="${(grade.grade_discount*total)/100 }" pattern="#,###" /></span></li>
                            <li>Total <span id="final-price">&#8361; <fmt:formatNumber value="${total-(grade.grade_discount*total)/100}" pattern="#,###" /></span></li>
                        </ul>
                        
                        <!-- <button type="submit" form="cartInfo" class="site-btn">&nbsp;&nbsp;&nbsp;&nbsp;Proceed to checkout&nbsp;&nbsp;&nbsp;&nbsp;</button> -->
                        <a href="javascript:document.cartInfo.submit();" class="primary-btn"> 구매하기 </a>
                        
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->

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