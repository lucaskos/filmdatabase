<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<c:out value="${film.title}"></c:out>
<c:out value="${film.year}"></c:out>
<c:out value="${film.description}"></c:out>
<table>
	<c:forEach var="actorsMap" items="${actorsMap}">
		<tr>
			<td>Name:</td>
			<td><c:out value="${actorsMap.key.name }" /></td>
		</tr>
		<tr>
			<td>Role:</td>
			<td><c:out value="${actorsMap.value }" /></td>
		</tr>

	</c:forEach>
</table>
<sf:form class='actor-to-film' var='actor' modelAttribute='actor'
	action="${pageContext.request.contextPath }/actoraddedtofilm"
	method='POST'>




	<h5>Add actor</h5>
	<table>

		<tr>
			<td>Name:</td>
			<td><sf:input type='text' path='name' />
				<div class='error'>
					<sf:errors path='name' />
				</div></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>
</sf:form>