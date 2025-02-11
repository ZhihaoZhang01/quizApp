<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 1/31/2025
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h2>Login</h2>

    <c:if test="${not empty error}">
        <div class="alert">${error}</div>
    </c:if>

    <form action="<c:url value='/login'/>" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required />

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required />

        <button type="submit">Login</button>
    </form>
    <p style="text-align:center;">Do not have an account? <a href="<c:url value='/register'/>">Register</a></p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
