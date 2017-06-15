<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="nav">
	<div class="container">
		<ul class="pull-left">
			<li><a href="<c:url value='/' />">Home</a></li>
			<li><a href="<c:url value='/filmslist' />">Films List</a></li>
			<li><a href="<c:url value='/actorlist' />">Actor List</a></li>
			<li><a href="<c:url value='/addfilm' />">Add film</a></li>
			<li><a href="<c:url value='/addactor' />">Add actor</a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="<c:url value='/admin' />">Admin</a></li>
			</sec:authorize>
		</ul>

		<sec:authorize access="!isAuthenticated() or isAnonymous()">
			<ul class="pull-right">
				<li><a class="login" href="<c:url value='/login' />">Log in</a></li>
			</ul>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<ul class="pull-right">
				<li><a class="login" href="<c:url value='/logout' />">Logout</a></li>
			</ul>
		</sec:authorize>
	</div>
</div>