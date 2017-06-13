<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="login-box">

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>

	<form class="loginform" name='loginForm'
		action="<c:url value='/login' />" method='POST'>

		<input type='text' placeholder='username' name='username' value=''>
		<input type='password' placeholder='password' name='password' /> <input
			name="submit" type="submit" value="submit" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			<p>Not registered? <a
			href="<c:url value='/newaccount'/>">Create Account</a></p><br/>
			<p>Remember me: <input type="checkbox" name="remember-me" /></p>
	</form>
</div>
