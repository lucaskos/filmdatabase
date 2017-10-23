<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="row">
	<div class="col-md-10 col-md-offset-2">
		<div class="item-info">
			<c:out value="${actor.name}"></c:out>
		</div>
		<div class="details">
			<h5>Filmography</h5>
			<c:forEach var="film" items="${films}">
				<p>
					In <a href="/films/film/<c:out value='${film.key.filmId}'/>"><c:out value="${film.key.title}"></c:out></a> as <c:out value="${film.value}"></c:out>
				</p>
			</c:forEach>
		</div>
	</div>
</div>
<div>
    <tiles:insertAttribute name="commentsList" />
</div>