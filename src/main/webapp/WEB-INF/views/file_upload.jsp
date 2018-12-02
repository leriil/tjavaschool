<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tshop</title>
    <link rel="stylesheet" href="<spring:url value="/resources/css/global.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/resources/css/file_upload.css"/>"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" type="text/javascript"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css"/>
    <script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

    <script src="<c:url value="/resources/js/global.js"/>"></script>
    <script src="<c:url value="/resources/js/addToCart.js"/>"></script>
    <script src="<c:url value="/resources/js/removeFromCart.js"/>"></script>
    <script src="<c:url value="/resources/js/file_upload.js"/>"></script>

    <security:csrfMetaTags/>
</head>
<!------ Include the above in your HEAD tag ---------->
<body>
<jsp:include page="../views/fragments/header.jsp"></jsp:include>
<div class="container"><br/>
    <div class="row">
        <c:if test="${param.fileUploaded==true}">
            <div class="alert alert-success alert-dismissable">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                Your file has been successfully uploaded and the product is now stored in the database.
            </div>
        </c:if>
        <c:if test="${param.fileUploaded==false}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                There has been a problem with uploading a file.
            </div>
        </c:if>
        <form:form method="POST" action="${pageContext.request.contextPath}/product/upload"
                   enctype="multipart/form-data">
            <div class="col-md-12">
                <div class="panel panel-default" style="margin-top: 30px">
                    <div class="panel-heading"><strong>Upload files</strong>
                        <small></small>
                    </div>
                    <div class="panel-body">
                        <div class="input-group image-preview">
                            <input id="fileName" placeholder="your file.." disabled="disabled" type="text"
                                   class="form-control image-preview-filename">
                            <span class="input-group-btn">
						<button type="button" class="btn btn-default image-preview-clear" style="display:none;">
                            <span class="glyphicon glyphicon-remove"></span> Clear </button>
						<div class="btn btn-default image-preview-input">
                            <span class="glyphicon glyphicon-folder-open"></span>
                            <span class="image-preview-input-title">Browse</span>
							<input type="file" name="product_file" id="fileUpload"/>
						</div>
						<button type="submit" class="btn btn-labeled btn-primary">
                            <span class="btn-label"><i class="glyphicon glyphicon-upload"></i>
                            </span>Upload</button>
						</span></div>

                        <br/>

                            <%--<!-- Drop Zone -->--%>
                            <%--<div class="upload-drop-zone" id="drop-zone"> Or drag and drop files here </div>--%>
                        <br/>
                        <!-- Progress Bar -->
                            <%--<div class="progress">--%>
                            <%--<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"> <span class="sr-only">60% Complete</span> </div>--%>
                            <%--</div>--%>
                        <br/>

                    </div>
                </div>
            </div>
        </form:form>
    </div>

</div>

</body>
</html>

