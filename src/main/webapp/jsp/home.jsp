<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29/12/2024
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        body {
            background-color: #ffeb3b;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            text-align: center;
        }
        h1 {
            color: #ff9800;
        }
        h3 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a {
            color: #ff9800;
            text-decoration: none;
            font-weight: bold;
            font-size: 18px;
        }
        a:hover {
            text-decoration: underline;
            color: #d32f2f;
        }
    </style>
</head>
<body>
<h1>Welcome to our resource!</h1>
<h3>Here are our main pages:</h3>
<h2>Hello, ${username}!</h2>
<ul>
    <li><a href="/announcementList">Announcements</a></li>
</ul>
</body>
</html>