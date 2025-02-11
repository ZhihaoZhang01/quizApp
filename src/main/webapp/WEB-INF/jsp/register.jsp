<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h2>Register</h2>

    <c:if test="${not empty error}">
        <div class="alert">${error}</div>
    </c:if>

    <form action="<c:url value='/register'/>" method="post">
        <label for="firstname">First name:</label>
        <input type="text" id="firstname" name="firstname" value="${user.firstname}" required />

        <label for="lastname">Last name:</label>
        <input type="text" id="lastname" name="lastname" value="${user.lastname}" required />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required />

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required />

        <button type="submit">Register</button>
    </form>
    <p style="text-align:center;">Already have an account? <a href="<c:url value='/login'/>">Login</a></p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
