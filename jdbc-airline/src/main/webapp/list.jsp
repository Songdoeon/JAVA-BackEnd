<%@ page import="com.example.jdbcairline.DbUtils" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %><%--
  Created by IntelliJ IDEA.
  User: songdo-eon
  Date: 2023/05/03
  Time: 5:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td>승객번호</td>
        <td>비행번호</td>
        <td>날짜</td>
    </tr>
    <%
        String flightNo = request.getParameter("id");
        Connection myConnection = DbUtils.getDataSource().getConnection();
        DataSource dataSource = DbUtils.getDataSource();
        String Query = "SELECT * From Reservation WHERE PassengerNo= "+flightNo;
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query)){
            while(resultSet.next()){
    %>
    <tr>
        <td><%= resultSet.getInt(1)%></td>
        <td><a href="/view.jsp?id=<%= resultSet.getInt(2)%>"/><%= resultSet.getInt(2)%></td>
        <td><%= resultSet.getString(3)%></td>
    </tr>
    <%
            }
            resultSet.close();
            statement.close();
            myConnection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    %>
</table>
</body>
</html>
