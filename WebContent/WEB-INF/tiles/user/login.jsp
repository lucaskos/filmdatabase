<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="containers">
	<div class="row">
		<div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1">
			<c:if test="${not empty error}">
				<div class="alert alert-warning">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form class="loginform" name='loginForm'
				action="<c:url value='/login' />" method='POST'>

				<div class="form-group">
					<label for="login">Login:</label> <input type="text"
						name="username" class="form-control" id="login">
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label> <input type="password"
						name="password" class="form-control" id="pwd">
				</div>
				<div class="checkbox">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <label><input type="checkbox">
						Remember me</label> <span class="pull-right">Not registered yet? <a
						href="<c:url value='/newaccount'/>">Register</a></span>
				</div>
				<button type="submit" value="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</div>
</div>