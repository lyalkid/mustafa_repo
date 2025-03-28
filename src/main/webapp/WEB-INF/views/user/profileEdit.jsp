
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit profile</title>

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
    p {
      margin-bottom: 20px;
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
    .message {
      color: red;
      text-align: center;
      margin-top: 10px;
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
<form action="${pageContext.request.contextPath}/profile/edit" method="post">
  <h2>Edit</h2>
  <p>Fill some fields</p>

  <input name="username" type="text" placeholder="Username" > <br>
  <input name="password" type="password" placeholder="Password" ><br>
  <input name="email" type="email" placeholder="Email" ><br>
  <input name="firstName" type="text" placeholder="First Name" ><br>
  <input name="secondName" type="text" placeholder="Second Name" ><br>
  <input name="birthDate" type="date" placeholder="Birth Date" ><br>

  <input type="submit" value="Edit">

    <a href="${pageContext.request.contextPath}/profile"> Cancel </a></p>
</form>
<p>
<div class="message">${usernameMessage}</div><br>
<div class="message">${emailMessage}</div><br>

</body>
</html>