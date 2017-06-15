<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div>

<c:out value="${actor.name}"></c:out>
<div>
<h5>Filmography</h5>
<c:forEach var="film" items="${films}">
	<p><c:out value="${film.key.title}"></c:out></p>
	<p><c:out value="${film.value}"></c:out></p>
</c:forEach>
</div>
</div>