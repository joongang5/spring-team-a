<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정하기</title>
<style type="text/css">
	#detailBoard { width: 600px; height: 300px; }
</style>
</head>
<body>
	<form action="noticeUpdate.do" method="post">
		<p>제목</p>
			<input class="noticeTitle" id="noticeTitle" name="title" value="${detail.title}" placeholder="제목을 입력하세요.">
		<p>내용</p>
		<textarea class="noticeContent" id="noticeContent" name="content" rows="10">${detail.content}</textarea><br>
		<input type="hidden" name="no" value="${detail.no}" >
        <button type="submit" class="writeBtn">글쓰기</button>
    </form>
</body>
</html>