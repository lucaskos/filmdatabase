<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p>Film</p>

<c:out value="${film.id }"></c:out>
<c:out value="${film.title }"></c:out>
<c:out value="${film.year }"></c:out>
<c:forEach var="actors" items="${film.actors }">
	<c:out value="${actors.name}"></c:out>
</c:forEach>