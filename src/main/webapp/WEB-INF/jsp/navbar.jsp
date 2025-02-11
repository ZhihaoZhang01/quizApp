<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/1/2025
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="navbar" style="background:#eee; padding:10px;">
    <ul style="list-style: none; display: flex; gap: 15px;">
        <c:if test="${not empty sessionScope.user}">
            <li><a href="<c:url value='/home'/>">Home</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.user and not empty openQuiz}">
            <li><a href="<c:url value='/quiz?quizId=${openQuiz.quizId}'/>">Taking Quiz</a></li>
        </c:if>
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <li><a href="<c:url value='/login'/>">Login</a></li>
                <li><a href="<c:url value='/register'/>">Register</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<c:url value='/logout'/>">Logout</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="<c:url value='/contact'/>">Contact</a></li>
    </ul>
</div>
