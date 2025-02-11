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
    <title>Quiz Result Management</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">

</head>
<body>
<%@ include file="admin_navbar.jsp" %>
<h2>Quiz Result Management</h2>
<form action="<c:url value='/admin/quizResults'/>" method="get">
    Category: <input type="text" name="category" value="${param.category}"/>
    User Email: <input type="text" name="userEmail" value="${param.userEmail}"/>
    <button type="submit">Filter</button>
</form>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Taken Time</th>
        <th>Category</th>
        <th>User Full Name</th>
        <th>Quiz Name</th>
        <th>Score</th>
        <th>Result</th>
        <th>Detail</th>
    </tr>
    <c:forEach var="history" items="${quizHistories}">
        <tr>
            <td>${history.endTime}</td>
            <td>${history.categoryName}</td>
            <td>${history.userName}</td>
            <td>${history.quizName}</td>
            <td>${history.score}</td>
            <td>${history.result}</td>
            <td>
                <a href="<c:url value='/quiz/result?quizId=${history.quizId}'/>">Detail</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div>
    <c:if test="${currentPage > 1}">
        <a href="<c:url value='/admin/quizResults?page=${currentPage - 1}'/>">Previous</a>
    </c:if>
    Page ${currentPage}
    <a href="<c:url value='/admin/quizResults?page=${currentPage + 1}'/>">Next</a>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>


