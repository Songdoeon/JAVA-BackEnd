<%--
  Created by IntelliJ IDEA.
  User: songdo-eon
  Date: 2023/04/08
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8" />
</head>
<body>
<c:choose>
    <c:when test="${empty post}">
        <c:set var="action" value="/addPost.do"/>
        <c:set var="method" value="post"/>
    </c:when>
    <c:otherwise>
        <c:set var="postId" value="${post.id}"/>
        <c:set var="action" value="/postUpdate.do"/>
        <c:set var="method" value="get"/>
    </c:otherwise>
</c:choose>
<form action=${action} method=${method}>
    <input type="hidden" name="id" value=${postId}>
    <h2>제목 : <input type="text" name="title"> </h2>
    <h2>내용 : <input type="text" name="content"> </h2>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty post}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>
</body>
</html>
