<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/2/2025
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Message Detail</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<%@ include file="admin_navbar.jsp" %>
<h2>Contact Message Detail</h2>
<p><strong>Subject:</strong> ${contact.subject}</p>
<p><strong>Email:</strong> ${contact.email}</p>
<p><strong>Time:</strong> ${contact.time}</p>
<p><strong>Message:</strong></p>
<p>${contact.message}</p>
<br/>
<a href="<c:url value='/admin/contacts'/>">Back to Contact List</a>
<%@ include file="footer.jsp" %>
</body>
</html>

