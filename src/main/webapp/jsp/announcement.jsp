<%--
  Created by IntelliJ IDEA.
  User: lyalkid
  Date: 31.12.2024
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>

    <li><a href="${pageContext.request.contextPath}/about">About</a></li>
    <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <li><a href="${pageContext.request.contextPath}/announcements/my">My announcements</a></li>
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

</head>
<body>
<h1>All Announcements</h1>

<c:if test="${not empty announcements}">
    <div class="announcements-grid">
        <c:forEach var="announcement" items="${announcements}">
            <div class="announcement-item">
                <h3 class="announcement-name"><a
                        href="${pageContext.request.contextPath}/announcements/${announcement.id}">${announcement.getTitle()}</a>
                </h3>
                    <%--                <a href="${pageContext.request.contextPath}/announcements/${announcement.id}">--%>
                    <%--                    <img src="${pageContext.request.contextPath}${announcement.imagePath}" alt="${announcement.title}"--%>
                    <%--                         class="announcement-image"/>--%>
                    <%--                </a>--%>
<%--                <h2>${announcement.title}</h2>--%>
<%--                <p>${announcement.description}</p>--%>
                <div class="clearfix"></div>
            </div>
        </c:forEach>
    </div>
</c:if>

<c:if test="${empty announcements}">
    <p>No announcements found.</p>
</c:if>
</body>
</html>
