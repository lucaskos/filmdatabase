<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	var ctx = "${pageContext.request.contextPath}"
</script>
<div class="header">
		<h3>FDB</h3>
		<h5>film data base</h5>
		<c:if test="${ not empty pageContext.request.userPrincipal.name }">
		
		<p>You are logged in as "${pageContext.request.userPrincipal.name }"</p>
		
		</c:if>
</div>