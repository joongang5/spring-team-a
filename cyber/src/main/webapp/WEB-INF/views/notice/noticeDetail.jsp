<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<script type="text/javascript">
function move(){
	location.href='./listNotice.do';
}
</script>
</head>
<body>
	<div id="detailBoard">
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>내용</th>
			</tr>
			<c:forEach items="${detail }" var="d">
			<tr>
				<td>${d.notice_no }</td>
				<td>${d.title }</td>
				<td>${d.date }</td>
				<td>${d.count }</td>
				<td>${d.id }(${d.name })</td>
			</tr>
			</c:forEach>
		</table>
	</div> <!-- end of detailBoard -->
	<button onclick="move()">돌아가기</button>
</body>
</html>