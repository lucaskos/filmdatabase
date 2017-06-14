<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<ul>
<li><a href="<c:url value='/' />">Home</a></li>
<li><a href="<c:url value='/filmslist' />">Films List</a></li>
<li><a href="<c:url value='/actorlist' />">Actor List</a></li>
<li><a href="<c:url value='/addfilm' />">Add film</a></li>
<li><a href="<c:url value='/addactor' />">Add actor</a></li>
</ul>
	<sec:authorize access="!isAuthenticated() or isAnonymous()">
		<a class="login" href="<c:url value='/login' />">Log in</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a class="login" href="<c:url value='/logout' />">Logout</a>
	</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin' />">Admin</a>
</sec:authorize>