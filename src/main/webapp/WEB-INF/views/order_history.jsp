<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>History</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css">
    <%--<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css">--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>

    <%--<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>--%>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container">

    <h2>Order History</h2>
    <table class="table table-hover table-striped">
        <tbody>
        <tr>
            <th>Order Id</th>
            <th>Order Status</th>
            <th>Payment Status</th>
            <%--<th>Volume</th>--%>
            <%--<th>inStock</th>--%>
            <%--<th>Category</th>--%>
        </tr>

<c:forEach items="${orders}" var="order">
    <tr>
        <td><a href="<spring:url value="/order/${order.saleId}"/>">${order.saleId}</a></td>
        <td>${order.orderStatus}</td>
        <td>${order.paymentStatus}</td>
        <%--<td>${}</td>--%>
        <%--<td>${}</td>--%>
        <%--<td>${}</td>--%>

    </tr>
</c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
