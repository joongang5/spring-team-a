<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
<style type="text/css">
	#detailBoard { width: 600px; height: 300px; }
</style>
</head>
<body>
	<form action="noticeWrite.do" method="post">
		<p>제목</p>
			<textarea class="noticeTitle" id="noticeTitle" name="title" placeholder="제목을 입력하세요."></textarea>
		<p>내용</p>
		<textarea class="noticeContent" id="noticeContent" name="content" rows="10"></textarea><br>
        <button type="submit" class="writeBtn">글쓰기</button>
    </form>
</body>
</html>