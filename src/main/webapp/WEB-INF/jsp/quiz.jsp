<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/1/2025
  Time: 6:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%@ include file="navbar.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Quiz Page</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<h2>Quiz</h2>
<div>Time Remaining: <span id="timer">${remainingTime}</span> seconds</div>
<form id="quizForm" action="<c:url value='/quiz/submit'/>" method="post">
    <input type="hidden" name="quizId" value="${quizId}" />
    <c:forEach var="detail" items="${quizDetails}" varStatus="status">
        <div style="margin-bottom:20px;">
            <p><strong>Questions ${status.index + 1}：</strong> ${detail.question.description}</p>
            <c:forEach var="choice" items="${detail.choices}">
                <label>
                    <input type="radio" name="answer_${detail.quizQuestion.qqId}" value="${choice.choiceId}" required />
                        ${choice.description}
                </label><br/>
            </c:forEach>
        </div>
    </c:forEach>
    <button type="submit">Submit</button>
</form>
<script>
    var remainingTime = ${remainingTime};
    var timerElement = document.getElementById("timer");
    var quizForm = document.getElementById("quizForm");
    var countdown = setInterval(function() {
        remainingTime--;
        if (remainingTime <= 0) {
            clearInterval(countdown);
            alert("Time is up! The quiz will be submitted automatically.");
            quizForm.submit();
        }
        timerElement.innerText = remainingTime;
    }, 1000);
</script>
<%@ include file="footer.jsp" %>
</body>
</html>
