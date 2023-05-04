<%--
  Created by IntelliJ IDEA.
  User: songdo-eon
  Date: 2023/04/08
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>유저-조회</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <td>이름</td>
        <td>${user.name}</td>
    </tr>
    <tr>
        <td>프로필</td>
        <td> <img src="<c:out value='${pageContext.request.contextPath}/images.do?profile=${user.getProfile()}'/>"/></td>
    </tr>
    </tbody>
</table>
<ul>
    <li><a href="/board.do">게시판</a></li>
</ul>
<p>유저수 : ${sessionScope.userCount}</p>
</body>
</html>