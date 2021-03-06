<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<script>
	var ctx = "${pageContext.request.contextPath}"
</script>
<div class="alert alert-warning">${title}<br/>${message}</div>

<sf:form action="${pageContext.request.contextPath }/changeRole">
	<table id="table-list" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>User ID:</th>
				<th>Username</th>
				<th>Email</th>
				<th>Current Role</th>
				<th>Change Role</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${users not null}">
			<c:forEach var="users" items="${users}">
				<tr>
					<td><c:out value="${users.id }" /></td>
					<td class="username"><c:out value="${users.username }" /></td>

					<td><c:out value="${users.email }" /></td>
					<td><c:out value="${users.role.role }" /></td>
					<td><select class="change-role">
							<option value="default" selected="selected">Select
								option</option>
							<c:forEach var='changeRole' items='${roles}'>
								<option value="${changeRole.role}" data-users="${users}"
									data-userid="${users.id }" data-username="${users.username }"><c:out
										value='${changeRole.role}' /></option>

							</c:forEach>
					</select></td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
</sf:form>