<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/2/2025
  Time: 6:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Management</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<%@ include file="admin_navbar.jsp" %>
<h2>Contact Us Management</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Subject</th>
        <th>Email</th>
        <th>Time</th>
        <th>Message Preview</th>
        <th>Action</th>
    </tr>
    <c:forEach var="contact" items="${contacts}">
        <tr>
            <td>${contact.subject}</td>
            <td>${contact.email}</td>
            <td>${contact.time}</td>
            <td>
                <c:choose>
                    <c:when test="${fn:length(contact.message) > 20}">
                        ${fn:substring(contact.message, 0, 20)}...
                    </c:when>
                    <c:otherwise>
                        ${contact.message}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="<c:url value='/admin/contacts/view?contactId=${contact.contactId}'/>">View</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
</body>
</html>

