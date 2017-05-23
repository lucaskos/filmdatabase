<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<p>Create user</p>
<sf:form class='adduserform' name='adduserform' modelAttribute='user'
	action="${pageContext.request.contextPath }/createaccount"
	method='POST'>

	<table>
		<tr>
			<td>Username:</td>
			<td><sf:input type='text' name='username' path='username' />
				<div class='error'>
					<sf:errors path='username' />
				</div></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><sf:input type='password' name='password' path='password' />
				<div class='error'>
					<sf:errors path='password' />
				</div></td>
		</tr>
		<tr>
			<td>Confirm password:</td>
			<td><input type='password' name='confirmpass' /></td>
		</tr>

		<tr>
			<td>Email:</td>
			<td><sf:input name='email' path='email' />
				<div class='error'>
					<sf:errors path='email' />
				</div></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</sf:form>