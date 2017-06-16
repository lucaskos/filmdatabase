<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="film">
	<div class="row">
		<div class="pull-right">
			<div class="rating">
				<div class="ratinginfo">
					<p>
						<c:out value="${filmRating}"></c:out>
					</p>
				</div>
				<sec:authorize access="isAuthenticated()">
					<div class="ratingwrapper"></div>
				</sec:authorize>
			</div>
		</div>
		<div class="pull-left">

			<div class="filmdetails">
				<div id="filmdetail">
					<p class="filmid" hidden="">
						<c:out value="${film.filmId}"></c:out>
					</p>
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
				<sec:authorize access="hasAnyRole('ADMIN', 'PREMIUM')">
					<sf:form action='${pageContext.request.contextPath}/removeFilm'
						method='GET' commandName="film">
						<button name="filmId" value="${film.filmId}">REMOVE</button>
					</sf:form>

					<sf:form action='${pageContext.request.contextPath}/editfilm'
						method='GET' commandName="film">
						<button name="filmId" value="${film.filmId}">EDIT</button>
					</sf:form>


					<input type="text" id="w-input-search" value=""
						placeholder="Search actor">
					<input type="text" id="role-in-film" value="" placeholder="What wast the role?">
					<span>
						<button id="button-id" type="button">Search</button>
					</span>
				</sec:authorize>
			</div>
		</div>
	</div>
</div>
