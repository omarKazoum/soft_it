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
    <title>Add user</title>
    <style>
        td{
            border: solid black 1px;
            padding: 15px;
        }

    </style>
</head>
<body>
<form action="users?action=add" method="POST">
    <label for="full_name">Full name:</label>
    <input type="text" name="full_name" id="full_name">
    <label for="email">user email:</label>
    <input type="email" name="email" id="email">
    <label for="password">password:</label>
    <input type="password" name="password" id="password">

    <input type="submit" value="save">
</form>
</table>

</body>
</html>
