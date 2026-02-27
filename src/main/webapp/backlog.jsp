<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:import url="/includes/navbar.jsp"/>
    </nav>
</header>
<main>
    <h1>My Media Backlog</h1>

    <p>BacklogEntries size: ${backlogEntries.size()}</p>

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
        <c:forEach var="entry" items="${backlogEntries}">
            <tr>
                <td>
                    <c:if test="${not empty entry.mediaItem.posterUrl}">
                        <img src="${entry.mediaItem.posterUrl}" alt="${entry.mediaItem.title}" >
                    </c:if>
                </td>
                <td>${entry.mediaItem.title}</td>
                <td>${entry.mediaItem.overview}</td>
                <td>${entry.status}</td>
                <td>${entry.notes}</td>
                <td>
                    <a href="editBacklog.jsp?id=${entry.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<c:import url="/includes/footer.jsp"/>
</body>
</html>
