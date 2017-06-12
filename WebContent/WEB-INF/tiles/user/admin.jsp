<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>

<sf:form action="${pageContext.request.contextPath }/changeRole"
	>
	<h2>Create user</h2>
	<table>
		<c:forEach var="users" items="${users}">
			<tr>
				<td>USER ID:</td>
				<td><c:out value="${users.id }" /></td>
				<td>USERNAME:</td>
				<td class="username"><c:out value="${users.username }" /></td>

				<td>EMAIL:</td>
				<td><c:out value="${users.email }" /></td>
				<c:forEach var="roles" items="${users.usersRoles }">
					<td><c:out value="${roles.role }" /></td>
				</c:forEach>
				<td><select class="change-role">
						<c:forEach var='changeRole' items='${roles}'>

						<option value="${changeRole.role}" data-users="${users}" data-userid="${users.id }"
								data-username="${users.username }"><c:out
									value='${changeRole.role}' /></option>

						</c:forEach>
				</select></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

</sf:form>