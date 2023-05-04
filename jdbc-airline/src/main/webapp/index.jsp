<%@ page import="java.sql.*" %>
<%@ page import="com.example.jdbcairline.DbUtils" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<table border="1">
    <tr>
        <td>번호</td>
        <td>이름</td>
        <td>등급</td>
        <td>나이</td>
    </tr>
<%
    Connection myConnection = DbUtils.getDataSource().getConnection();
    PreparedStatement statement = null;
    ResultSet result = null;
    try{
        String Query = "SELECT PassengerNo,PassengerName, Grade, Age From Passenger";
        statement =  myConnection.prepareStatement(Query);
        result =statement.executeQuery();
        while(result.next()){
%>
<tr>
    <td><a href="/list.jsp?id=<%= result.getInt(1)%>"/><%= result.getInt(1)%></td>
    <td><%= result.getString(2)%></td>
    <td><%= result.getInt(3)%></td>
    <td><%= result.getInt(4)%></td>
</tr>
<%
        }
        result.close();
        statement.close();
        myConnection.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
%>
</table>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>