<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Review</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	<%--href="<spring:url value="/resources/css/home.css"/>" type="text/css" />--%>
<link rel="stylesheet"
	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
	<script src="<c:url value="/resources/js/global.js"/>"></script>
	<script src="<c:url value="/resources/js/addToCart.js"/>"></script>

</head>
<body>
	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h1>Please review the order for accuracy</h1>
			<h3>Products</h3>
			<c:forEach items="${productsInCart}" var="p">
				<div class="form-group">
					<label>Name</label> <span>${p.name}</span>
				</div>
				<div class="form-group">
					<label>Price</label> <span>${p.price }</span>
				</div>

				<div class="form-group">
					<label>Weight</label> <span>${p.weight}</span>
				</div>

				<div class="form-group">
					<label>Volume</label> <span>${p.volume}</span>
				</div>
				<div class="form-group">
					<label>Category</label> <span>${p.category}</span>
				</div>
			</c:forEach>
			<h3>Shipping Address</h3>
			<div class="form-group">
				<label>Name</label> <span>${order.user.name}</span>
			</div>

			<div class="form-group">
				<label>Surname</label> <span>${order.user.surname }</span>
			</div>
			<div class="form-group">
				<label>Country</label> <span>${order.address.country}</span>
			</div>

			<div class="form-group">
				<label>City</label> <span>${order.address.city }</span>
			</div>

			<div class="form-group">
				<label>Zipcode</label> <span>${order.address.zipCode}</span>
			</div>

			<div class="form-group">
				<label>Street</label> <span>${order.address.street}</span>
			</div>
			<div class="form-group">
				<label>House</label> <span>${order.address.house}</span>
			</div>
			<div class="form-group">
				<label>Flat</label> <span>${order.address.flat}</span>
			</div>
			<h3>3.Payment and shipping</h3>
			<div class="form-group">
				<label>Payment Method</label> <span>${order.paymentMethod}</span>
			</div>

			<div class="form-group">
				<label>Delivery Method</label> <span>${order.deliveryMethod }</span>
			</div>


			<%--<div class="form-group">--%>
				<%--<label>Indicators</label>--%>
				<%--<c:forEach var="indicator" items="${resource.indicators}">--%>
					<%--<span>${indicator}</span>--%>
				<%--</c:forEach>--%>
			<%--</div>--%>

			<a href="<spring:url value="/order/place"/>" class="btn btn-default">Edit</a>
			<a href="<spring:url value="/order/save"/>" class="btn btn-default">Confirm</a>
			<p>


			</p>
		</div>
	</div>
</body>
</html>