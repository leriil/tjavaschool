
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css">--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <script>var ctx = "${pageContext.request.contextPath}"</script>
    <sec:csrfMetaTags/>

    <%--<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>--%>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container">
    <h2>User</h2>
    <table class="table table-hover table-striped">
        <tbody>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>E-mail</th>
            <th>Birth date</th>
            <th>Country</th>
            <th>City</th>
            <th>Zip code</th>
            <th>Street</th>
            <th>House</th>
            <th>Flat</th>
        </tr>
        <tr>
            <th>${user.userId}</th>
            <td>${user.username}</td>
            <td>${user.surname}</td>
            <td>${user.email}</td>
            <td>${user.birthDate}</td>
            <td>${user.address.country}</td>
            <td>${user.address.city}</td>
            <td>${user.address.zipCode}</td>
            <td>${user.address.street}</td>
            <td>${user.address.house}</td>
            <td>${user.address.flat}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
