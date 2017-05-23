<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p>Create user</p>
<form class='adduserform' name='adduserform' action="<c:url value='/accountcreated' />" method='POST'>

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
				<td>Confirm password:</td>
				<td><input type='password' name='confirmpass' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		</table>

		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>