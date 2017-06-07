<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="film">
	<div id="rating">
		<div class="ratinginfo">
			<p>
				<c:out value="${film.rating }"></c:out>
			</p>
		</div>
		<div class="ratingwrapper">
		
		</div>
	</div>
	<div class="main-film-wrapper">
		<div id="filmdetail">
			<p>
				<c:out value="${film.title}"></c:out>
			</p>
			<p>
				<c:out value="${film.year}"></c:out>
			</p>
			<p>
				<c:out value="${film.description}"></c:out>
			</p>
		</div>

		<div id="actorlist">
			<c:if test="${not empty actorfilm }">
				<h3>Cast</h3>
			</c:if>
			<c:forEach var="actorfilm" varStatus="status" items="${actorfilm}">

				<table id="actorinfilm">
					<tr>
						<td>Name:</td>
						<td><c:out value="${actorfilm.actor.name }" /></td>
					</tr>
					<tr>
						<td>Role:</td>
						<td><c:out value="${actorfilm.role }" /></td>
					</tr>
				</table>
			</c:forEach>


		</div>
		<sec:authorize access="isAuthenticated()">
			<sf:form class='actor-to-film' var='actorfilm'
				modelAttribute='actorFilm'
				action="${pageContext.request.contextPath }/actoraddedtofilm"
				method='POST'>
				<h5>Add actor</h5>
				<table>
					<tr>
						<td>Name:</td>
						<td><sf:input type='text' path='actor.name' /></td>
					</tr>
					<tr>
						<td>Role:</td>
						<td><sf:input type='text' path='role' /></td>
					</tr>
					<tr>
						<td colspan='2'><input name="submit" type="submit"
							value="submit" /></td>
					</tr>
				</table>
			</sf:form>
		</sec:authorize>
	</div>

</div>