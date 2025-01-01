<%--
  Created by IntelliJ IDEA.
  User: lyalkid
  Date: 01.01.2025
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create announcement</title>
</head>
<body>
<form action="<c:url value="/announcements/my/new"/>" method="post">
  <label for="title">Title:</label><br>
  <input type="text" id="title" name="title"><br><br>


  <label for="description">Описание фильма:</label><br>
  <textarea id="description" name="description"></textarea><br><br>

  <input type="submit" value="Добавить"><br><br>
</form>
</body>
</html>
