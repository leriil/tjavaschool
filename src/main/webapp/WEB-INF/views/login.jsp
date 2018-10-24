
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tshop</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container" style="width: 300px;">
    <c:url value="/login" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" name="custom_username" placeholder="login" >
        <input type="password" class="form-control" name="custom_password" placeholder="Password">
        <c:if test="${param.error!=null}">
            <p>Invalid Username or Password</p>
        </c:if>
        <c:if test="${param.logout!=null}">
            <div class="alert alert-secondary alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                You've been successfully signed out.
            </div>
        </c:if>
        <%--<c:if test="${param.logout!=null}">--%>
            <%--<p>You have successfully been signed out</p>--%>
        <%--</c:if>--%>
        <sec:csrfInput/>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-secondary btn-block" type="submit">Sing in</button>
    </form>
</div>

</body>
</html>
