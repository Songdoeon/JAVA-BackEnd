<%--
  Created by IntelliJ IDEA.
  User: songdo-eon
  Date: 2023/04/08
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>게시물-조회</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <td>번호</td>
        <td>${post.id}</td>
    </tr>
    <tr>
        <td>제목</td>
        <td>${post.title}</td>
    </tr>
    <tr>
        <td>내용</td>
        <td>${post.content}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>${post.writerUserId}</td>
    </tr>
    <tr>
        <td>작성 시간</td>
        <td><fmt:formatDate value="${post.writeTime}" pattern="yyyy-MM-dd HH:mm:ss" />
    </tr>

    </tbody>
</table>
    <form method="post" action="/postUpdate.do">
        <input type="hidden" name="id" value="${post.id}" scope="request"/>
        <button type="submit">수정</button>
    </form>
    <form method="post" action="/postDelete.do">
        <input type="hidden" name="id" value="${post.id}" />
        <button type="submit">삭제</button>
    </form>
<ul>
    <li><a href="/board.do">게시판</a></li>
</ul>
<p>유저수 : ${sessionScope.userCount}</p>
</body>
</html>