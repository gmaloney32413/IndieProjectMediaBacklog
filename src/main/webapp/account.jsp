<%--
  Created by IntelliJ IDEA.
  User: Gavin
  Date: 5/10/2026
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>

<c:import url="/includes/navbar.jsp"/>

<div class="container mt-4">

    <h2>Account Information</h2>

    <div class="card p-3">

        <p><strong>Name:</strong> ${sessionScope.user.name}</p>
        <p><strong>Username:</strong> ${sessionScope.user.username}</p>
        <p><strong>Email:</strong> ${sessionScope.user.email}</p>
        <p><strong>Cognito ID:</strong> ${sessionScope.user.cognitoSub}</p>

    </div>

</div>

</body>
</html>