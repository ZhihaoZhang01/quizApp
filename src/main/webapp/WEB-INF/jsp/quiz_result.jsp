<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/1/2025
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="navbar.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Quiz Result</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<h2>Quiz Result</h2>

<div>
    <p><strong>User Name:</strong> ${quizHistory.userName}</p>
    <p><strong>Quiz Name:</strong> ${quizHistory.quizName}</p>
    <p><strong>Category:</strong> ${quizHistory.categoryName}</p>
    <p><strong>Start Time:</strong> ${quizHistory.startTime}</p>
    <p><strong>End Time:</strong> ${quizHistory.endTime}</p>
    <p><strong>Score:</strong> ${quizHistory.score}</p>
    <p><strong>Result:</strong> ${quizHistory.result}</p>
</div>

<hr/>
<c:forEach var="detail" items="${quizDetails}">
    <div class="question">
        <p><strong>Question:</strong> ${detail.question.description}</p>
        <ul style="list-style: none; padding-left: 0;">
            <c:forEach var="choice" items="${detail.choices}">
                <c:set var="cssClass" value="default" />
                <c:if test="${choice.choiceId == detail.quizQuestion.userChoice.choiceId}">
                    <c:choose>
                        <c:when test="${choice.isCorrect == 1}">
                            <c:set var="cssClass" value="correct"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="cssClass" value="incorrect"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:if test="${choice.isCorrect == 1 and choice.choiceId != detail.quizQuestion.userChoice.choiceId}">
                    <c:set var="cssClass" value="missed"/>
                </c:if>
                <li class="option ${cssClass}">
                        ${choice.description}
                    <c:if test="${choice.choiceId == detail.quizQuestion.userChoice.choiceId}">
                        <strong>(Your Choice)</strong>
                    </c:if>
                    <c:if test="${choice.isCorrect == 1}">
                        <strong>(Correct Answer)</strong>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:forEach>

<div>
    <a href="<c:url value='/home'/>">
        <button type="button">Take Another Quiz</button>
    </a>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>

