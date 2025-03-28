<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: #f0f4f8;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            background-color: #3f51b5;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
            max-width: 600px;
            text-align: center;
        }

        h1 {
            font-size: 48px;
            margin: 0;
            color: #fff;
        }

        p {
            font-size: 18px;
            color: white;
        }

        a {
            text-decoration: none;
            color: white;
            background-color: #ff5722;
            padding: 10px 20px;
            border-radius: 5px;
            margin-top: 20px;
            display: inline-block;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #e64a19;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>404 - Page Not Found</h1>
    <p>Oops! The page you're looking for doesn't exist.</p>
    <p>It may have been moved, deleted, or you may have entered an incorrect URL.</p>
    <a href="${pageContext.request.contextPath}/home">Go to Homepage</a>
</div>
</body>
</html>