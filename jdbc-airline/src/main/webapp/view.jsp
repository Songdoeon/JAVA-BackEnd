<%--
  Created by IntelliJ IDEA.
  User: songdo-eon
  Date: 2023/05/02
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.jdbcairline.DbUtils" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td>비행번호</td>
        <td>출발지</td>
        <td>도착지</td>
        <td>가격</td>
        <td>비행기 종류</td>
        <td>항공사</td>
    </tr>
<%
    String flightNo = request.getParameter("id");
    Connection myConnection = DbUtils.getDataSource().getConnection();
    DataSource dataSource = DbUtils.getDataSource();
    String Query = "SELECT F.FlightNo, Deparetures,Arrival,Price, KindOfAircraft, a.Airline ";
    Query += "From Flight AS F inner Join Aircraft AS a ON f.AircraftNo = a.AircraftNo ";
    Query += "WHERE FlightNo =" +flightNo;
    try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Query)){
        while(resultSet.next()){
%>
    <tr>
        <td><%= resultSet.getInt(1)%></td>
        <td><%= resultSet.getString(2)%></td>
        <td><%= resultSet.getString(3)%></td>
        <td><%= resultSet.getString(4)%></td>
        <td><%= resultSet.getString(5)%></td>
        <td><%= resultSet.getString(6)%></td>
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
