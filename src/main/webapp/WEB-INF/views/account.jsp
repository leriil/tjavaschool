<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Account</title>
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

<div class="container">
    <h1>My Account</h1>
    <spring:url value="/user/account/update" var="formUrl"/>

    <form:form action="${formUrl}" method="POST" modelAttribute="user">
    <h2>UserDetails</h2>
    <div class="row">

        <div class="form-group">
            <label for="order-user-name">Name</label>
            <form:input path="name" cssClass="form-control" id="order-user-name"/>
        </div>

        <div class="form-group">
            <label for="order-user-surname">Surname</label>
            <form:input path="surname" cssClass="form-control" id="order-user-surname"/>
        </div>

        <div class="form-group">
            <label for="order-user-surname">E-mail</label>
            <form:input path="email" cssClass="form-control" id="order-user-surname"/>
        </div>
        <div class="form-group">
            <label for="order-user-name">BirthDate</label>
            <form:input path="birthDate"  cssClass="form-control" id="order-user-name"/>
        </div>


    </div>
    <h2>Address</h2>
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
            <label for="address-house">House</label>
            <form:input path="address.house" cssClass="form-control" id="address-house"/>

        </div>

        <div class="form-group">
            <label for="address-flat">Flat</label>
            <form:input path="address.flat" cssClass="form-control" id="address-flat"/>

        </div>
    </div>

    <h2>Password</h2>
    <div class="row">

        <div class="form-group">
            <label for="user-password">Password</label>
            <form:input path="password" cssClass="form-control" id="user-password" />

        </div>



    <%--</div>--%>
    <button type="submit" class="btn btn-default">Confirm</button>
</div>


</form:form>


</body>
</html>
