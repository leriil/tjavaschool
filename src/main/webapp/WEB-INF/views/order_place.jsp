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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/resource.js"/>"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<c:url value="/resources/js/removeFromCart.js"/>"></script>
    <sec:csrfMetaTags/>
    <script>var ctx = "${pageContext.request.contextPath}"</script>


</head>

<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<%--<div class="outer-container">--%>


<%--<div class="container"><h2>Hello</h2></div>--%>
<div class="container">
    <h1>Order details</h1>
    <spring:url value="/order/review" var="formUrl"/>
    <form:form action="${formUrl}" method="POST" modelAttribute="order">
    <h2>1.Client</h2>
    <div class="row">

        <div class="form-group">
            <label for="order-user-name">Name</label>
            <form:input path="user.name" cssClass="form-control" id="order-user-name"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="order-user-surname">Surname</label>
            <form:input path="user.surname" cssClass="form-control" id="order-user-surname"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>
    </div>
    <h2>2.Shipping Address</h2>
    <div class="row">
        <div class="form-group">
            <label for="order-country">Country</label>
            <form:input path="address.country" cssClass="form-control" id="order-country"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="order-city">City</label>
            <form:input path="address.city" cssClass="form-control" id="order-city"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>
        <div class="form-group">
            <label for="order-zip">ZipCode</label>
            <form:input path="address.zipCode" cssClass="form-control" id="order-zip"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="order-street">Street</label>
            <form:input path="address.street" cssClass="form-control" id="order-street"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="order-house">House</label>
            <form:input path="address.house" cssClass="form-control" id="order-house"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="order-flat">Flat</label>
            <form:input path="address.flat" cssClass="form-control" id="order-flat"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>
    </div>
        <%--<a id="request_link" href="<spring:url value="/resource/request"/>">Send Request</a>--%>
    <h2>3.Payment and Delivery</h2>
    <div class="row">
        <div class="form-group">
            <label for="order-delivery-method">Delivery Method</label>
            <form:input path="deliveryMethod" cssClass="form-control" id="order-delivery-method"/>
                <%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
        </div>

        <div class="form-group">
            <label for="order-payment-method">Payment Method</label>
            <form:select path="paymentMethod" items="${paymentOptions}"
                         cssClass="selectpicker" id="order-payment-method"/>
        </div>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</div>


</form:form>
<div class="container">
    <h1>Products in your cart</h1>
    <table class="table table-hover">
        <tbody>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Weight</th>
            <th>Volume</th>
            <th>Category</th>
            <th></th>
        </tr>
        <c:forEach items="${productsInCart}" var="p" varStatus="loop">
            <tr>
                <td id="productForRemoval-${loop.index}">${p.productId}</td>
                <td><a href="<spring:url value="/product/${p.productId}"/>">${p.name}</a></td>
                <td>${p.price}</td>
                <td>${p.weight}</td>
                <td>${p.volume}</td>
                <td>${p.category}</td>
                <td>
                    <button id="removeFromCart-${loop.index}" onclick="removeProduct(${p.productId})"><span class="glyphicon glyphicon-trash"></span></button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td/>
            <td/>
            <td/>
            <td/>
            <td/>
            <td bgcolor="#a9a9a9"><b>Total</b></td>
            <td bgcolor="#a9a9a9"><span id="total"/></td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
