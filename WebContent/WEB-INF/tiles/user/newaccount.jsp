<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="col-lg-4 col-md-6 col-sm-10 col-xs-12">
	<sf:form class='register' name='adduserform' modelAttribute='user'
		action="${pageContext.request.contextPath }/createaccount"
		method='POST'>
		<h2>Create user</h2>

		<div class="form-group">
			<label for="username">Username</label>
			<sf:input class="form-control" path="username" type="text"
				id="username" name="username" />
			<spring:hasBindErrors name="user">
				<div class="alert alert-danger fade in">
					<sf:errors path="username">
					</sf:errors>
				</div>
			</spring:hasBindErrors>
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<sf:input class="form-control" path="password" type="password"
				id="password" name="password" />
			<spring:hasBindErrors name="user">
				<div class="alert alert-danger fade in">
					<sf:errors path="password">
					</sf:errors>
				</div>
			</spring:hasBindErrors>
		</div>
		<div class="form-group">
			<label for="repass">Confirm Password</label> <input
				class="form-control" type="password" name="repass" id="repass" />
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<sf:input class="form-control" path="email" type="text" id="email"
				name="email" />
			<spring:hasBindErrors name="user">
				<div class="alert alert-danger fade in">
					<sf:errors path="email">
					</sf:errors>
				</div>
			</spring:hasBindErrors>
		</div>
		<button type="submit" class="btn btn-default">Register</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</sf:form>
</div>