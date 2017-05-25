<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>

<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
	<h2>
		Welcome : ${pageContext.request.userPrincipal.name} | <a
			href="javascript:formSubmit()"> Logout</a>
	</h2>
	<p>this is custom made form</p>
</c:if>

<c:forEach var="users" items="${users}">
		<tr>
		<td><c:out value="${users.id }"/></td>
		<td><c:out value="${users.username }"/></td>
		<td><c:out value="${users.password }"/></td>
		<td><c:out value="${users.email }"/></td>
		</tr>
	</c:forEach>