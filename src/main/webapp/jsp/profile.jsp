<%--
  Created by IntelliJ IDEA.
  User: lyalkid
  Date: 01.01.2025
  Time: 05:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><nav>
    <ul class="navbar">
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
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
        <c:if test="${not empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/signOut" method="post">
                <button type="submit">signOut</button>
            </form>
        </c:if>
    </ul>
</nav>
    <title>Profile</title>
</head>
<body>
<h2>Details</h2>
<div class="profile-card">
    <div class="profile-header">
        <div class="item">

            <h3>Username: ${username} </h3>
            <h3>Email: ${email} </h3>
            <h3>
                First name: ${firstName} <br>
                Second name: ${secondName} <br>
                Birth date: ${birthDate} <br>
            </h3>
        </div>

        <div class="clearfix"></div>
    </div>
</div>
<p>Want to edit info about yourself? <a href="/profile/edit">Edit</a></p>


</body>
</html>
