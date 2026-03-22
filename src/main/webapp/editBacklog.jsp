<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${not empty backlogEntry}">
                Edit Backlog Entry
            </c:when>
            <c:otherwise>
                Add Backlog Entry
            </c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<header>
    <c:import url="/includes/navbar.jsp"/>
</header>

<main>
    <h1>
        <c:choose>
            <c:when test="${not empty backlogEntry}">
                Edit Backlog Entry
            </c:when>
            <c:otherwise>
                Add Backlog Entry
            </c:otherwise>
        </c:choose>
    </h1>

    <form action="editBacklog" method="post">

        <input type="hidden" name="tmdbId" value="${backlogEntry.mediaItem.tmdbId}">
        <input type="hidden" name="mediaType" value="${backlogEntry.mediaItem.mediaType}">
        <input type="hidden" name="title" value="${backlogEntry.mediaItem.title}">
        <input type="hidden" name="overview" value="${backlogEntry.mediaItem.overview}">
        <input type="hidden" name="posterUrl" value="${backlogEntry.mediaItem.posterUrl}">

        <input type="hidden" name="mediaId" value="${backlogEntry.mediaItem.id}">

        <!-- Hidden ID only if editing -->
        <c:if test="${not empty backlogEntry}">
            <input type="hidden" name="entryId" value="${backlogEntry.id}"/>
        </c:if>

        <div>
            <h2>
                Media Title: ${backlogEntry.mediaItem.title}
            </h2>
        </div>

        <div>
            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <c:forEach var="s" items="${backlogStatuses}">
                    <option value="${s}"
                            <c:if test="${not empty backlogEntry.id && backlogEntry.status == s}">
                                selected
                            </c:if>>
                            ${s}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="userRating">Your Rating (1-10):</label>
            <input type="number"
                   id="userRating"
                   name="userRating"
                   min="1"
                   max="10"
                   value="${not empty backlogEntry.id ? backlogEntry.userRating : ''}"/>
        </div>

        <div>
            <label for="notes">Notes:</label><br>
            <textarea id="notes"
                      name="notes"
                      rows="5"
                      cols="40">${not empty backlogEntry.id ? backlogEntry.notes : ''}</textarea>
        </div>

        <div>
            <button type="submit">
                <c:choose>
                    <c:when test="${not empty backlogEntry}">
                        Update Entry
                    </c:when>
                    <c:otherwise>
                        Add Entry
                    </c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>

    <c:if test="${not empty backlogEntry.id}">
        <!-- Delete button -->
        <form action="deleteBacklog" method="post" style="display:inline;">
            <input type="hidden" name="id" value="${backlogEntry.id}" />
            <button type="submit" onclick="return confirm('Are you sure you want to delete this entry?');">
                Delete Entry
            </button>
        </form>
    </c:if>
</main>

<c:import url="/includes/footer.jsp"/>

</body>
</html>