<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product</title>
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
<sec:authorize access="hasAuthority('SALESPERSON')" var="salesperson"/>
<div class="container">
    <h2>Product</h2>
    <table class="table table-hover table-striped">
        <tbody>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Weight</th>
            <th>Volume</th>
            <th>inStock</th>
            <th>Category</th>
        </tr>
        <tr>
            <th id="productForCart">${product.productId}</th>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.weight.intValue()}</td>
            <td>${product.volume}</td>
            <td>${product.inStock}</td>
            <td>${product.category.categoryName}</td>
        </tr>
        <%--<sec:csrfInput/>--%>
        </tbody>
    </table>
    <%--TODO:hide this button from salesperson--%>
    <c:choose>
        <c:when test="${salesperson}">
        </c:when>
        <c:otherwise>
            <button class="btn btn-primary button-spacing" id="addToCart">Add to Cart</button>
        </c:otherwise>
    </c:choose>

    <!-- The Modal -->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Cart</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body align-content-md-center">
                    Product ${product.name} has been added to your cart.

                </div>

                <!-- Modal footer -->
                <div class="modal-footer align-content-lg-center">
                    <a href="<spring:url value="/order/place"/>" class="btn btn-success" role="button" id="addToCart">
                        Check out</a>
                    <a href="<spring:url value="/product/all"/>" class="btn btn-success" role="button">Continue
                        shopping</a>
                    <%--<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>--%>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
