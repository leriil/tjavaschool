<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tshop</title>
    <link rel="stylesheet"
          href="<spring:url value="/resources/css/global.css"/>"/>
    <link rel="stylesheet"
          href="<spring:url value="/resources/css/datepicker.css"/>"/>
    <link rel="stylesheet"
          href="<spring:url value="/resources/css/bootstrap-multiselect.css"/>"/>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Latest Jquery -->
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"
            type="text/javascript"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap-multiselect.js"/>"></script>

    <script src="<spring:url value="/resources/js/global.js"/>"></script>
    <script src="<spring:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<spring:url value="/resources/js/dates.js"/>"></script>
    <script src="<spring:url value="/resources/js/moment.js"/>"></script>
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
            <label for="user-login">Login<span class="errors">&#42;</span></label>
            <form:input path="login" cssClass="form-control" id="user-login"/>
            <form:errors path="login" cssClass="errors"></form:errors>
        </div>

        <div class="form-group">
            <label for="user-name">First Name<span class="errors">&#42;</span></label>
            <form:input path="name" cssClass="form-control" id="user-name"/>
            <form:errors path="name" cssClass="errors"></form:errors>
        </div>
        <div class="form-group">
            <label for="user-surname">Last Name<span class="errors">&#42;</span></label>
            <form:input path="surname" cssClass="form-control" id="user-surname"/>
            <form:errors path="surname" cssClass="errors"></form:errors>
        </div>
        <div class="form-group">
            <label for="user-email">E-mail<span class="errors">&#42;</span></label>
            <form:input path="email" cssClass="form-control" id="user-email"/>
            <form:errors path="email" cssClass="errors"></form:errors>
        </div>

        <div class="form-group">

            <label for="user-birthdate">Date of Birth</label>
            <div class="input-append date clearfix" id="user-birth"
                 data-date="" data-date-format="yyyy-mm-dd">
                <div class="form-inline col-sm-5">
                    <form:input path="birthDate" cssClass="pull-left form-control" id="user-birthdate"/>
                    <form:errors path="birthDate" cssClass="errors"></form:errors>
                    <span style="margin-left: 7px; margin-top: 7px" class="add-on pull-left"><i
                            class="glyphicon glyphicon-calendar"></i></span></div>
            </div>
            <p class="errors" id="valid-user-birthday"></p>

        </div>


        <div class="form-group">
            <label for="user-password">Password<span class="errors">&#42;</span></label>
            <form:password path="password" cssClass="form-control" id="user-password"/>
            <form:errors path="password" cssClass="errors"></form:errors>
        </div>
        <div class="form-group">
            <label for="user-confirm-password">Confirm Password<span class="errors">&#42;</span></label>
            <form:password path="confirmPassword" cssClass="form-control" id="user-confirm-password"/>
            <form:errors path="confirmPassword" cssClass="errors"></form:errors>
        </div>
        <sec:csrfInput/>
        <div class="button-spacing">
            <button type="submit" id="btn-save" class="btn btn-primary">Confirm</button>
        </div>

    </form:form>
</div>
</body>
</html>