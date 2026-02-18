<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Media Backlog</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<c:import url="/includes/header.jsp"/>
<main>
    <section id="about">
        <h2>Welcome to My Media Backlog</h2>
        <p>
            My Media Backlog is a personal media management web application that helps you track all the movies and TV shows you want to watch, are currently watching, or have completed.
            Search the <strong>TMDB</strong> database to find your favorite media, add it to your backlog, and keep track of your progress.
            You can also add personal notes, update the status of each title, and organize your media collection efficiently.
            Stay on top of your entertainment, all in one place!
        </p>
    </section>
    <a href="login.jsp">login</a>
</main>
<c:import url="/includes/footer.jsp"/>
</body>
</html>