<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Card</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/resource.js"/>"></script>
    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <sec:csrfMetaTags/>
    <script>var ctx = "${pageContext.request.contextPath}"</script>

</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>

<div class="container">
    <c:if test="${param.paymentProblem==true}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        There has been a problem with the transaction.
        Either there are not enough sufficient funds on your account or the information you entered isn't correct.
        </div>
        <div class="alert alert-info alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            Try again or contact your bank for further details.
        </div>
    </c:if>

    <div class="row">
        <h1>Card</h1>
    </div>

    <spring:url value="/order/pay" var="formUrl"/>
    <form:form action="${formUrl}" method="POST" modelAttribute="card">

        <div class="row">

            <div class="form-group">
                <label for="card-number">Card Number</label>
                <form:input path="cardNumber" cssClass="form-control" id="card-number"/>
            </div>

            <div class="form-group">
                <label for="card-user-name">Name</label>
                <form:input path="userName" cssClass="form-control" id="card-user-name"/>
            </div>

            <div class="form-group">
                <label for="card-cvv">CVV</label>
                <form:input path="cvv" cssClass="form-control" id="card-cvv"/>
            </div>

            <button type="submit" class="btn btn-success">OK</button>

        </div>

    </form:form>
    <p>


    </p>

</div>
</body>
</html>
