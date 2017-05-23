<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>this is gonna be list of books</h1>

<table class="booklist">
	<tr>
		<th>Title</th>
		<th>Author</th>
		<th>Isbn</th>
	</tr>
	<c:forEach var="books" items="${books}">
		<tr>
		<td><c:out value="${books.title }"/></td>
		<td><c:out value="${books.author.firstName }"/></td>
		<td><c:out value="${books.isbn }"/></td>
		</tr>
	</c:forEach>
</table>