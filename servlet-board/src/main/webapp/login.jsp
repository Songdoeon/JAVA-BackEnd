<%--
  Created by IntelliJ IDEA.
  User: songdo-eon
  Date: 2023/04/05
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%@ include file="locale.jsp"%>

<fmt:bundle basename="message">
<form method="post", action="/login.do">
    <p><fmt:message key = "id"/> : <input type="text" name ="userId" required/> </p>
    <p><fmt:message key = "password"/> : <input type="password" name ="userPassword" required/> </p>
    <p><Button type="submit"><fmt:message key = "login"/></Button></p>
</form>
<form method="post" action="/locale.do">
    <input type="submit" name="locale" value="ko">
    <input type="submit" name="locale" value="en">
</form>
</fmt:bundle>
</body>
</html>