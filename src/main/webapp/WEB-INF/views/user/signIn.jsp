<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
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
    .container {
      background: #ffffff;
      padding: 40px;
      border-radius: 10px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
      width: 350px;
      text-align: center;
      transition: transform 0.3s;
    }
    .container:hover {
      transform: translateY(-5px);
    }
    h2 {
      margin-bottom: 20px;
      color: #3f51b5;
      font-size: 26px;
      font-weight: bold;
    }
    input[type="text"],
    input[type="password"] {
      width: 100%;
      padding: 12px;
      margin: 10px 0;
      border: 2px solid #3f51b5;
      border-radius: 5px;
      transition: border-color 0.3s;
      font-size: 16px;
    }
    input[type="text"]:focus,
    input[type="password"]:focus {
      border-color: #1e88e5;
      outline: none;
    }
    input[type="submit"] {
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
    input[type="submit"]:hover {
      background-color: #303f9f;
      transform: translateY(-2px);
    }
    p {
      margin-top: 15px;
      color: #555;
      font-size: 14px;
    }
    .message {
      color: red;
      text-align: center;
      margin-top: 10px;
      font-weight: bold;
    }
    a {
      color: #3f51b5;
      text-decoration: none;
    }
    a:hover {
      text-decoration: underline;
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
  <div class="message">${message}</div>
</div>
</body>
</html>