<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<c:url value="/resources/js/order.js"/>"></script>
    <script>var ctx = "${pageContext.request.contextPath}"</script>

    <sec:csrfMetaTags/>

    <%--<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>--%>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container">

    <div class="row">
        <h2>Order # ${order.orderId}</h2>
        <%--<a href="<spring:url value="/order/place"/>" class="btn btn-primary"> Repeat the order </a>--%>

        <button class="btn btn-primary" onclick="repeatOrder(${order.orderId})">Repeat order</button>
    </div>

    <%--<h3>User Details</h3>--%>
    <%--<div class="container">--%>
        <%--<div class="row">--%>
            <%--<div class="form-group">--%>
                <%--<label>Name</label> <span>${order.user.name}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>Surname</label> <span>${order.user.surname}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>E-mail</label> <span>${order.user.email}</span>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--TODO:remove when not needed anymore--%>
<br>
    <address>
        <strong><span>${order.user.name}</span> <span>${order.user.surname}</span></strong><br>
        <a href="mailto:#"><span>${order.user.email}</span></a>
    </address>

    <address>
        <strong>Address</strong><br>
        <span>${order.address.house}</span> <span>${order.address.street}</span>, <span>${order.address.flat}</span><br>
        <span>${order.address.city}</span>, <span>${order.address.zipCode}</span><br>
        <%--<abbr title="Phone">P:</abbr> (123) 456-7890--%>
    </address>
    <strong>Payment and Delivery details</strong><br>
    Order: <span>${order.deliveryMethod}</span><br>
    Paid : <span>${order.paymentMethod}</span><br>

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
                <td id="productToCart"><a href="<spring:url value="/product/${product.productId}"/>">${product.name}</a></td>
                <td>${product.price}</td>
                <td>${product.weight}</td>
                <td>${product.volume}</td>
                <td>${product.inStock}</td>
                <td>${product.category}</td>

            </tr>
        </c:forEach>

        </tbody>
    </table>


    <%--<h3>Address</h3>--%>
    <%--<div class="container">--%>
        <%--<div class="row">--%>
            <%--<div class="form-group">--%>
                <%--<label>Country</label> <span>${order.address.country}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>City</label> <span>${order.address.city}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>ZipCode</label> <span>${order.address.zipCode}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>Street</label> <span>${order.address.street}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>House</label> <span>${order.address.house}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>Flat</label> <span>${order.address.flat}</span>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<h3>Payment and Delivery details</h3>--%>
    <%--<div class="container">--%>
        <%--<div class="row">--%>
            <%--<div class="form-group">--%>
                <%--<label>Delivery method</label> <span>${order.deliveryMethod}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>Payment Method</label> <span>${order.paymentMethod}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>Payment Status</label> <span>${order.paymentStatus}</span>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label>Order Status</label> <span>${order.orderStatus}</span>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>
</body>
</html>
