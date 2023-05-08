<%--
  Created by IntelliJ IDEA.
  User: songdo-eon
  Date: 2023/04/05
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css" />
    <meta charset="UTF-8" />
</head>
<body>
<%@ include file="locale.jsp"%>

<fmt:bundle basename="message">
<h1><fmt:message key = "board"/></h1>
<p><a href="/postAdd.jsp" ><fmt:message key = "addBoard"/></a></p>
<table border =1>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items = "${postList}">
        <tr>
            <td>${post.id}</td>
            <td><c:url var="post_View" value="/postView.do" >
                <c:param name="id" value="${post.id}" />
            </c:url>
                <a href="${post_View}">${post.title}</a></td>
            <td><c:url var="user_View" value="/userView.do" >
                <c:param name="id" value="${post.writerUserId}" />
            </c:url>
                <a href="${user_View}">${post.writerUserId}</a></td>

        </tr>
    </c:forEach>
    </tbody>
    <p><a href="/logout.do" ><fmt:message key = "logout"/></a></p>
</table>
<p><fmt:message key = "user"/> : ${sessionScope.userCount}</p>
</fmt:bundle>
</body>
</html>