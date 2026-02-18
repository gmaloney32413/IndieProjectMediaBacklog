<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Backlog</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
    <nav>
        <%@ include file="includes/navbar.jsp" %>
    </nav>
</header>
<main>
    <h1>My Media Backlog</h1>

    <table border="1" id="backlog-table">
        <thead>
        <tr>
            <th>Poster</th>
            <th>Title</th>
            <th>Overview</th>
            <th>Status</th>
            <th>Notes</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Media items will populate here dynamically -->
        </tbody>
    </table>
</main>

<script src="backlog.js"></script>
</body>
</html>
