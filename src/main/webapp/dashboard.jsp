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
    <%@ include file="includes/navbar.jsp" %>
</header>
<main>
    <h1>Dashboard</h1>

    <!-- Search TMDB -->
    <section id="search-section">
        <h2>Search TMDB</h2>
        <form id="tmdb-search-form">
            <input type="text" id="search-query" placeholder="Enter movie or TV show" required>
            <select id="search-type">
                <option value="movie">Movie</option>
                <option value="tv">TV Show</option>
            </select>
            <button type="submit">Search</button>
        </form>
        <div id="search-results">
            <!-- TMDB search results will populate here -->
        </div>
    </section>

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
                <td id="planned-count">0</td>
            </tr>
            <tr>
                <td>In Progress</td>
                <td id="in-progress-count">0</td>
            </tr>
            <tr>
                <td>Completed</td>
                <td id="completed-count">0</td>
            </tr>
            <tr>
                <td>Dropped</td>
                <td id="dropped-count">0</td>
            </tr>
            </tbody>
        </table>
    </section>
</main>

<script src="dashboard.jsp"></script>
</body>
</html>
