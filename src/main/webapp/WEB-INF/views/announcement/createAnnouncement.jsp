
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Create Announcement</title>

  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f0f4f8;
      margin: 0;
      padding: 20px;
    }
    form {
      background: white;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
      max-width: 600px;
      margin: auto;
    }
    label {
      display: block;
      margin-bottom: 10px;
      color: #333;
    }
    input[type="text"],
    input[type="number"],
    textarea,
    input[type="file"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    button {
      background-color: #3f51b5;
      color: white;
      border: none;
      padding: 10px;
      border-radius: 5px;
      cursor: pointer;
      width: 100%;
      transition: background-color 0.3s;
    }
    button:hover {
      background-color: #303f9f;
    }
    .message {
      color: #c62828;
      margin-top: 10px;
      text-align: center;
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
  </style>
</head>
<body>

<form action="<c:url value='/announcements/my/new'/>" method="post" enctype="multipart/form-data">
  <label for="title">Title:
    <input type="text" id="title" name="title" required>
  </label>

  <label for="description">Description:
    <textarea id="description" name="description" required></textarea>
  </label>

  <label for="priceId">Price:
    <input type="number" name="price" id="priceId" required>
  </label>

  <c:forEach var="currency" items="${requestScope.currencyList}">
    <label>
      <input type="radio" name="currency" value="${currency}" required> ${currency}
    </label>
  </c:forEach>

  <label for="images">Image:
    <input name="images" type="file" id="images" multiple required>
  </label>

  <button type="submit">Create</button>

  <div class="message">${message}</div>
</form>

<a href="${pageContext.request.contextPath}/announcements/my">Back to My Announcements</a>

</body>
</html>