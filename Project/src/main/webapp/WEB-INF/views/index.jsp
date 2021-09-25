<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>글쓴이</th>
		</tr>
		<c:forEach items="${list	}" var="l">
			<tr>
				<td>${l.no	}</td>
				<td>${l.title	}</td>
				<td>${l.content	}</td>
				<td>${l.date	}</td>
				<td>${l.count	}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>