<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29/12/2024
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <nav>
        <ul class="navbar">
            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
            <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
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
                    <button type="submit">signOut</button>
                </form>
            </c:if>



        </ul>
    </nav>
    <meta charset="UTF-8">
    <title>Home Page</title>

</head>
<body>
<h1>Welcome to our resource!</h1>
<h3>Here are our main pages:</h3>
<h2>Hello, ${username}!</h2>

</body>
</html>