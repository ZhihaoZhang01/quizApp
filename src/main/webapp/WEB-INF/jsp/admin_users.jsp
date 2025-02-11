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
    <title>User Management</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<%@ include file="admin_navbar.jsp" %>
<h2>User Management</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Full Name</th>
        <th>Email</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.firstname} ${user.lastname}</td>
            <td>${user.email}</td>
            <td>
                <c:choose>
                    <c:when test="${user.isActive == 1}">Active</c:when>
                    <c:otherwise>Suspended</c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="<c:url value='/admin/users/toggleStatus'/>" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="${user.userId}"/>
                    <c:choose>
                        <c:when test="${user.isActive == 1}">
                            <button type="submit">Suspend</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit">Activate</button>
                        </c:otherwise>
                    </c:choose>
                </form>
            </td>
        </tr>
    </c:forEach>
</table
<%@ include file="footer.jsp" %>
</body>
</html>

