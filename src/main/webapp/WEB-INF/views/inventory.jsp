<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="zxx">

<head>
  
<style>
.paging {
	text-align: center;
	font-size: 18px;
}

.shop__cart__table1 {
    margin-bottom: 30px;

    table {
        width: 100%;
    }

    thead {
        border-bottom: 1px solid #f2f2f2;

        th {
            font-size: 18px;
            color: $heading-color;
            font-weight: 600;
            text-transform: uppercase;
            padding-bottom: 20px;
        }
    }

    tbody {

        tr {
            border-bottom: 1px solid #f2f2f2;
			
            td {
                padding: 10px 0;
            }
            
			.rating {

                        i {
                            font-size: 10px;
                            color: #e3c01c;
                            margin-right: -4px;
                        }
                    }
                    
            .cart__product__item1 {
                overflow: hidden;
                width: 340px;
                

                img {
                    float: left;
                    margin-right: 50px;
                }

                .cart__product__item__title {
                    overflow: hidden;
                    padding-top: 23px;
                    

                    h6 {
                        color: $heading-color;
                        font-weight: 600;
                    }

                    
                }
            }

            .cart__price {
                font-size: 16px;
                color: $primary-color;
                font-weight: 600;
                width: 190px;
            }

            .cart__quantity {
                width: 190px;

                .pro-qty {
                    border: none;
                    padding: 0;
                    width: 110px;
                    border-radius: 0;

                    input {
                        color: #444444;
                    }

                    .qtybtn {
                        font-size: 16px;
                        color: #444444;
                    }
                }
            }

            .cart__total {
                font-size: 16px;
                color: $primary-color;
                font-weight: 600;
                width: 150px;
            }

            .cart__close {
                text-align: right;

                span {
                    height: 45px;
                    width: 45px;
                    background: #f2f2f2;
                    border-radius: 50%;
                    font-size: 18px;
                    color: $heading-color;
                    line-height: 44px;
                    text-align: center;
                    display: inline-block;
                    font-weight: 600;
                    cursor: pointer;
                }
            }
        }
    }
}
</style>

    

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

    <%@ include file="header.jsp" %>

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/"><i class="fa fa-home"></i> 홈</a>
                        <span> 재고관리 </span>
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
                    <div class="shop__cart__table1">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Review</th>
                                    <th>Price</th>
                                    <th>&nbsp;&nbsp;Remain</th>
                                    <th>Sales</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            
                            
                            <c:set var = "total" value = "0" />
                            
                           
                            <c:forEach var="product" items="${list }" varStatus="status">
		 						<tr>
                                    <td class="cart__product__item1">
                                    	<a href="product_detail?product_id=${product.product_id}"> <img src="${pageContext.request.contextPath}/resources/img/shop-cart/cp-1.jpg" alt="" > </a>
                                        <div class="cart__product__item__title">
                                            <h6><a href="inventory_detail?product_id=${product.product_id}">${product.product_name}</a></h6>
                                            <div>${product.product_id}</div>
                                            
                                        </div>
                                    </td>
                                    	
                                    <td>
                                    	<div class="rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                    </td>
                                    
                                    <td class="cart__price" style="color: #ca1515;">&#8361; <fmt:formatNumber value="${product.product_price}" pattern="#,###" /></td>
                                    <td class="cart__quantity" style="text-align: left;">
                                        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        	<c:choose>
	                                        	<c:when test="${product.product_remain == 0}">
	                                        	매진
	                                        	</c:when>
	                                        	<c:otherwise>
	                                        	${product.product_remain}
	                                        	</c:otherwise>
                                        	</c:choose>
                                        </div>
                                    </td>
                                    <td class="cart__quantity">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <fmt:formatNumber value="${product.product_sales}" pattern="#,###" /></td>
                                </tr>
                                
                                
                                <c:set var= "total" value="${total + product.product_price * product.product_remain}"/>
                                
                                
							</c:forEach>
                            
                            
                            
                               
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            	<div class="pagination__option paging">
            		
            		<c:choose>
            			<c:when test="${pageListNUM>1}">
							<a href="inventory?pageListNUM=${pageListNUM-1}&pageNUM=${pageListNUM*10-10}"> &lt; </a>
						</c:when>
						<c:otherwise>
							<a style="color:lightgray;"> &lt; </a> 
						</c:otherwise>
            		</c:choose>
	                &nbsp;
							  	
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
					
						<c:choose>
							<c:when test="${i == 1 && param.pageNUM == null }">
								<a href="inventory?pageListNUM=${pageListNUM }&pageNUM=${i }" style="color: #ca1515;"> ${i } </a>
							</c:when>
							<c:when test="${i == param.pageNUM }">
								<a href="inventory?pageListNUM=${pageListNUM }&pageNUM=${i }" style="color: #ca1515;"> ${i } </a>
							</c:when>
							<c:otherwise>
								<a href="inventory?pageListNUM=${pageListNUM }&pageNUM=${i }" > ${i } </a>
							</c:otherwise>
						</c:choose>
						&nbsp;
					
					</c:forEach>
							  
					<c:choose>
            			<c:when test="${pageListNUM<(totalPage/10)}">
							<a href="inventory?pageListNUM=${pageListNUM+1}&pageNUM=${pageListNUM*10+1}"> &gt; </a>
						</c:when>
						<c:otherwise>
							<a style="color:lightgray;"> &gt; </a> 
						</c:otherwise>
            		</c:choose>		  
					
					
                </div>
                
                
                <br><br>
            <div class="row">
                
                
                
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="/project/product_register"> Upload New Product </a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn update__btn">
                        <a href="#"><span class="icon_loading"></span> Update cart</a>
                    </div>
                </div>
            </div>
            
            <br>
            
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
                            <li>Subtotal <span>&#8361; <fmt:formatNumber value="${total}" pattern="#,###" /></span></li>
                            <li>Total <span>&#8361; <fmt:formatNumber value="${total}" pattern="#,###" /></span></li>
                        </ul>
                        <a href="/project/pay" class="primary-btn">Proceed to checkout</a>
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