<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<sf:form class="form-horizontal" role="form" modelAttribute="film"
				action="${pageContext.request.contextPath }/docreate" method='POST'>
				<h2>Add film</h2>

				<div class="form-group">
					<label class="col-sm-3 control-label">Title:</label>
					<div class="col-sm-9">
						<sf:input class="form-control" type='text' name='title'
							path='title' />
						<sf:errors class="alert alert-danger" path='title' />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">Year:</label>
					<div class="col-sm-9">
						<sf:input class="form-control" type="text" name='year' path='year' />
						<sf:errors class="alert alert-danger" path='year' />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">Description:</label>
					<div class="col-sm-9">
						<sf:textarea class="form-control" type='text' name='description'
							path='description' />
						<sf:errors class="alert alert-danger" path='description' />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<c:if test="${ empty film.title}">
							<input class="btn btn-lg btn-success" type="submit"
								value="Add new film" />
						</c:if>
						<c:if test="${ not empty film.title}">
							<c:input class="btn btn-success" type="submit" value="#{Constants.updateButtonText}" />
						</c:if>
					</div>
				</div>
			</sf:form>
		</div>
	</div>
</div>