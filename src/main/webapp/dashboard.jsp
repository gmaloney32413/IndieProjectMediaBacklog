<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Media Backlog Dashboard</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp"/>
</header>
<main>
    <h1>Dashboard</h1>

    <!-- Backlog Status Table -->
    <section id="backlog-status">
        <h2>Backlog Status</h2>
        <table border="1">
            <thead>
            <tr>
                <th>Status</th>
                <th>Count</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Planned</td>
                <td id="planned-count">${plannedCount}</td>
            </tr>
            <tr>
                <td>In Progress</td>
                <td id="in-progress-count">${inProgressCount}</td>
            </tr>
            <tr>
                <td>Completed</td>
                <td id="completed-count">${completedCount}</td>
            </tr>
            <tr>
                <td>Dropped</td>
                <td id="dropped-count">${droppedCount}</td>
            </tr>
            </tbody>
        </table>
    </section>

    <!-- Search TMDB -->
    <section id="search-section">
        <h2>Search TMDB</h2>
        <form id="tmdb-search-form">
            <input type="text" id="search-query" placeholder="Enter movie or TV show" required>
            <select id="search-type">
                <option value="movie">Movie</option>
                <option value="tv">TV Show</option>
                <option value="any">Any</option>
            </select>
            <button type="submit">Search</button>
        </form>
        <div id="search-results">
            <table border="1" id="backlog-table">
                <thead>
                <tr>
                    <th>Poster</th>
                    <th>Title</th>
                    <th>Overview</th>
                    <th>Media type</th>
                    <th>Release Date</th>
                    <th>Add</th>
                </tr>
                </thead>
                <tbody>
                <!-- Media items will populate here dynamically -->
                <c:forEach var="item" items="${mediaItems}">
                    <tr>
                        <td>
                            <c:if test="${not empty entry.mediaItem.posterUrl}">
                                <img src="${entry.mediaItem.posterUrl}" alt="${entry.mediaItem.title}" >
                            </c:if>
                        </td>
                        <td>${item.title}</td>
                        <td>${item.overview}</td>
                        <td>${item.mediaType}</td>
                        <td>${item.releaseDate}</td>
                        <td>
                            <a href="editBacklog?id=${item.id}">Add</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>
<c:import url="/includes/footer.jsp"/>
</body>
</html>
