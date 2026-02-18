<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gavin
  Date: 2/16/2026
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <form>
        <h2>Login</h2>
        <label for="uname">Username:</label><br>
        <input type="text" id="uname" name="uname"><br>
        <label for="pass">password:</label><br>
        <input type="text" id="pass" name="pass"><br>
        <input type="submit" value="submit"><br>
        <a href="createUser.jsp">Dont have an account? Create one here.</a>
    </form>
</head>
<body>

</body>
</html>