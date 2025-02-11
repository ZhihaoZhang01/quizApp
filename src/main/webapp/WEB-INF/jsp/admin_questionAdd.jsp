<%--
  Created by IntelliJ IDEA.
  User: Zhihao Zhang
  Date: 2/2/2025
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Question</title>
    <link rel="stylesheet" href="<c:url value='../../resources/css/style.css'/>">
</head>
<body>
<%@ include file="admin_navbar.jsp" %>
<h2>Add New Question</h2>
<form:form method="post" action="/admin/questions/add" modelAttribute="questionForm">
    <label for="categoryId">Category:</label>
    <form:select path="categoryId" id="categoryId">
        <form:options items="${categories}" itemValue="categoryId" itemLabel="name"/>
    </form:select>
    <br/><br/>

    <label for="description">Question Description:</label><br/>
    <form:textarea path="description" id="description" rows="4" cols="50"/>
    <br/><br/>

    <h3>Choices</h3>
    <label for="choice1">Choice 1:</label>
    <form:input path="choice1" id="choice1" />
    <br/><br/>

    <label for="choice2">Choice 2:</label>
    <form:input path="choice2" id="choice2" />
    <br/><br/>

    <label for="choice3">Choice 3:</label>
    <form:input path="choice3" id="choice3" />
    <br/><br/>

    <label for="choice4">Choice 4:</label>
    <form:input path="choice4" id="choice4" />
    <br/><br/>

    <label>Correct Choice:</label>
    <form:radiobutton path="correctChoice" value="1"/> Choice 1
    <form:radiobutton path="correctChoice" value="2"/> Choice 2
    <form:radiobutton path="correctChoice" value="3"/> Choice 3
    <form:radiobutton path="correctChoice" value="4"/> Choice 4
    <br/><br/>

    <button type="submit">Add Question</button>
</form:form>
<%@ include file="footer.jsp" %>
</body>
</html>