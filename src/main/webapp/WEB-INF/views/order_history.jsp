<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>History</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>

</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container">

    <h2>Order History</h2>
    <table class="table table-hover table-striped">
        <tbody>

        <c:if test="${orders.size()==0}">
            <h4>You have not placed any orders yet.</h4>
        </c:if>

        <c:if test="${orders.size()>0}">
            <tr>
                <th>Order Id</th>
                <th>Order Status</th>
                <th>Payment Status</th>
                <th>Order Date</th>
            </tr>

            <c:forEach items="${orders}" var="order">
                <tr>
                    <td><a href="<spring:url value="/order/${order.orderId}"/>">Order # ${order.orderId}</a></td>
                    <td>${order.orderStatus}</td>
                    <td>${order.paymentStatus}</td>
                    <td>${order.orderDate}</td>
                </tr>
            </c:forEach>
        </c:if>


        </tbody>
    </table>
</div>
</body>
</html>
