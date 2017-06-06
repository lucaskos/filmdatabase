<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form class='addactor' modelAttribute='actor'
	action="${pageContext.request.contextPath }/actoradded"
	method='POST'>
<h2>Add actor</h2>
	<table>
	
		<tr>
			<td>Name:</td>
			<td><sf:input type='text' path='name' />
				<div class='error'>
					<sf:errors path='name' />
				</div></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>
</sf:form>