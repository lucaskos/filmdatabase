<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-success">
				<p>
					Hi <b><c:out value="${user}" /></b>
				</p>
			</div>
		</div>
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-danger">
				<c:forEach var="error" items="${error}">
					<c:out value="${error}" />
					<br />
				</c:forEach>
			</div>
		</div>
	</div>
</div>