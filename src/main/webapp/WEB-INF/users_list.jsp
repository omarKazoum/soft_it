<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 21/10/2022
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Available users</title>
    <style>
        td{
            border: solid black 1px;
            padding: 15px;
        }

    </style>
</head>
<body>
<table style="border-collapse:collapse">
    <%
        ResultSet result= (ResultSet) request.getAttribute("users");
        if(result==null)
            out.print("it's null");
        else
        while(result.next()){
    %>
    <tr>
        <td><%= result.getInt(1)%></td>
        <td><%= result.getString(2)%></td>
    </tr>
    <% }%>
</table>

</body>
</html>
