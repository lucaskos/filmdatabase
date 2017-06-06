<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Actor list</h1>

<table class="actorlist">
	<tr>
		<th>Name</th>
	</tr>

	<c:forEach var="actor" items="${actor}">
		<tr data-href='<c:out value="${film.filmId }" />'>

			<td><c:out value="${actor.name }" /></td>
		</tr>
		
	</c:forEach>

</table>