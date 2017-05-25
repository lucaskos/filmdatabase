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
<table>
	<tr>
		<th>Id</th>
		<th>Username</th>
		<th>Password</th>
		<th>Email</th>
		<th>Roles</th>
	</tr>
	<c:forEach var="users" items="${users}">
		<tr>
			<td><c:out value="${users.id }" /></td>
			<td><c:out value="${users.username }" /></td>
			<td><c:out value="${users.password }" /></td>
			<td><c:out value="${users.email }" /></td>
			<c:forEach var="roles" items="${users.usersRoles }">
				<td><c:out value="${roles.role }" /></td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>