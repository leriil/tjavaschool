
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Place order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css">
    <%--<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css">--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/cartNumber.js"/>"></script>
    <%--<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>--%>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<%--<div class="outer-container">--%>
<h1>Order details</h1>


<%--<div class="container"><h2>Hello</h2></div>--%>
<div class="container">
<spring:url value="/product/order/save" var="formUrl"/>
<form:form action="${formUrl}" method="POST" modelAttribute="sale">
<h2>2.Shipping Address</h2>
    <div class="row">

        <div class="form-group">
            <label for="sale-user-name">Name</label>
            <form:input path="user.name" cssClass="form-control" id="sale-user-name" />
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="sale-user-surname">Surname</label>
            <form:input path="user.surname" cssClass="form-control" id="sale-user-surname"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="sale-country">Country</label>
            <form:input path="address.country" cssClass="form-control" id="sale-country"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="sale-city">City</label>
            <form:input path="address.city" cssClass="form-control" id="sale-city"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>
        <div class="form-group">
            <label for="sale-zip">ZipCode</label>
            <form:input path="address.zipCode" cssClass="form-control" id="sale-zip"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="sale-street">Street</label>
            <form:input path="address.street" cssClass="form-control" id="sale-street"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="sale-house">House</label>
            <form:input path="address.house" cssClass="form-control" id="sale-house"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="sale-flat">Flat</label>
            <form:input path="address.flat" cssClass="form-control" id="sale-flat"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>
            <%--<a id="request_link" href="<spring:url value="/resource/request"/>">Send Request</a>--%>
        <div class="form-group">
            <label for="sale-delivery-method">Delivery Method</label>
            <form:input path="deliveryMethod" cssClass="form-control" id="sale-delivery-method"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="sale-payment-method">Payment Method</label>
            <form:input path="paymentMethod" cssClass="form-control" id="sale-payment-method"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </div>


</form:form>
</div>
<div class="container">
    <h2>2.Products</h2>
    <table class="table table-hover">
        <tbody>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Weight</th>
            <th>Volume</th>
            <th>Category</th>
        </tr>
        <c:forEach items="${productsInCart}" var="p">
            <tr>
                <td><a href="<spring:url value="/product/${p.productId}"/>">${p.name}</a></td>
                <td>${p.price}</td>
                <td>${p.weight}</td>
                <td>${p.volume}</td>
                <td>${p.category}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--</div>--%>
</body>
</html>
