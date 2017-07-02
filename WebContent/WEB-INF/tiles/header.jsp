<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	var ctx = "${pageContext.request.contextPath}"
</script>
<div class="page-header">
	<div class="container">
		<h1>
			FDB <span>film data base</span>
		</h1>
		<c:if test="${ not empty pageContext.request.userPrincipal.name }">
			<small class="pull-right">You are logged in as <b>${pageContext.request.userPrincipal.name}</b></small>
		</c:if>
	</div>
</div>