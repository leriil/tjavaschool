<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Order Review</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="<spring:url value="/resources/css/bootstrap-select.min.css"/>"
          type="text/css"/>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script
            src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>

</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<%--TODO:make products into a table. Add Total sum--%>
<div class="container">
    <div class="row">
        <h1>Please review the order for accuracy</h1>
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

            <c:forEach items="${productsInCart}" var="product">
                <tr>
                    <td><a href="<spring:url value="/product/${product.productId}"/>">${product.name}</a></td>
                    <td>${product.price}</td>
                    <td>${product.weight}</td>
                    <td>${product.volume}</td>
                    <td>${product.inStock}</td>
                    <td>${product.category.categoryName}</td>

                </tr>
            </c:forEach>

            <tr class="info">
                <td/>
                <td/>
                <td/>
                <td/>
                <td ><b>Total</b></td>
                <td ><span id="total"/></td>
            </tr>
            </tbody>
        </table>

        <h3>Shipping Address</h3>
        <div class="form-group">
            <label>Name</label> <span>${order.user.name}</span>
        </div>

        <div class="form-group">
            <label>Surname</label> <span>${order.user.surname }</span>
        </div>
        <div class="form-group">
            <label>Country</label> <span>${order.address.country}</span>
        </div>

        <div class="form-group">
            <label>City</label> <span>${order.address.city }</span>
        </div>

        <div class="form-group">
            <label>Zipcode</label> <span>${order.address.zipCode}</span>
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
        <h3>3.Payment and shipping</h3>
        <div class="form-group">
            <label>Payment Method</label> <span>${order.paymentMethod}</span>
        </div>

        <div class="form-group">
            <label>Delivery Method</label> <span>${order.deliveryMethod }</span>
        </div>


        <a href="<spring:url value="/order/place"/>" class="btn btn-default">Edit</a>
        <a href="<spring:url value="/order/save"/>" class="btn btn-default">Confirm</a>
        <p>


        </p>
    </div>
</div>
</body>
</html>