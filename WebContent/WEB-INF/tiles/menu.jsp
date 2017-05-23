<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<a href="<c:url value='/' />">Home</a>
<a href="<c:url value='/filmslist' />">Book List</a>
<a href="<c:url value='/addfilm' />">Add book</a>

	<sec:authorize access="!isAuthenticated() or isAnonymous()">
		<a class="login" href="<c:url value='/login' />">Log in</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a class="login" href="<c:url value='/logout' />">Logout</a>
	</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin' />">Admin</a>
</sec:authorize>