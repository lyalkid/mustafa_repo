
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>

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
            margin-top: 20px;
            color: #3f51b5;
        }
        h2 {
            text-align: center;
            margin-top: 10px;
            color: #333;
        }
        h3 {
            text-align: center;
            margin-top: 5px;
        }
        form {
            display: inline;
        }
    </style>
</head>
<body>
<nav>
    <ul class="navbar">
        <c:if test="${sessionScope.role == 'admin'}">
            <li><a href="/admin/users">Users</a></li>
        </c:if>
        <li><a href="${pageContext.request.contextPath}/about">About</a></li>
        <li><a href="https://t.me/CaganSelcuk" target="_blank">Contact</a></li>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="/announcements">Announcements</a></li>
        <c:if test="${not empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/signOut" method="post">
                <button type="submit" style="background: none; border: none; color: white; cursor: pointer;">Sign Out</button>
            </form>
        </c:if>
    </ul>
</nav>

<h1>Welcome to our resource!</h1>
<h2>Hello, ${username}!</h2>

</body>
</html>