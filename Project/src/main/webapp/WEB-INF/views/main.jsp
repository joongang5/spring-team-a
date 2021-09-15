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
				<td>${l.sb_nno	}</td>
				<td>${l.sb_title	}</td>
				<td>${l.sb_date	}</td>
				<td>${l.sb_count	}</td>
				<td>${l.sm_name	}(${l.sm_id	})</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>