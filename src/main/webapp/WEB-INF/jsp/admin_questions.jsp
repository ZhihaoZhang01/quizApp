<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/2/2025
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question Management</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<%@ include file="admin_navbar.jsp" %>
<h2>Question Management</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Category</th>
        <th>Description</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="question" items="${questions}">
        <tr>
            <td>${question.category.categoryId}</td>
            <td>${question.description}</td>
            <td>
                <c:choose>
                    <c:when test="${question.isActive == 1}">Active</c:when>
                    <c:otherwise>Suspended</c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="<c:url value='/admin/questions/edit?questionId=${question.questionId}'/>">Edit</a>
                <form action="<c:url value='/admin/questions/toggleStatus'/>" method="post" style="display:inline;">
                    <input type="hidden" name="questionId" value="${question.questionId}"/>
                    <c:choose>
                        <c:when test="${question.isActive == 1}">
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
</table>
<div style="margin-top:15px;">
    <c:if test="${currentPage > 1}">
        <a href="<c:url value='/admin/questions?page=${currentPage - 1}'/>">Previous</a>
    </c:if>
    Page ${currentPage}
    <a href="<c:url value='/admin/questions?page=${currentPage + 1}'/>">Next</a>
</div>

<br/>
<a href="<c:url value='/admin/questions/add'/>">Add New Question</a>
<%@ include file="footer.jsp" %>
</body>
</html>
