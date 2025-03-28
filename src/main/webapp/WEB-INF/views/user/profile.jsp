
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f0f4f8;
      margin: 0;
      padding: 20px;
    }

    nav {
      margin-bottom: 20px;
    }

    .navbar {
      list-style-type: none;
      padding: 0;
      display: flex;
      justify-content: space-around;
      background-color: #3f51b5;
      padding: 10px;
      border-radius: 5px;
    }

    .navbar li {
      display: inline;
    }

    .navbar a {
      color: white;
      text-decoration: none;
      padding: 10px 15px;
      transition: background-color 0.3s;
    }

    .navbar a:hover {
      background-color: #303f9f;
    }

    h1 {
      text-align: center;
      margin-bottom: 20px;
      color: #3f51b5;
    }


    .clearfix {
      clear: both;
    }

    .profile-card {
      background: white;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
      margin: 20px auto;
      width: 300px;
    }
    .profile-header {
      text-align: center;
    }
    h2 {
      text-align: center;
      margin-top: 20px;
      color: #3f51b5;
    }
    .clearfix {
      clear: both;
    }
    p {
      text-align: center;
      color: #333;
    }

  </style>
  <title>Profile</title>
</head>
<body>
<nav>
  <ul class="navbar">
    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>

    <c:if test="${not empty sessionScope.user}">
      <form action="${pageContext.request.contextPath}/signOut" method="post">
        <button type="submit" style="background: none; border: none; color: white; cursor: pointer;">Sign Out</button>
      </form>
    </c:if>
  </ul>
</nav>

<h2>Details</h2>
<div class="profile-card">
  <div class="profile-header">
    <div class="item">
      <h3>Username: ${username}</h3>
      <h3>Email: ${email}</h3>
      <h3>
        First name: ${firstName} <br>
        Second name: ${secondName} <br>
        Birth date: ${birthDate} <br>
      </h3>
    </div>
    <div class="clearfix"></div>
  </div>
</div>
<p>Want to edit info about yourself? <a href="/profile/edit">Edit</a></p>

</body>
</html>