<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tshop</title>
    <link rel="stylesheet" href="<spring:url value="/resources/css/global.css"/>"/>
    <link rel="stylesheet" href="<spring:url value="/resources/css/datepicker.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" type="text/javascript"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap-datepicker.js"/>"></script>


    <%--<head>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
    <%--<title>Account</title>--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css"/>

    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>--%>
    <script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

    <%----%>

    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<c:url value="/resources/js/removeFromCart.js"/>"></script>
    <script src="<spring:url value="/resources/js/dates.js"/>"></script>
    <sec:csrfMetaTags/>
    <script>var ctx = "${pageContext.request.contextPath}"</script>
</head>

<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>

<div class="container">
    <h1>My Account</h1>

    <c:if test="${param.updated==true}">
    <div class="alert alert-success alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        You've successfully updated your account.
    </div>
    </c:if>
    <spring:url value="/user/account/update" var="formUrl"/>

    <form:form action="${formUrl}" method="POST" modelAttribute="user">
    <h2>UserDetails</h2>
    <div class="row">

        <div class="form-group">
            <label for="account-user-name">Name</label>
            <form:input path="name" cssClass="form-control" id="account-user-name"/>
            <form:errors path="name" cssClass="errors"></form:errors>
        </div>

        <div class="form-group">
            <label for="account-user-surname">Surname</label>
            <form:input path="surname" cssClass="form-control" id="account-user-surname"/>
            <form:errors path="surname" cssClass="errors"></form:errors>
        </div>

        <div class="form-group">
            <label for="account-user-surname">E-mail</label>
            <form:input path="email" cssClass="form-control" id="account-user-surname"/>
            <form:errors path="email" cssClass="errors"></form:errors>
        </div>
        <div class="form-group">
            <label for="account-user-birthdate">Date of Birth</label>
            <div class="input-append date clearfix"
                 data-date="" data-date-format="yyyy-mm-dd">
                <div class="form-inline col-sm-5">
                    <form:input path="birthDate" cssClass="pull-left form-control" id="account-user-birthdate"/>
                    <span style="margin-left: 7px; margin-top: 7px" class="add-on pull-left"><i
                            class="glyphicon glyphicon-calendar"></i></span></div>
                <form:errors path="birthDate" cssClass="errors"></form:errors>
            </div>

        </div>

    </div>
    <h2>Address</h2>
    <div class="row">
        <div class="form-group">
            <label for="account-country">Country</label>
            <form:input path="address.country" cssClass="form-control" id="account-country"/>
            <form:errors path="address.country" cssClass="errors"></form:errors>

        </div>

        <div class="form-group">
            <label for="account-city">City</label>
            <form:input path="address.city" cssClass="form-control" id="account-city"/>

        </div>
        <div class="form-group">
            <label for="account-zip">ZipCode</label>
            <form:input path="address.zipCode" cssClass="form-control" id="account-zip"/>

        </div>

        <div class="form-group">
            <label for="account-street">Street</label>
            <form:input path="address.street" cssClass="form-control" id="account-street"/>

        </div>

        <div class="form-group">
            <label for="account-house">House</label>
            <form:input path="address.house" cssClass="form-control" id="account-house"/>

        </div>

        <div class="form-group">
            <label for="account-flat">Flat</label>
            <form:input path="address.flat" cssClass="form-control" id="account-flat"/>

        </div>
    </div>

    <h2>Password</h2>
    <div class="row">

        <div class="form-group">
            <label for="account-password">Password</label>
            <form:password path="password" cssClass="form-control" id="account-password"/>
            <form:errors path="password" cssClass="errors"/>
            <%--showPassword="true"--%>
        </div>
        <div class="form-group">
            <label for="account-password">Confirm Password</label>
            <form:password path="confirmPassword" cssClass="form-control" id="account-password"/>
            <%--<form:errors path="confirmPassword" cssClass="errors"/>--%>
                <%--showPassword="true"--%>
        </div>
        <div class="row button-spacing">
            <button type="submit" class="btn btn-primary button-spacing">Confirm</button>
        </div>
</div>


</form:form>


</body>
</html>
