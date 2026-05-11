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

    <hr>

    <h3>Backlog Stats</h3>

    <div class="row">

        <div class="col-md-4">
            <div class="card p-3">
                <p><strong>Total Items:</strong> ${totalBacklog}</p>
                <p><strong>Active:</strong> ${active}</p>
                <p><strong>Completed:</strong> ${completed}</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3">
                <p><strong>Planned:</strong> ${planned}</p>
                <p><strong>In Progress:</strong> ${inProgress}</p>
                <p><strong>Dropped:</strong> ${dropped}</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3">
                <p><strong>Movies:</strong> ${movieCount}</p>
                <p><strong>TV Shows:</strong> ${tvCount}</p>
                <p><strong>Completion Rate:</strong> ${completionRate}%</p>
            </div>
        </div>

    </div>

</div>

</body>
</html>