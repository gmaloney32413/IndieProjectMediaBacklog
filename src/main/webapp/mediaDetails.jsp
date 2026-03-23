<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${mediaItem.title} - Details</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>${mediaItem.title}</h1>
<c:if test="${not empty mediaItem.posterUrl}">
    <img src="${mediaItem.posterUrl}" alt="${mediaItem.title}" />
</c:if>

<p><strong>Overview:</strong> ${mediaItem.overview}</p>
<p><strong>Media Type:</strong> ${mediaItem.mediaType}</p>
<c:choose>
    <c:when test="${mediaItem.mediaType eq 'movie'}">
        <p><strong>Director:</strong> ${mediaItem.director}</p>
        <p><strong>Runtime:</strong> ${mediaItem.runtime} minutes</p>
        <p><strong>Rating:</strong> ${mediaItem.rating}</p>
    </c:when>
    <c:when test="${mediaItem.mediaType eq 'tv_show'}">
        <p><strong>Seasons:</strong> ${mediaItem.numberOfSeasons}</p>
        <p><strong>Total Episodes:</strong> ${mediaItem.totalEpisodes}</p>
        <p><strong>Ongoing:</strong> ${mediaItem.ongoing}</p>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${param.returnPage == 'backlog'}">
        <a href="backlog">Back to Backlog</a>
    </c:when>
    <c:otherwise>
        <a href="dashboard">Back to Dashboard</a>
    </c:otherwise>
</c:choose>
</body>
</html>