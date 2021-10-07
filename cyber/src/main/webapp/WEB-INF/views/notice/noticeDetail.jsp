<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<style type="text/css">
	#detailBoard { width: 600px; height: 300px; }
</style>
<script type="text/javascript">
function move(){
	location.href='./listNotice.do';
}
</script>
</head>
<body>
	<div id="detailBoard">
		<b>번호 |</b> ${detail.no }
		<b>제목 |</b> ${detail.title }
		<b>작성자 |</b> ${detail.id }(${detail.name })
		<b>등록일 |</b> ${detail.date }
		<button onclick="location.href='./delete.do?no=${detail.no }'">삭제하기</button>
		<button onclick="location.href='./update.do?no=${detail.no }'">수정하기</button>
		<hr>
		${detail.content }
	</div> <!-- end of detailBoard -->
	<button onclick="move()">돌아가기</button>
</body>
</html>