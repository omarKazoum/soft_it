<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="jdk.dynalink.linker.LinkerServices" %>
<%@ page import="com.omar.softit.entities.User" %>
<%@ page import="java.util.List" %><%--
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
<a href="users?action=add">add <new></new></a>
<a href="logout">logout <new></new></a>

    <table style="border-collapse:collapse">
        <tr>
            <td>#</td>
            <td>email</td>
            <td> full name</td>
        </tr>
        <%
            List<User> users= (List<User>) request.getAttribute("users");
            for(User user:users){
        %>
        <tr>
            <td><%= user.getId()%></td>
            <td><%= user.getEmail()%></td>
            <td><%= user.getFullName()%></td>
            <td><a href="?action=delete&user_id=<%= user.getId()%>">delete</a>
            <td><a href="?action=edit&user_id=<%= user.getId()%>">edit</a>
            </td>
        </tr>
        <% }%>
</table>

</body>
</html>
