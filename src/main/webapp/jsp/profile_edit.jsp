<%--
  Created by IntelliJ IDEA.
  User: lyalkid
  Date: 01.01.2025
  Time: 06:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit profile</title>
</head>
<body>
<form action="/profile/edit" method="post">
    <h2>Edit</h2>
    <p>Fill some fields</p><br>
    <input name="username" type="text" placeholder="Username"> <br>
    <input name="password" type="password" placeholder="Password" ><br>
    <input name="email" type="email" placeholder="Email" ><br>
    <input name="firstName" type="text" placeholder="First Name" ><br>
    <input name="secondName" type="text" placeholder="Second Name" ><br>
    <input name="birthDate" type="date" placeholder="Birth Date" ><br>
    <input type="submit" value="Edit">
</form>
<div>${usernameMessage}</div>
<div>${emailMessage}</div>

</body>
</html>
