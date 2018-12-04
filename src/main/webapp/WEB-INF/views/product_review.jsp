<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Product Review</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="<spring:url value="/resources/css/bootstrap-select.min.css"/>"
          type="text/css"/>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script
            src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <sec:csrfMetaTags/>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <h2>Please review the product for accuracy</h2>

        <div class="form-group">
            <label>Name</label> <span>${product.name}</span>
        </div>

        <div class="form-group">
            <label>Price</label> <span>${product.price }</span>
        </div>

        <div class="form-group">
            <label>Weight</label> <span>${product.weight}</span>
        </div>

        <div class="form-group">
            <label>Color</label> <span>${product.color}</span>
        </div>
        <div class="form-group">
            <label>inStock</label> <span>${product.inStock}</span>
        </div>
        <div class="form-group">
            <label>Category</label> <span>${product.category.categoryName}</span>
        </div>


        <%--<div class="form-group">--%>
        <%--<label>Indicators</label>--%>
        <%--<c:forEach var="indicator" items="${resource.indicators}">--%>
        <%--<span>${indicator}</span>--%>
        <%--</c:forEach>--%>
        <%--</div>--%>

        <a href="<spring:url value="/product/add"/>" class="btn btn-default">Edit</a>
        <a href="<spring:url value="/product/save"/>" class="btn btn-default">Save</a>
        <p>


        </p>
    </div>
</div>
</body>
</html>