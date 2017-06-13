<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form class='addfilm' modelAttribute='film'
	action="${pageContext.request.contextPath }/docreate" method='POST'>
	<h2>Add film</h2>
	<table>

		<tr>
			<td>Title:</td>
			<td><sf:input type='text' name='title' path='title' />
				<div class='error'>
					<sf:errors path='title' />
				</div></td>
		</tr>
		<tr>
			<td>Year:</td>
			<td><sf:input type='text' name='year' path='year' />
				<div class='error'>
					<sf:errors path='year' />
				</div></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><sf:textarea type='text' name='description'
					path='description' />
				<div class='error'>
					<sf:errors path='description' />
				</div></td>
		</tr>

		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>
</sf:form>