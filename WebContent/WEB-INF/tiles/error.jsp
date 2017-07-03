<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<span class="alert alert-danger">Error page</span>
	<div class="error-details alert alert-danger">
		<c:out value="${datetime}"></c:out><br/>
		<strong><c:out value="${exception.message}"></c:out></strong><br/>
		<c:out value="${url}"></c:out>
	</div>
	<c:if test="${not empty msg}">
		<h2>${msg}</h2>
	</c:if>

	<c:if test="${not empty errMsg}">
		<h2>${errMsg}</h2>
	</c:if>

</div>