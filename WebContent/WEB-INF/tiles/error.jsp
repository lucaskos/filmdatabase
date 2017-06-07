<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p>Error page</p>
	<c:if test="${not empty msg}">
		<h2>${msg}</h2>
	</c:if>
	
		<c:if test="${not empty errMsg}">
		<h2>${errMsg}</h2>
	</c:if>