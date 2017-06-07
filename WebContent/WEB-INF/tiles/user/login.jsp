<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="login-box">

	<h2>Login with Username and Password</h2>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>

	<form class="loginform" name='loginForm' action="<c:url value='/login' />" method='POST'>

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		</table>

		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<a href="<c:url value='/newaccount'/>">Create Account</a>
	</form>
</div>
<!-- 
<form:form name="loginForm" action="<c:url value='j_spring_security_check' />" method="post">
<table>
		<tr>
			<td>User:</td>
			<td><form:input type='text' name='username' path="username" /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><form:input type='password' name='password' path="password"/></td>
		</tr>
		<tr>
			<td colspan='2'><form:input  path="" name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>
</form:form>
 -->