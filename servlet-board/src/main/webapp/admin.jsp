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
<%@ include file="locale.jsp"%>

<fmt:bundle basename="message">
<body>
<h1><fmt:message key = "userList"/></h1>
<p><a href="/userAdd.jsp" ><fmt:message key = "addUser"/></a></p>
<table border =1>
    <thead>
    <tr>
        <th><fmt:message key = "id"/></th>
        <th><fmt:message key = "profile"/></th>
        <th><fmt:message key = "update"/></th>
        <th><fmt:message key = "delete"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items = "${userList}">
        <tr>
            <td>${item.id}</td>
            <td>${item}</td>
<%--            <td>--%>
<%--                <c:url var="add_link" value="/userAdd.jsp" scope="request">--%>
<%--&lt;%&ndash;                    <c:param name="id" value="${item.id}" />&ndash;%&gt;--%>
<%--                </c:url>--%>
<%--                <a href="${add_link}">추가</a>--%>
<%--            </td>--%>
            <td>
                <form method="post" action="/userUpdate.do">
                    <input type="hidden" name="id" value="${item.id}" scope="request"/>
                    <button type="submit"><fmt:message key = "update"/></button>
                </form>
            </td>
            <td>
                <form method="post" action="/userDelete.do">
                    <input type="hidden" name="id" value="${item.id}" />
                    <button type="submit"><fmt:message key = "delete"/></button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <p></p>
    <p><a href="/logout.do" ><fmt:message key = "logout"/></a></p>
</table>
</fmt:bundle>
</body>
</html>