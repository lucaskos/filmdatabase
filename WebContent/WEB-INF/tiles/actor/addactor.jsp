<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<sf:form class="form-horizontal" modelAttribute='actor'
				action="${pageContext.request.contextPath }/actoradded"
				method='POST'>
				<h2>Add actor</h2>
				<div class="form-group row">
					<div class="col-xs-4">
					<label class="control-label" for="name">Name:</label>
					<sf:input class="form-control" id="name" type='text' path='name'/>
					<sf:errors class="alert alert-danger" path='name' />
					</div>
				</div>
					<input class="btn btn-lg btn-success" name="submit" type="submit"
						value="Add actor" />
			</sf:form>
		</div>
	</div>
</div>