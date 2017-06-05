<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<c:out value="${film.id }"></c:out>
<c:out value="${film.title }"></c:out>
<c:out value="${film.year }"></c:out>
<c:forEach var="actors" items="${film.actors }">
	<c:out value="${actors.name}"></c:out>
	
</c:forEach>

<sf:form class='addactor' name='addactor' modelAttribute='actor'
	action="${pageContext.request.contextPath }/addactor"
	method='POST'>
<h2>Create user</h2>
	<table>
	
		<tr>
			<td>Name:</td>
			<td><sf:input type='text' name='name' path='name' />
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