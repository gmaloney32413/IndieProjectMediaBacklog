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

        <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
            Media Backlog
        </a>

        <div class="collapse navbar-collapse">

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <c:if test="${not empty sessionScope.user}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/backlog">
                            My Backlog
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">
                            Dashboard
                        </a>
                    </li>
                </c:if>

            </ul>

            <!-- RIGHT SIDE NAV -->
            <ul class="navbar-nav ms-auto">

                <!-- NOT LOGGED IN -->
                <c:if test="${empty sessionScope.user}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logIn">
                            Sign In
                        </a>
                    </li>
                </c:if>

                <!-- LOGGED IN DROPDOWN -->
                <c:if test="${not empty sessionScope.user}">
                    <li class="nav-item dropdown">

                        <a class="nav-link dropdown-toggle d-flex align-items-center"
                           href="#"
                           id="userDropdown"
                           role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false">

                            <!-- simple user icon -->
                            <span class="me-1">👤</span>

                            <!-- optional: show username/email -->
                                ${sessionScope.user.username}
                        </a>

                        <ul class="dropdown-menu dropdown-menu-end"
                            aria-labelledby="userDropdown">

                            <li>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/account">
                                    Account Info
                                </a>
                            </li>

                            <li>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/settings">
                                    Settings
                                </a>
                            </li>

                            <li><hr class="dropdown-divider"></li>

                            <li>
                                <a class="dropdown-item text-danger"
                                   href="${pageContext.request.contextPath}/logout">
                                    Sign Out
                                </a>
                            </li>

                        </ul>
                    </li>
                </c:if>

            </ul>

        </div>
    </div>
</nav>