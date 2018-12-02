<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/global.css"/>" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<c:url value="/resources/js/accounting.js"/>"></script>
    <script>var ctx = "${pageContext.request.contextPath}"</script>
    <%--<sec:csrfMetaTags/>--%>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container">

    <h2>All Products</h2>

    <c:if test="${param.paymentProblem==false}" >
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        You've successfully made your purchase! You can follow the status and the location of your order in your "History".
        </div>
    </c:if>
    <c:if test="${param.placementProblem==false}" >
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            You've successfully placed your order! You can follow the status and the location of your order in your "History".
        </div>
    </c:if>

    <table id="allProductsTable" class="table table-hover table-striped">
        <tbody>
        <tr class="sortTable" data-toggle="tooltip" title="click the column name to sort the products">
            <th id="columnName">Name</th>
            <th id="columnPrice">Price</th>
            <th>Weight</th>
            <th>Volume</th>
            <th>in Stock</th>
            <th>Category</th>
        </tr>

        <%--<c:forEach items="${products}" var="product">--%>
            <%--<tr>--%>
                <%--<td><a href="<spring:url value="/product/${product.productId}"/>">${product.name}</a></td>--%>
                <%--<td>${product.price}</td>--%>
                <%--<td>${product.weight}</td>--%>
                <%--<td>${product.volume}</td>--%>
                <%--<td>${product.inStock}</td>--%>
                <%--<td>${product.category}</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>

        </tbody>
    </table>
</div>
</body>
</html>
