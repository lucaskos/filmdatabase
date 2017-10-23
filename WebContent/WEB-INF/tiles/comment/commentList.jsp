<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="static com.luke.films.common.Constants.COMMENTS_LIST" %>
<div>
    <c:forEach items="${COMMENTS_LIST}" var="comment">
        <p>${comment.user.username}</p>
        <p>${comment.text}</p>
        <p>${comment.createdDate}</p>
    </c:forEach>
</div>
