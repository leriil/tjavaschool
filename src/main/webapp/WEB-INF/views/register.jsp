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
    <link rel="stylesheet" src="<c:url value="/resources/css/global.css"/>">
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
    <form id="registration-form" action="${registerVar}" method="POST">
        <div class="form-group">
            <label>Login</label>
            <input name="login" class="form-control"/>
            <form:errors path="login" cssClass="errors"></form:errors>
        </div>
        <div class="form-group">
            <label>First Name</label>
            <input name="name" class="form-control"/>
        </div>
        <div class="form-group">
            <label>Last Name</label>
            <input name="surname" class="form-control"/>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input name="email" class="form-control"/>
        </div>
        <div class="form-group">
            <label>Birth</label>
            <input type="date" name="birthDate" class="form-control">
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" class="form-control"/>
        </div>
        <sec:csrfInput/>

        <button type="submit" id="btn-save" class="btn btn-primary">Confirm</button>
    </form>
</div>
</body>
</html>