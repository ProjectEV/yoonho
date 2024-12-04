<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="zxx">
<meta charset="UTF-8">
<title>TechNova</title>
<head>


 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
        $(document).ready(function () {
            // 수량 변경 이벤트 핸들러
            $(".quantity-input").on("input", function () {
                const quantity = $(this).val(); // 입력된 수량
                
                const remain = $(".remain").data("product-remain");
                

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
             
            });

            
        });
    </script>


<style>
.review-item {
	color: #e3c01c;
	margin-right: -4px;
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
                        <a href="/"><i class="fa fa-home"></i> Home </a>
                        <a href="/project/product"> 제품목록 </a>
                        <span> 카테고리 : ${product.product_category} </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="product__details__pic">
                    
                    
                        <div class="product__details__pic__left product__thumb nice-scroll">
                        	<c:forEach var="file_name" items="${file_name}" varStatus="status">
                        		<a class="pt active" href="#product-${status.index + 1}">
	                                <img src="${pageContext.request.contextPath}/images/${file_name}" alt="">
	                            </a>
                        	</c:forEach>
                        </div>
                        
                        
                        <div class="product__details__slider__content">
                            <div class="product__details__pic__slider owl-carousel">
	                            <c:forEach var="file_name" items="${file_name}" varStatus="status">
                                	<img data-hash="product-${status.index + 1}" class="product__big__img" src="${pageContext.request.contextPath}/images/${file_name}" alt="">
	                        	</c:forEach>
                            </div>
                        </div>
                        
                        
                    </div>
                </div>
                
                
                
                <div class="col-lg-6">
                    <div class="product__details__text">
                        <h3>${product.product_name} <span>SAMSUNG</span></h3>
                        
                        <!-- 별점, 상품평 갯수 표시 -->
                        <div class="rating">
                            <c:choose>
                                    		<c:when test="${product.avgScore < 0.25}">
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 0.75}">
                                    			<i class="fa fa-star-half-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 1.25}">
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 1.75}">
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star-half-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 2.25}">
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 2.75}">
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star-half-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 3.25}">
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 3.75}">
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star-half-o"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 4.25}">
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star-o"></i>
                                    		</c:when>
                                    		<c:when test="${product.avgScore < 4.75}">
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star-half-o"></i>
                                    		</c:when>
                                    		<c:otherwise>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    			<i class="fa fa-star"></i>
                                    		</c:otherwise>
                                    	</c:choose>
                            <span>( ${totalReview} reviews )</span>
                        </div>
                        <div class="product__details__price">&#8361; <fmt:formatNumber value="${product.product_price}" pattern="#,###" /></div>
                        <p>${product.product_content}</p>
                        <div class="product__details__button">
                            <form action="/project/cart_register" method="get" id="cart">
                               <div class="quantity">
                                   <span>수량 :</span>
                                   <div class="remain pro-qty" data-product-remain="${product.product_remain }">
                                      <input type="hidden" name="product_id" value="${product.product_id}" />
                                       <input type="number" class="quantity-input" name="amount" value="1">
                                   </div>
                               </div>
                               
                               <a href="javascript:;" onclick="document.getElementById('cart').submit();" class="cart-btn"><span class="icon_bag_alt"></span> 장바구니</a>
                            </form>
                            
                            <ul>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_adjust-horiz"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__details__widget">
                            <ul>
                                <li>
                                    <span>Availability:</span>
                                    <div class="stock__checkbox">
                                        <label for="stockin">
                                            In Stock
                                            <input type="checkbox" id="stockin">
                                            <span class="checkmark"></span>     
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <span>Available color:</span>
                                    <div class="color__checkbox">
                                        <label for="red">
                                            <input type="radio" name="color__radio" id="red" checked>
                                            <span class="checkmark"></span>
                                        </label>
                                        <label for="black">
                                            <input type="radio" name="color__radio" id="black">
                                            <span class="checkmark black-bg"></span>
                                        </label>
                                        <label for="grey">
                                            <input type="radio" name="color__radio" id="grey">
                                            <span class="checkmark grey-bg"></span>
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <span>Available size:</span>
                                    <div class="size__btn">
                                        <label for="xs-btn" class="active">
                                            <input type="radio" id="xs-btn">
                                            xs
                                        </label>
                                        <label for="s-btn">
                                            <input type="radio" id="s-btn">
                                            s
                                        </label>
                                        <label for="m-btn">
                                            <input type="radio" id="m-btn">
                                            m
                                        </label>
                                        <label for="l-btn">
                                            <input type="radio" id="l-btn">
                                            l
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <span>Promotions:</span>
                                    <p>Free shipping</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">상품설명</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab">상품스펙</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab">상품평</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                <h6>상품설명</h6>
                                <p>${product.product_content}</p>

                            </div>
                            <div class="tab-pane" id="tabs-2" role="tabpanel">
                                <h6>상품스펙</h6>
                                <p>${product.product_content}</p>

                            </div>
                            <div class="tab-pane" id="tabs-3" role="tabpanel">
                                <div>
	                                <c:forEach var="review_list" items="${review_list}">
	        							<div class="review-item">
	        								<c:forEach begin="1" end="${review_list.boards_review_score}">
	                    						<i class="fa fa-star"></i>
	                						</c:forEach>
	                						<c:forEach begin="1" end="${5-review_list.boards_review_score}">
	                    						<i class="fa fa-star-o"></i>
	                						</c:forEach>
	                						
	                						
	            							<p><strong>제목:</strong> ${review_list.boards_title}</p>
	            							<p><strong>내용:</strong> ${review_list.boards_content}</p>
	            							<p><strong>작성자:</strong> ${review_list.boards_userid}</p>
	            						<div class="stars">
	                						
	            						</div>
	         				 			 <hr>
	        							</div>
	  							  	</c:forEach>
                                
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="related__title">
                        <h5>RELATED PRODUCTS</h5>
                    </div>
                </div>
                
                <c:forEach var="product" items="${randomProductList }" varStatus="status">
                
                	<div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="${pageContext.request.contextPath}/resources/img/product/related/rp-2.jpg">
                            <c:if test="${product.product_remain < 1}">
                            	<div class="label stockout">out of stock</div>
                            </c:if>
                            <ul class="product__hover">
                                <li><a href="${pageContext.request.contextPath}/resources/img/product/related/rp-2.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="product_detail?product_id=${product.product_id}">${product.product_name }</a></h6>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product__price">&#8361; <fmt:formatNumber value="${product.product_price }" pattern="#,###" /></div>
                            
                        </div>
                    </div>
                </div>
                
                </c:forEach>
                
                
                
                
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->

    <%@ include file="instagram.jsp" %>

	<%@ include file="footer.jsp" %>

</body>

</html>