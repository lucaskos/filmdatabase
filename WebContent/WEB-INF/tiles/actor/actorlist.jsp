<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="list">
	<h1>Actor list</h1>
	<table id="table-list" class="table-hover">
		<thead>
			<tr>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="actor" items="${actor}">
				<tr>
					<td class="table-details" title="Click on actor name to see details." data-href='<c:out value="${actor.id }" />'><c:out
							value="${actor.name }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>