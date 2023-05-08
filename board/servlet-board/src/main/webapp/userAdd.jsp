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
    <c:when test="${empty user}">
        <c:set var="userId" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="userId" value="${user.id}"/>
    </c:otherwise>
</c:choose>
<form action="/userAdd.do" method="post">
    <h2>ID : <input type="text" name="id" value=${userId}> </h2>
    <h2>Password : <input type="text" name="password"> </h2>
    <h2>이름 : <input type="text" name="name"> </h2>
    <h2>profile : <input type="file" name="file"> </h2>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty user}">
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
