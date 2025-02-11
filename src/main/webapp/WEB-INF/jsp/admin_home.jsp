<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/2/2025
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<%@ include file="admin_navbar.jsp" %>
<h2>Admin Home Page</h2>
<ul>
    <li><a href="<c:url value='/admin/users'/>">User Management</a></li>
    <li><a href="<c:url value='/admin/quizResults'/>">Quiz Result Management</a></li>
    <li><a href="<c:url value='/admin/questions'/>">Question Management</a></li>
    <li><a href="<c:url value='/admin/contacts'/>">Contact Us Management</a></li>
</ul>
<%@ include file="footer.jsp" %>
</body>
</html>

