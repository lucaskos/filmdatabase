<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="comment section">
    <h5>Comments section</h5>
    <c:forEach items="${comments}" var="comment">
	<a href="<c:out value='${comment.commentId}' />">
	    <span>${comment.user.username}<span>
        <span>${comment.text}<span>
        <span>${comment.createdDate}<span>
		</a>
    </c:forEach>
</div>
