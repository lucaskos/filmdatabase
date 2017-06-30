<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<div class="col-lg-6 col-md-6 col-sm-10 col-xs-10">

		<sf:form class='register' name='adduserform' modelAttribute='user'
			action="${pageContext.request.contextPath }/createaccount"
			method='POST'>
			<h2>Create user</h2>

			<spring:hasBindErrors name="user">
				<div class="errors alert alert-danger">
					<c:forEach var="error" items="${errors.allErrors}">
						<b><spring:message message="${error}" /></b>
						<br />
					</c:forEach>
				</div>
			</spring:hasBindErrors>
			<div class="form-group">
				<label for="username">Username</label>
				<sf:input class="form-control" path="username" type="text"
					id="username" name="username" />
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<sf:input class="form-control" path="password" type="password"
					id="password" name="password" />
			</div>
			<div class="form-group">
				<label for="repass">Confirm Password</label> <input
					class="form-control" type="password" name="repass" id="repass" />
			</div>
			<div class="form-group">
				<label for="email">Email</label>
				<sf:input class="form-control" path="email" type="text" id="email"
					name="email" />
			</div>
			<button type="submit" class="btn btn-default">Register</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</sf:form>
	</div>
</div>