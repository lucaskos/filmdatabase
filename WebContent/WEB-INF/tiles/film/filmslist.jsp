<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="list">
	<h1>Films list</h1>
	<table class="table-list">
		<thead>
			<tr>
				<th>Title</th>
				<th>Year</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="film" items="${film}">
				<tr>
					<td data-href='<c:out value="${film.filmId }" />'><c:out
							value="${film.title }" /></td>
					<td><c:out value="${film.year }" /></td>
					<td><c:out value="${film.description }" /></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</div>