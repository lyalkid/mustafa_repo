<%--
  Created by IntelliJ IDEA.
  User: lyalkid
  Date: 01.01.2025
  Time: 05:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            background-color: #ffeb3b; /* Sarı arka plan */
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        form {
            background-color: #ff9800; /* Turuncu arka plan */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
            width: 300px;
            text-align: center;
        }
        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #ffc107; /* Daha açık turuncu */
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #ffca28; /* Hover rengi */
        }
    </style>
</head>
<body>

<div class="container">
<form id="loginForm" class="form-horizontal" action="<c:url value="/signIn"/>" method="POST">
    <h2>Sign In</h2>
    <input name="username" type="text" placeholder="Username" required>
    <input name="password" type="password" placeholder="Password" required>
    <input type="submit" value="Sign In">
    <p>Don't you have an account? <a href="signUp">Sign Up</a></p>
</form>
    <div>${message}</div>
</div>
</body>
</html>
