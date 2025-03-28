<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Announcements</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f4f8;
            margin: 0;
            padding: 20px;
        }

        nav {
            margin-bottom: 20px;
        }

        .navbar {
            list-style-type: none;
            padding: 0;
            display: flex;
            justify-content: space-around;
            background-color: #3f51b5;
            padding: 10px;
            border-radius: 5px;
        }

        .navbar li {
            display: inline;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            transition: background-color 0.3s;
        }

        .navbar a:hover {
            background-color: #303f9f;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #3f51b5;
        }

        .announcements-grid {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .announcement-item {
            background: white;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            margin: 10px;
            width: 300px;
            text-align: center;
        }

        .announcement-name {
            font-size: 18px;
            margin: 0;
            color: #333;
        }

        .clearfix {
            clear: both;
        }

        a.back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #3f51b5;
            transition: color 0.3s;
        }

        a.back-link:hover {
            color: #303f9f;
        }
    </style>
</head>
<body>
<nav>
    <ul class="navbar">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li><a href="${pageContext.request.contextPath}/announcements/my">My announcements</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
            </c:otherwise>
        </c:choose>

        <c:if test="${not empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/signOut" method="post">
                <button type="submit" style="background: none; border: none; color: white; cursor: pointer;">Sign Out</button>
            </form>
        </c:if>
    </ul>
</nav>

<h1>All Announcements</h1>

<c:if test="${not empty announcements}">
    <div class="announcements-grid">
        <c:forEach var="announcement" items="${announcements}">
            <div class="announcement-item">
                <h3 class="announcement-name">
                    <a href="${pageContext.request.contextPath}/announcements/${announcement.id}">${announcement.getTitle()}</a>
                </h3>
                <div class="clearfix"></div>
            </div>
        </c:forEach>
    </div>
</c:if>

<c:if test="${empty announcements}">
    <p>No announcements found.</p>
</c:if>
<a href="${pageContext.request.contextPath}/home" class="back-link">Back to Home</a>
</body>
</html>