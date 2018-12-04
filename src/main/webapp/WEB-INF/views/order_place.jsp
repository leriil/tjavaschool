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
    <link rel="stylesheet" href="<spring:url value="/resources/css/global.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<c:url value="/resources/js/removeFromCart.js"/>"></script>
    <sec:csrfMetaTags/>
    <script>var ctx = "${pageContext.request.contextPath}"</script>


</head>

<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<sec:authorize access="hasAnyAuthority('IS_AUTHENTICATED_ANONYMOUSLY','CLIENT')" var="anonymousAndClient"/>
<div class="container">
    <h1>Order details</h1>
    <spring:url value="/order/review" var="formUrl"/>

    <div class="container">

        <c:if test="${productsInCart.size() > 0}">
            <h1>Products in your cart</h1>
            <table class="table table-hover">
                <tbody>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Weight</th>
                    <th>Color</th>
                    <th>Category</th>
                    <c:if test="${anonymousAndClient}">
                <th></th>
                    </c:if>

                </tr>
                <c:forEach items="${productsInCart}" var="p" varStatus="loop">
                    <tr>
                        <td id="productForRemoval-${loop.index}">${p.productId}</td>
                        <td><a href="<spring:url value="/product/${p.productId}"/>">${p.name}</a></td>
                        <td>${p.price}</td>
                        <td>${p.weight}</td>
                        <td>${p.color}</td>
                        <td>${p.category.categoryName}</td>

                        <c:if test="${anonymousAndClient}">
                    <td>
                        <button class="btn btn-danger" id="removeFromCart-${loop.index}"
                                onclick="removeProduct(${p.productId})">
                            <span class="glyphicon glyphicon-trash"></span></button>
                    </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <tr class="info">
                    <td/>
                    <td/>
                    <td/>
                    <td/>
                    <td/>
                    <td><b>Total</b></td>
                    <td><span id="total"/></td>
                </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${productsInCart.size()==0}">
            <h4>There are currently no products in your cart.</h4>
        </c:if>

    </div>

    <form:form action="${formUrl}" method="POST" modelAttribute="order">
    <c:if test="${productsInCart.size()>0}">
        <h2>1.Client</h2>
        <div class="row">

            <div class="form-group">
                <label for="order-user-name">Name</label>
                <form:input path="user.name" cssClass="form-control" id="order-user-name"/>

            </div>

            <div class="form-group">
                <label for="order-user-surname">Surname</label>
                <form:input path="user.surname" cssClass="form-control" id="order-user-surname"/>

            </div>
        </div>
        <h2>2.Shipping Address</h2>
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
                <label for="order-house">House</label>
                <form:input path="address.house" cssClass="form-control" id="order-house"/>

            </div>

            <div class="form-group">
                <label for="order-flat">Flat</label>
                <form:input path="address.flat" cssClass="form-control" id="order-flat"/>

            </div>
        </div>

        <h2>3.Payment and Delivery</h2>
        <div class="row">

            <div class="form-group">
                <label for="order-delivery-method">Delivery Method</label>
                <form:select path="deliveryMethod" items="${deliveryOptions}"
                             cssClass="selectpicker" id="order-delivery-method"/>
            </div>

            <div class="form-group">
                <label for="order-payment-method">Payment Method</label>
                <form:select path="paymentMethod" items="${paymentOptions}"
                             cssClass="selectpicker" id="order-payment-method"/>
            </div>
        </div>


        <div class="button-spacing">
            <button type="submit" class="btn btn-primary button-spacing">Submit</button>
        </div>
    </c:if>
</div>


</form:form>


</body>
</html>
