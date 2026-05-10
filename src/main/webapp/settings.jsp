<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Settings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<c:import url="/includes/navbar.jsp"/>

<div class="container mt-4">

    <h2>Settings</h2>

    <form action="settings" method="post">

        <div class="mb-3">
            <label>Name</label>
            <input type="text"
                   name="name"
                   class="form-control"
                   value="${sessionScope.user.name}">
        </div>

        <div class="mb-3">
            <label>Username</label>
            <input type="text"
                   name="username"
                   class="form-control"
                   value="${sessionScope.user.username}">
        </div>

        <button type="submit" class="btn btn-primary">
            Save Changes
        </button>

    </form>

</div>

</body>
</html>