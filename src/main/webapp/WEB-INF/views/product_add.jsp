<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/resource.js"/>"></script>
	<script src="<c:url value="/resources/js/global.js"/>"></script>
	<script src="<c:url value="/resources/js/cartNumber.js"/>"></script>
	<sec:csrfMetaTags/>
    <script>var ctx = "${pageContext.request.contextPath}"</script>

</head>
<body>
	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">
	
		<div class="row">
			<h1>Product</h1>
		</div>
		
		<spring:url value="/product/review" var="formUrl"/>
		<form:form action="${formUrl}" method="POST" modelAttribute="product">
			
			<div class="row">

				<div class="form-group">
					<label for="product-name">Name</label>
					<form:input path="name" cssClass="form-control" id="product-name"/>
					<%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
				</div>

				<div class="form-group">
					<label for="product-price">Price</label>
					<form:input path="price" cssClass="form-control" id="product-price"/>
						<%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
				</div>
				<div class="form-group">
					<label for="product-weight">Weight</label>
					<form:input path="weight" cssClass="form-control" id="product-weight"/>
						<%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
				</div>

				<div class="form-group">
					<label for="product-volume">Volume</label>
					<form:input path="volume" cssClass="form-control" id="product-volume"/>
						<%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
				</div>

				<div class="form-group">
					<label for="product-inStock">inStock</label>
					<form:input path="inStock" cssClass="form-control" id="product-inStock"/>
						<%--<input type="text" id="resource-name" class="form-control" name="name" />--%>
				</div>

				<div class="form-group">
					<label for="product-category">Category</label>
					<form:select path="category" items="${categoryOptions}" cssClass="selectpicker" id="product-category"></form:select>
				</div>


                <%--<a id="request_link" href="<spring:url value="/resource/request"/>">Send Request</a>--%>
				
				<button type="submit" class="btn btn-default">Submit</button>

			</div>
		
		</form:form>
		
	</div>
</body>
</html>