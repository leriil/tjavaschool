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
    <script src="<spring:url value="/resources/js/moment.js"/>"></script>
    <script src="<spring:url value="/resources/js/accounting.js"/>"></script>
    <script src="<spring:url value="/resources/js/global.js"/>"></script>
    <script src="<spring:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<spring:url value="/resources/js/dates.js"/>"></script>
    <script src="<spring:url value="/resources/js/orders.js"/>"></script>

    <script>var ctx = "${pageContext.request.contextPath}"</script>
    <sec:csrfMetaTags/>

    <%--<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>--%>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container">
    <div>
        <h1>Orders</h1>

        <h3>Get orders for a period of time by choosing the first and last days of that period</h3>

        <div class="form-inline">
            <div class="form-group">
                <label for="first-day">First Day</label>
                <div class="input-append date clearfix"
                     data-date="" data-date-format="yyyy-mm-dd">
                    <div>
                        <input class=" form-control pull-left" id="first-day"/>
                        <span style="margin-left: 7px; margin-top: 7px;" class="add-on pull-left"><i
                                class="glyphicon glyphicon-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="last-day">Last Day</label>
                <div class="input-append date clearfix"
                     data-date="" data-date-format="yyyy-mm-dd">
                    <div>
                        <input class="form-control pull-left" id="last-day"/>
                        <span style="margin-left: 7px; margin-top: 7px" class="add-on pull-left"><i
                                class="glyphicon glyphicon-calendar"></i></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-inline button-spacing">
            <button id="getOrders" type="submit" class="btn btn-primary mb-2">OK
            </button>
        </div>

        <p id="validDateOrders" class="errors"></p>

        <table id="allOrdersTable" class="table table-hover table-striped">
            <tbody>
            <tr>
                <th>Order Id</th>
                <th>User Name</th>
                <th>Payment Method</th>
                <th>Delivery Method</th>
                <th>Payment Status</th>
                <th>Order Status</th>
                <th>Order Date</th>
            </tr>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>