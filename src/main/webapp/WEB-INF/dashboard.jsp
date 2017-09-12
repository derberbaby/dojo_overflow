<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question Dashboard</title>
</head>
<body>
<h1>Questions Dashboard</h1>

<table>
	<tr>
		<th>Question</th>
		<th>Tags</th>
	</tr>
	<c:forEach var="question" items ="${questionList}">
		<tr>
			<td>${question.question}</td>
			<td><c:forEach var="tag" items="${question.tags}" varStatus="loop">
					<button>${tag.subject}</button>
					<%-- <c:if test="${!loop.last}">, </c:if> --%>
				</c:forEach></td>
		</tr>
	</c:forEach>
</table>

<a href="/questions/new">New Question</a>

</body>
</html>
