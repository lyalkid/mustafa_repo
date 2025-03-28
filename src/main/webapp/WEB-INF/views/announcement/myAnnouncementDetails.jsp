
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Announcement Details</title>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: #f0f4f8;
            margin: 0;
            padding: 20px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #3f51b5;
            transition: color 0.3s;
        }

        a:hover {
            color: #303f9f;
        }

        .announcement-card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
        }

        .announcement-title {
            font-size: 24px;
            font-weight: bold;
            color: #3f51b5;
        }

        .announcement-description {
            font-size: 16px;
            margin: 10px 0;
            color: #333;
        }

        .announcement-images img {
            max-width: 100%;
            height: auto;
            margin: 5px;
            border-radius: 5px;
        }

        .clearfix {
            clear: both;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/announcements">Back to Announcements</a>

<div class="announcement-card">
    <div class="announcement-header">
        <div class="item">
            <label for="title">Title:</label>
            <p id="title" class="announcement-title">${title}</p>
            <label for="description">Description:</label>
            <p id="description" class="announcement-description">${description}</p>
            <h3>Images:</h3>
            <c:if test="${not empty images}">
                <div class="announcement-images">
                    <c:forEach var="image" items="${images}">
                        <img src="${pageContext.request.contextPath}/images/${image.filePath}" alt="${title}"/>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty images}">
                <p>No images available for this announcement.</p>
            </c:if>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
</body>
</html>