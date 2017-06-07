<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="filmdetails">

	<table>
		<tr>
			<td><c:out value="${film.title}"></c:out></td>
		</tr>
		<tr>
			<td><c:out value="${film.year}"></c:out></td>
		</tr>
		<tr>
			<td><c:out value="${film.description}"></c:out></td>
		</tr>
	</table>

	<div class="actorlist">

		<table>
			<c:forEach var="actorfilm" items="${actorfilm}">
				<tr>
					<td>Name:</td>
					<td><c:out value="${actorfilm.actor.name }" /></td>
				</tr>
				<tr>
					<td>Role:</td>
					<td><c:out value="${actorfilm.role }" /></td>
				</tr>

			</c:forEach>
		</table>

	</div>
	<sec:authorize access="isAuthenticated()">
		<sf:form class='actor-to-film' var='actorfilm'
			modelAttribute='actorFilm'
			action="${pageContext.request.contextPath }/actoraddedtofilm"
			method='POST'>
			<h5>Add actor</h5>
			<table>
				<tr>
					<td>Name:</td>
					<td><sf:input type='text' path='actor.name' /></td>
				</tr>
				<tr>
					<td>Role:</td>
					<td><sf:input type='text' path='role' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>
		</sf:form>
	</sec:authorize>
</div>