<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Order</title>
    <link rel="stylesheet" href="<spring:url value="/resources/css/global.css"/>" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/resource.js"/>"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<c:url value="/resources/js/removeFromCart.js"/>"></script>
    <script src="<c:url value="/resources/js/accounting.js"/>"></script>
    <sec:csrfMetaTags/>
    <script>var ctx = "${pageContext.request.contextPath}"</script>


</head>

<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>

<div class="container">

    <c:if test="${param.orderUpdated==true}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            The order has been successfully updated.
        </div>
    </c:if>
    <c:if test="${param.orderUpdated==false}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            The order was not updated. Contact your supervisor for more details.
        </div>
    </c:if>
    <h1>Order details</h1>
    <spring:url value="/order/update" var="formUrl"/>

    <div class="container">
        <h1>Products</h1>

        <table class="table table-hover">
            <tbody>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Weight</th>
                <th>Volume</th>
                <th>Category</th>

            </tr>
            <c:forEach items="${order.products}" var="p" varStatus="loop">
                <tr>
                    <td id="productForRemoval-${loop.index}">${p.productId}</td>
                    <td><a href="<spring:url value="/product/${p.productId}"/>">${p.name}</a></td>
                    <td>${p.price}</td>
                    <td>${p.weight}</td>
                    <td>${p.volume}</td>
                    <td>${p.category.categoryName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

    <form:form action="${formUrl}" method="POST" modelAttribute="order">
    <h2>1.Client</h2>
    <div class="row">

        <div class="form-group">
            <label for="order-user-name">Name</label>
            <form:input path="user.name" cssClass="form-control" id="order-user-name"/>

        </div>

        <div class="form-group">
            <label for="order-user-surname">Surname</label>
            <form:input path="user.surname" cssClass="form-control" id="order-user-surname"/>

        </div>
    </div>
    <h2>2.Shipping Address</h2>
    <div class="row">
        <div class="form-group">
            <label for="order-country">Country</label>
            <form:input path="address.country" cssClass="form-control" id="order-country"/>

        </div>

        <div class="form-group">
            <label for="order-city">City</label>
            <form:input path="address.city" cssClass="form-control" id="order-city"/>

        </div>
        <div class="form-group">
            <label for="order-zip">ZipCode</label>
            <form:input path="address.zipCode" cssClass="form-control" id="order-zip"/>

        </div>

        <div class="form-group">
            <label for="order-street">Street</label>
            <form:input path="address.street" cssClass="form-control" id="order-street"/>

        </div>

        <div class="form-group">
            <label for="order-house">House</label>
            <form:input path="address.house" cssClass="form-control" id="order-house"/>

        </div>

        <div class="form-group">
            <label for="order-flat">Flat</label>
            <form:input path="address.flat" cssClass="form-control" id="order-flat"/>

        </div>
    </div>

    <h2>3.Payment and Delivery</h2>
    <div class="row">
        <div class="form-group">
            <label for="delivery-method">Delivery Method</label>
            <form:input path="deliveryMethod" cssClass="form-control" id="delivery-method" readonly="true"/>

        </div>

        <div class="form-group">
            <label for="payment-method">Payment Method</label>
            <form:input path="paymentMethod" cssClass="form-control" id="payment-method" readonly="true"/>

        </div>

        <div class="form-group">
            <label for="order-payment-status">Payment Status</label>
            <form:select path="paymentStatus" items="${paymentStatusOptions}"
                         cssClass="selectpicker" id="order-payment-status"/>
        </div>

        <div class="form-group">
            <label for="order-status">Order Status</label>
            <form:select path="orderStatus" items="${orderStatusOptions}"
                         cssClass="selectpicker" id="order-status"/>
        </div>
    </div>


    <div class="row button-spacing">
        <button type="submit" class="btn btn-primary button-spacing">Update</button>
    </div>
</div>


</form:form>


</body>
</html>
