<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="list">
	<h1>Actor list</h1>

	<table id="table-list">
		<tr>
			<th>Name</th>
		</tr>

		<c:forEach var="actor" items="${actor}">
			<tr>
				<td data-href='<c:out value="${actor.id }" />'><c:out value="${actor.name }" /></td>
			</tr>

		</c:forEach>

	</table>

</div>