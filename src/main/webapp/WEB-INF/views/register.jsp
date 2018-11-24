<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tshop</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/global.css"/>" type="text/css"/>
    <%--<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css">--%>
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
        <h1>New Account</h1>
    </div>
    <spring:url value="/register" var="registerVar"/>
    <form:form action="${registerVar}" method="POST" modelAttribute="user">
        <div class="form-group">
            <label for="user-login">Login</label>
            <form:input path="login" cssClass="form-control" id="user-login"/>
            <form:errors path="login" cssClass="errors"></form:errors>
        </div>

        <div class="form-group">
            <label for="user-name">First Name</label>
            <form:input path="name" cssClass="form-control" id="user-name"/>
            <form:errors path="name" cssClass="errors"></form:errors>
        </div>
        <div class="form-group">
            <label for="user-surname">Last Name</label>
            <form:input path="surname" cssClass="form-control" id="user-surname"/>
            <form:errors path="surname" cssClass="errors"></form:errors>
        </div>
        <div class="form-group">
            <label for="user-email">E-mail</label>
            <form:input path="email" cssClass="form-control" id="user-email"/>
            <form:errors path="email" cssClass="errors"></form:errors>
        </div>

        <div class="form-group">
            <label for="user-birthdate">Date of Birth</label>
            <form:input path="birthDate" cssClass="form-control" id="user-birthdate"/>
            <form:errors path="birthDate" cssClass="errors"></form:errors>
        </div>

        <div class="form-group">
            <label for="user-password">Password</label>
            <form:password path="password" cssClass="form-control" id="user-password"/>
            <form:errors path="password" cssClass="errors"></form:errors>
        </div>
        <div class="form-group">
            <label for="user-confirm-password">Confirm Password</label>
            <form:password path="confirmPassword" cssClass="form-control" id="user-confirm-password"/>
            <form:errors path="confirmPassword" cssClass="errors"></form:errors>
        </div>
        <sec:csrfInput/>

        <button type="submit" id="btn-save" class="btn btn-primary">Confirm</button>
    </form:form>
</div>
</body>
</html>