<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="list">
	<h1>Films list</h1>
	<table class="table-list">
		<tr>
			<th>Title</th>
			<th>Year</th>
			<th>Description</th>
			<th>Rating</th>
		</tr>

		<c:forEach var="film" items="${film}">
			<tr data-href='<c:out value="${film.filmId }" />'>

				<td><c:out value="${film.title }" /></td>
				<td><c:out value="${film.year }" /></td>
				<td><c:out value="${film.description }" /></td>
				<td><c:out value="${film.rating }" /></td>
			</tr>

		</c:forEach>

	</table>
</div>