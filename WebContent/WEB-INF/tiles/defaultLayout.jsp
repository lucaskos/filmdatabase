<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<tiles:importAttribute name="javascripts" />
<tiles:importAttribute name="stylesheets" />
<!DOCTYPE>
<html>
<head>

<!-- stylesheets-->
<c:forEach var="css" items="${stylesheets}">
	<link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
</c:forEach>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<tiles:insertAttribute name="includes"></tiles:insertAttribute>
<title>Films Database</title>
</head>
<body>
	<div class="container-fluid">
		<header>
			<tiles:insertAttribute name="header" />
		</header>
		<menu>
			<tiles:insertAttribute name="menu" />
		</menu>
		<div class="container">
			<div class="col-lg-12 col-md-10 col-sm-6 col-xs-12">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>

		<c:forEach var="script" items="${javascripts}">
			<script src="<c:url value="${script}"/>"></script>
		</c:forEach>
	</div>
</body>
</html>
