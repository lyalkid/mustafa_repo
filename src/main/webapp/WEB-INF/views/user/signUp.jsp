<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>

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

        h2 {
            margin-bottom: 20px;
            color: #3f51b5;
            font-size: 24px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            color: #333;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"],
        input[type="date"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 10px;
            border: 2px solid #3f51b5;
            border-radius: 5px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="password"]:focus,
        input[type="email"]:focus,
        input[type="date"]:focus {
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
        .message {
            color: red;
            text-align: center;
            margin-top: 10px;
            font-weight: bold;
        }
        button:hover {
            background-color: #303f9f;
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/signUp" method="post">
        <h2>Register</h2>

        <label for="username">Username:</label>
        <input name="username" type="text" placeholder="Username" id="username" required>

        <label for="pass">Password:</label>
        <input name="password" type="password" placeholder="Password" id="pass" required>

        <label for="email">Email:</label>
        <input name="email" type="email" placeholder="Email" id="email" required>

        <label for="firstName">First name:</label>
        <input name="firstName" type="text" placeholder="First Name" id="firstName" required>

        <label for="secondName">Second name:</label>
        <input name="secondName" type="text" placeholder="Second Name" id="secondName" required>

        <label for="birth">Birth date:</label>
        <input name="birthDate" type="date" placeholder="Birth Date" id="birth" required>

        <button type="submit">Send</button>
    </form>
    <div class="message">${userMessage}</div>
    <div class="message">${emailMessage}</div>
</div>
</body>
</html>