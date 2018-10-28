<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	var productCounter=0;
</script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<nav class="navbar navbar-default bg-light navbar-light fixed-top">

	<sec:authorize access="hasAuthority('CLIENT')" var="client"/>
	<sec:authorize access="hasAuthority('SALESPERSON')" var="salesperson"/>

		<div class="container-fluid">

			<div class="navbar-header">
      			<a class="navbar-brand" href="#">Tshop</a>
    		</div>
    		
    		<ul class="nav navbar-nav">
    		
    			<li><a href="<spring:url value="/product/all"/>">Home</a></li>

				<c:choose>
					<c:when test="${client}">

						<li class="dropdown">

							<a href="#" class="dropdown-toggle"
							   data-toggle="dropdown" role="button"
							   aria-expanded="false">Orders <span class="caret"></span></a>

							<ul class="dropdown-menu" role="menu">
								<li><a href="<spring:url value="/order/history"/>">History</a></li>
								<li><a href="<spring:url value="/order/place"/>">Place an order</a></li>
								<li><a href="<spring:url value="/order/status"/>">Status</a></li>
							</ul>

						</li>

						<li class="dropdown">
							<a href="#" class="dropdown-toggle"
							   data-toggle="dropdown" role="button"
							   aria-expanded="false">Welcome, <sec:authentication property="name"/>
								<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<spring:url value="/user/account"/>">Account</a></li>
								<li>
									<a class="logout" href="#">Sign out</a>
									<form class="logout-form" action="<spring:url value="/logout"/>" method="post">
									<sec:csrfInput/>
									</form>
								</li>
							</ul>
						</li>
					</c:when>

					<c:when test="${salesperson}">

						<li class="dropdown">

							<a href="#" class="dropdown-toggle"
							   data-toggle="dropdown" role="button"
							   aria-expanded="false">Statistics <span class="caret"></span></a>

							<ul class="dropdown-menu" role="menu">
								<li><a href="<spring:url value="/user/find/top"/>">Top 10 clients</a></li>
								<li><a href="<spring:url value="/product/find/top"/>">Top 10 products</a></li>
								<li><a href="<spring:url value="/profit/month/"/>">Month profit</a></li>
								<li><a href="<spring:url value="/profit/week"/>">Week profit</a></li>
							</ul>

						</li>

						<li class="dropdown">

							<a href="#" class="dropdown-toggle"
							   data-toggle="dropdown" role="button"
							   aria-expanded="false">Products <span class="caret"></span></a>

							<ul class="dropdown-menu" role="menu">

								<li><a href="<spring:url value="/product/add"/>">Add product</a></li>
								<li><a href="<spring:url value="/product/category/add"/>">Add category</a></li>
								<li><a href="<spring:url value="/product/fileImport"/>">Import from file</a></li>

							</ul>

						</li>

						<li class="dropdown">
							<a href="#" class="dropdown-toggle"
							   data-toggle="dropdown" role="button"
							   aria-expanded="false">Welcome, <sec:authentication property="name"/>
								<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<spring:url value="/user/account"/>">Account</a></li>
								<li>
									<a class="logout" href="#">Sign out</a>
									<form class="logout-form" action="<spring:url value="/logout"/>" method="post">
										<sec:csrfInput/>
									</form>
								</li>
							</ul>
						</li>

					</c:when>

					<c:otherwise>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"
							   data-toggle="dropdown" role="button"
							   aria-expanded="false">Profile
								<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<spring:url value="/login"/>">Sign in</a></li>
								<li><a href="<spring:url value="/register"/>">Register</a></li>
							</ul>
						</li>
					</c:otherwise>
				</c:choose>

        		<li>
					<button type="button" class="btn" onclick="window.location.href='<spring:url value="/order/place"/>'">Basket
						<span id="basket" class="badge badge-light"></span>
					</button>
				</li>
    		</ul>
    		
		</div>
</nav>
