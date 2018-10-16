<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/global.js"/>"></script>

<a href="<spring:url value="/login"/>" class="btn btn-default">sign in</a>
<%--<sec:authorize access="!isAuthenticated()">--%>
    <%--<p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>--%>
<%--</sec:authorize>--%>
</body>
</html>