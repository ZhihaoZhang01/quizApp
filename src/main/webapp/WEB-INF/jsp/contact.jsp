<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/1/2025
  Time: 11:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="navbar.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<h2>Contact Us</h2>
<c:if test="${not empty message}">
    <div style="color: green;">${message}</div>
</c:if>
<form action="<c:url value='/contact'/>" method="post">
    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject" value="${contact.subject}" required /><br/><br/>
    <label for="email">Email address:</label>
    <input type="email" id="email" name="email" value="${contact.email}" required /><br/><br/>
    <label for="message">Message content:</label>
    <textarea id="message" name="message" required>${contact.message}</textarea><br/><br/>
    <button type="submit">Send</button>
</form>
<%@ include file="footer.jsp" %>
</body>
</html>
