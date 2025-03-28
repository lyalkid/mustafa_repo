<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Review</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: #f0f4f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        form {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
            transition: transform 0.3s;
        }
        form:hover {
            transform: translateY(-5px);
        }
        h1 {
            margin-bottom: 20px;
            color: #3f51b5;
            font-size: 24px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            color: #333;
        }
        input[type="number"],
        textarea {
            width: 100%;
            padding: 12px;
            margin-bottom: 10px;
            border: 2px solid #3f51b5;
            border-radius: 5px;
            transition: border-color 0.3s;
        }
        input[type="number"]:focus,
        textarea:focus {
            border-color: #1e88e5;
            outline: none;
        }
        button {
            background-color: #3f51b5;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.3s;
        }
        button:hover {
            background-color: #303f9f;
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
<h1>Create Review</h1>
<form action="${pageContext.request.contextPath}/reviews/create" method="post">
    <input type="hidden" name="announcementId" value="${announcementId}">
    <label for="rate">Rating (1-5):</label>
    <input type="number" id="rate" name="rate" min="1" max="5" required>
    <label for="comment">Comment:</label>
    <textarea id="comment" name="comment" required></textarea>
    <button type="submit">Submit Review</button>
</form>
</body>
</html>
