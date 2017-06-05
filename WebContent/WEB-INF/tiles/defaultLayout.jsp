<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<link href="static/css/main.css" type="text/css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<tiles:insertAttribute name="includes"></tiles:insertAttribute>
<title>Insert title here</title>
</head>
<body>
	<div>
		<tiles:insertAttribute name="header" />
	</div>
	<menu>
		<tiles:insertAttribute name="menu" />
	</menu>
	<div class="content">
		<tiles:insertAttribute name="content" />
	</div>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>