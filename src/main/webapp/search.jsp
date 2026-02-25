<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Media Backlog Search</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<c:import url="includes/header.jsp"/>
<h1>Search</h1>

<!-- Search form -->
<form method="get" action="search">
  <input type="text" name="search" placeholder="Search by title..." value="${param.search}" />
  <button type="submit">Search</button>
</form>

<hr/>

<c:choose>
  <c:when test="${empty mediaItems}">
    <p>No media items found.</p>
  </c:when>
  <c:otherwise>
    <table border="1" cellpadding="5" cellspacing="0">
      <thead>
      <tr>
        <th>Poster</th>
        <th>Title</th>
        <th>Type</th>
        <th>Release Date</th>
        <th>Overview</th>
        <th>Details</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${mediaItems}">
        <tr>
          <td>
            <c:if test="${not empty item.posterUrl}">
              <img src="${item.posterUrl}" alt="${item.title}" width="100"/>
            </c:if>
          </td>
          <td>${item.title}</td>
          <td>${item.mediaType}</td>
          <td>
            <c:choose>
              <c:when test="${not empty item.releaseDate}">${item.releaseDate}</c:when>
              <c:otherwise>Unknown</c:otherwise>
            </c:choose>
          </td>
          <td>${item.overview}</td>
          <td>

          </td>
          <td>
            <!-- Add to Backlog button -->
            <form method="post" action="addToBacklog">
              <input type="hidden" name="mediaItemId" value="${item.id}" />
              <button type="submit">Add to Backlog</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:otherwise>
</c:choose>
<c:import url="includes/footer.jsp"/>
</body>
</html>