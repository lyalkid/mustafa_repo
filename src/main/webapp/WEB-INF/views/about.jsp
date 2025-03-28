
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="tr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>About</title>

  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f0f4f8;
      margin: 0;
      padding: 20px;
    }
    .container {
      max-width: 800px;
      margin: auto;
      background: white;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    }
    h1 {
      text-align: center;
      margin-bottom: 20px;
      color: #3f51b5;
    }
    p {
      line-height: 1.6;
      color: #333;
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
  </style>
</head>
<body>
<nav>
  <ul class="navbar">
    <li><a href="https://t.me/CaganSelcuk"  target="_blank">Contact</a></li>
    <li><a href="${pageContext.request.contextPath}/home">Home</a> </li>
    <c:if test="${not empty sessionScope.user}">
      <form action="${pageContext.request.contextPath}/signOut" method="post">
        <button type="submit" style="background: none; border: none; color: white; cursor: pointer;">Sign Out</button>
      </form>
    </c:if>
  </ul>
</nav>
<div class="container">
  <h1>About</h1>
  <p>Welcome to, where we turn your real estate dreams into reality. With a dedicated team of experienced professionals, we specialize in helping you buy, sell, and invest in properties that fit your lifestyle and goals. Your journey home starts here!</p>
  <p>Your trusted partner in the real estate market. We are committed to providing exceptional service and expert guidance to help you navigate the buying, selling, and investing process. Our team of dedicated professionals understands the local market and is passionate about finding the perfect property for you. Whether you're a first-time buyer or a seasoned investor, we are here to make your real estate experience seamless and rewarding. Let us help you find your dream home today!</p>
</div>



</body>
</html>
