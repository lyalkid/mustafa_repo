
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Announcement Details</title>

    <style>
        body {
            font-family: Arial, sans-serif;
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

        .buttons-container {
            margin-top: 20px;
            text-align: center;
        }

        button {
            background-color: #3f51b5;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            margin: 5px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #303f9f;
        }

        .review-card {
            background: #e0e0e0;
            padding: 10px;
            border-radius: 5px;
            margin: 10px 0;
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


            <!-- Кнопки "Add to Favorites" и "Make Review" -->
            <div class="buttons-container">
                <c:choose>
                    <c:when test="${!isFavorite}">
                        <form action="${pageContext.request.contextPath}/favorites/add" method="post">
                            <input type="hidden" name="announcementId" value="${id}">
                            <button type="submit">Add to Favorites</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/favorites/delete" method="post">
                            <input type="hidden" name="announcementId" value="${id}">
                            <button type="submit">Delete from Favorites</button>
                        </form>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${! isReviewed}">
                        <!-- Make Review -->
                        <form action="${pageContext.request.contextPath}/reviews/create" method="get">
                            <input type="hidden" name="announcementId" value="${id}">
                            <button type="submit">Make Review</button>
                        </form>
                    </c:when>
                </c:choose>

            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>


<h3>Reviews:</h3>
<c:if test="${not empty reviews}">
    <div class="reviews-list">
        <c:forEach var="review" items="${reviews}">
            <div class="review-card">
                <p><strong>Username:</strong> ${review.author}</p>
                <p><strong>Rating:</strong> ${review.rate}/5</p>
                <p><strong>Comment:</strong> ${review.comment}</p>
            </div>
        </c:forEach>
    </div>
</c:if>
<c:if test="${empty reviews}">
    <p>No reviews available for this announcement.</p>
</c:if>

</body>
</html>