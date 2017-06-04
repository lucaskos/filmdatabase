<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Films list</h1>

<table class="booklist">
	<tr>
		<th>Title</th>
		<th>Year</th>
		<th>Description</th>
		<th>Rating</th>
	</tr>

	<c:forEach var="film" items="${film}">
<a href="${film.id}">
		<tr>

			<td><c:out value="${film.title }" /></td>
			<td><c:out value="${film.year }" /></td>
			<td><c:out value="${film.description }" /></td>
			<td><c:out value="${film.rating }" /></td>
			<c:forEach var="actors" items="${film.actors }">
				<c:out value="${actors.name}"></c:out>
			</c:forEach>

		</tr>
		</a>
	</c:forEach>

</table>