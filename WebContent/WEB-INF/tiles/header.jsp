<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	var ctx = "${pageContext.request.contextPath}"
</script>
<div class="page-header">
	<h1>
		FDB <span>film data base</span>
	</h1>
	<c:if test="${ not empty pageContext.request.userPrincipal.name }">

		<p>You are logged in as "${pageContext.request.userPrincipal.name }"</p>

	</c:if>
</div>