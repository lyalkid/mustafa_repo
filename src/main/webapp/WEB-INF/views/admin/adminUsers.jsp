<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - User Management</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f4f8;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #3f51b5;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: auto;
            background: white;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }
        th {
            background-color: #3f51b5;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        button {
            background-color: #d32f2f;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #c62828;
        }
    </style>
</head>
<body>
<h1>User Management</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td>
                <!-- Delete button -->
                <form action="${pageContext.request.contextPath}/admin/users" method="post" style="display: inline;">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" onclick="return confirm('Are you sure you want to delete this user?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/home" class="back-link">Back to Home</a>
</body>
</html>