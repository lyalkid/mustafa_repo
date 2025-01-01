<%--
  Created by IntelliJ IDEA.
  User: lyalkid
  Date: 01.01.2025
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Announcement details</title>
</head>
<body>
<h2>Details</h2>
<div class="announcement-card">
    <div class="announcement-header">
        <div class="item">

            <h3>Title: ${title} </h3>
            <p>
                Author: ${author}<br>
            </p>
        </div>

        <span class="description">${desc}</span>

        <div class="clearfix"></div>
    </div>
</div>
</body>
</html>
