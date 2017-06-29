<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">
	$('.dropdown-toggle').on('click', function() {
		$($(this).attr('data-target')).slideToggle();
	});
</script>
<nav class="navbar navbar-inverse bg-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">FDB</a>
		</div>

		<div style="display: inline-block;">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a class="glyphicon glyphicon-home"
						href="<c:url value='/home' />"></a></li>
					<li class="dropdown"><a class="dropdown-toggle" href="#"
						data-toggle="dropdown">Films <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							<li><a href="<c:url value='/filmslist' />">Films List</a></li>
							<li><a href="<c:url value='/addfilm' />">Add film</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Actors <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/actorlist' />">Actor List</a></li>
							<li><a href="<c:url value='/addactor' />">Add actor</a></li>
						</ul></li>
					<li><a href="<c:url value='/admin' />">Admin</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="!isAuthenticated() or isAnonymous()">
						<li><a class="login" href="<c:url value='/login' />">Log
								in</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a class="login" href="<c:url value='/logout' />">Logout</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
</nav>
