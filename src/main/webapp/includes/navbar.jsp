<%--
  Created by IntelliJ IDEA.
  User: Gavin
  Date: 2/16/2026
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Media Backlog</a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="backlog">My Backlog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="search">Search</a>
                </li>
            </ul>

            <span class="navbar-text me-3">
                Welcome, ${sessionScope.username}
            </span>

            <a href="logout" class="btn btn-outline-light btn-sm">Logout</a>
        </div>
    </div>
</nav>
