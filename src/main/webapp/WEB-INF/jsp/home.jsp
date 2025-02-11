<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="navbar.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<h2>Welcome, ${user.firstname}!</h2>
<c:if test="${not empty openQuiz}">
    <div style="padding:10px; border:1px solid #f00; background-color:#ffe6e6;">
        <p>You have an unfinished quiz!
            <a href="<c:url value='/quiz?quizId=${openQuiz.quizId}'/>">Continue Quiz</a>
        </p>
    </div>
</c:if>
<h3>Category</h3>
<ul>
    <c:forEach var="category" items="${categories}">
        <li>
                ${category.name}
            <a href="<c:url value='/quiz/start?categoryId=${category.categoryId}'/>">Start New Quiz</a>
        </li>
    </c:forEach>
</ul>

<h3>Recent Quizzes</h3>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>User Name</th>
        <th>Quiz Name</th>
        <th>Category Name</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Score</th>
        <th>Result</th>
        <th>Detail</th>
    </tr>
    <c:forEach var="history" items="${quizHistory}">
        <tr>
            <td>${history.userName}</td>
            <td>${history.quizName}</td>
            <td>${history.categoryName}</td>
            <td>${history.startTime}</td>
            <td>${history.endTime}</td>
            <td>${history.score}</td>
            <td>${history.result}</td>
            <td>
                <a href="<c:url value='/quiz/result?quizId=${history.quizId}'/>">Detail</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
</body>
</html>