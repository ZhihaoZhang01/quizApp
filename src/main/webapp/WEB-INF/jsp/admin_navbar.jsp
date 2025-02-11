<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/2/2025
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
<div id="admin-navbar" style="background:#ddd; padding:10px;">
    <ul style="list-style: none; display: flex; gap: 15px;">
        <li><a href="<c:url value='/admin/home'/>">Admin Home</a></li>
        <li><a href="<c:url value='/admin/users'/>">User Management</a></li>
        <li><a href="<c:url value='/admin/quizResults'/>">Quiz Result Management</a></li>
        <li><a href="<c:url value='/admin/questions'/>">Question Management</a></li>
        <li><a href="<c:url value='/admin/contacts'/>">Contact Management</a></li>
        <c:choose>
            <c:when test="${empty user}">
                <li><a href="<c:url value='/login'/>">Login</a></li>
                <li><a href="<c:url value='/register'/>">Register</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<c:url value='/logout'/>">Logout</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

