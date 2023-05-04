<%@ page import="java.sql.*" %>

<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.nhnacademy.paging.DbUtils" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "안녕 친구들!" %>
</h1>
<table border="1">
    <tr>
        <td>번호</td>
        <td>이름</td>
        <td>도시</td>
    </tr>
    <%
        int itemPerPage=20;
        int pages = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int size;
        Connection myConnection = DbUtils.getDataSource().getConnection();
        String numQuery = "SELECT count(*) FROM MEMBERS";
        PreparedStatement statement =  myConnection.prepareStatement(numQuery);
        ResultSet result =statement.executeQuery();
        result.next();
        size = result.getInt(1);
        size = size/itemPerPage;
        int startPage = (pages/10)*10+1;
        int endPage = startPage+9;
        if(endPage>size){
            endPage=size;
        }
        if(pages>size){
            pages=size;
        }

        String firstLink = "<a href=?page=1>First"+"</a>";
        String endLink = "<a href=?page="+size+">END"+"</a>";
        String prevLink = "<a href=?page="+(pages-1)+">Prev"+"</a>";
        String nextLink = "<a href=?page="+(pages+1)+">Next"+"</a>";
        String Query = "SELECT * From MEMBERS WHERE Id>=? and Id<=?";
        statement =  myConnection.prepareStatement(Query);
        statement.setInt(1,(pages*itemPerPage)-(itemPerPage-1));
        statement.setInt(2,pages*itemPerPage);
        result =statement.executeQuery();
        while(result.next()){
    %>
    <tr>
        <td><%= result.getInt(1)%></td>
        <td><%= result.getString(2)%></td>
        <td><%= result.getString(3)%></td>
    </tr>
    <%
            }
            result.close();
            statement.close();
            myConnection.close();
    %>
</table>
    <%=firstLink%> | <%=prevLink%>
    <% for(int i=startPage; i<=endPage;i++){%>
    <a href="?page=<%=i%>"><%=i%></a>
    <%}%>
    <%=nextLink%> | <%=endLink%>
</body>
</html>