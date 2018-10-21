<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css">
    <%--<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css">--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/cartNumber.js"/>"></script>
    <script src="<c:url value="/resources/js/order.js"/>"></script>
    <script>var ctx = "${pageContext.request.contextPath}"</script>
    <sec:csrfMetaTags/>

    <%--<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>--%>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container">
    <h2>Order # ${order.saleId}</h2>

    <h3>User Details</h3>
    <div class="container">
        <div class="row">
            <div class="form-group">
                <label>Name</label> <span>${order.user.name}</span>
            </div>
            <div class="form-group">
                <label>Surname</label> <span>${order.user.surname}</span>
            </div>
            <div class="form-group">
                <label>E-mail</label> <span>${order.user.email}</span>
            </div>
        </div>
    </div>

    <h3>Products</h3>
    <table class="table table-hover table-striped">
        <tbody>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Weight</th>
            <th>Volume</th>
            <th>inStock</th>
            <th>Category</th>
        </tr>

        <c:forEach items="${order.products}" var="product">
            <tr>
                <td><a href="<spring:url value="/product/${product.productId}"/>">${product.name}</a></td>
                <td>${product.price}</td>
                <td>${product.weight}</td>
                <td>${product.volume}</td>
                <td>${product.inStock}</td>
                <td>${product.category}</td>

            </tr>
        </c:forEach>

        </tbody>
    </table>

    <h3>Address</h3>
   <div class="container">
       <div class="row">
           <div class="form-group">
               <label>Country</label> <span>${order.address.country}</span>
           </div>
           <div class="form-group">
               <label>City</label> <span>${order.address.city}</span>
           </div>
           <div class="form-group">
               <label>ZipCode</label> <span>${order.address.zipCode}</span>
           </div>
           <div class="form-group">
               <label>Street</label> <span>${order.address.street}</span>
           </div>
           <div class="form-group">
               <label>House</label> <span>${order.address.house}</span>
           </div>
           <div class="form-group">
               <label>Flat</label> <span>${order.address.flat}</span>
           </div>
       </div>
   </div>
    <h3>Other detais</h3>
    <div class="container">
        <div class="row">
            <div class="form-group">
                <label>Delivery method</label> <span>${order.deliveryMethod}</span>
            </div>
            <div class="form-group">
                <label>Payment Method</label> <span>${order.paymentMethod}</span>
            </div>
            <div class="form-group">
                <label>Payment Status</label> <span>${order.paymentStatus}</span>
            </div>
            <div class="form-group">
                <label>Order Status</label> <span>${order.orderStatus}</span>
            </div>
        </div>
</div>

    <a href="<spring:url value="/order/repeat/save"/>"> Check out</a>
    <%--<a href="<spring:url value="/order/place"/>" class="btn btn-default" id="addToCart">buy</a>--%>
    <%--<button id="addProductToCart" type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">--%>
        <%--<form id="cart-form" action="<spring:url value="/product/addToCart"/>" method="post"></form>--%>
        <%--<sec:csrfInput/>--%>
        <%--Add to cart--%>
    <%--</button>--%>



</div>
</body>
</html>
