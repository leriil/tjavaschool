<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script src="<spring:url value="/resources/js/accounting.js"/>"></script>
    <script src="<spring:url value="/resources/js/moment.js"/>"></script>
    <script src="<spring:url value="/resources/js/global.js"/>"></script>
    <script src="<spring:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<spring:url value="/resources/js/dates.js"/>"></script>
    <script src="<spring:url value="/resources/js/orders.js"/>"></script>
    <script src="<spring:url value="/resources/js/profit.js"/>"></script>

    <script>var ctx = "${pageContext.request.contextPath}"</script>
    <sec:csrfMetaTags/>

    <%--<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>--%>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container">
    <h1>Orders</h1>


    <div class="form-group col-md-3">
        <label for="profitSelect">Get profit for last:</label>
        <select class="form-control" id="profitSelect">
            <option>week</option>
            <option>month</option>
            <option>year</option>
        </select>
    </div>


    <div class="row">
        <div class="col-md-6">
            <div class="form-inline">
                <div class="form-group">
                    <label for="profit-first-day">First Day</label>
                    <div class="input-append date clearfix"
                         data-date="" data-date-format="yyyy-mm-dd">
                        <div>
                            <input class=" form-control pull-left" id="profit-first-day"/>
                            <span style="margin-left: 7px; margin-top: 7px" class="add-on pull-left"><i
                                    class="glyphicon glyphicon-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="profit-last-day">Last Day</label>
                    <div class="input-append date clearfix"
                         data-date="" data-date-format="yyyy-mm-dd">
                        <div>
                            <input class="form-control pull-left" id="profit-last-day"/>
                            <span style="margin-left: 7px; margin-top: 7px" class="add-on pull-left"><i
                                    class="glyphicon glyphicon-calendar"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row button-spacing">
        <div class="form-group">
            <button id="profitButton" type="submit" style="margin-left: 60px" class="btn btn-primary mb-2">OK
            </button>
        </div>
    </div>

    <p id="validDate" class="errors"></p>


    <table id="profitTable" class="table table-hover table-striped">
        <tbody>
        <tr>

            <th>Date</th>
            <th>Profit</th>
            <th>Products sold</th>

        </tr>
        </tbody>
    </table>


</div>
</body>
</html>
