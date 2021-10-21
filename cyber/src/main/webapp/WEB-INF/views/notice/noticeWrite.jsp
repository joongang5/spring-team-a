<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
<style type="text/css">
#detailBoard {
	width: 600px;
	height: 300px;
}

@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap')
	;

html {
	font-family: 'Nanum Gothic', sans-serif;
	color: #black;
}

body {
	margin: 0;
	padding: 0;
}

#wrap {
	width: 1000px;
	margin: 0 auto;
}

header {
	width: 1000px;
	height: 130px;
	background: #white;
}

aside {
	float: left;
	width: 240px;
	height: 600px;
	background: #e3f0ff;
}

main {
	float: left;
	width: 760px;
	height: 600px;
	background: #white;
}

footer {
	clear: both;
	width: 1000px;
	height: 100px;
	background: #cee5fe;
}

button {
	width: 100px;
	height: 35px;
	text-align: center;
	font-family: 'Nanum Gothic', sans-serif;
	background-color: white;
	border-color: #e3f0ff;
	border-radius: 10px;
	margin-bottom: 10px;
}

/* 본문 */

table {
	margin: 0 auto;
	margin-top: 10px;
}

#noticeTitle {
	width: 600px;
	height: 30px;
}

#noticeContent {
	width: 600px;
	height: 500px;
}

th {
	background-color: #e3f0ff;
	padding: 10px;
}

#fileSelect {
	margin-left: 50px;
}

h2 {
	color: #4c85d6;
}

#naviandtitle {
	margin: 10px;
}
</style>
<script type="text/javascript">
function check() {
	var title = document.getElementById("noticeTitle");
	var content = document.getElementById("noticeContent");
	
	if (title.value.length < 5 || title.value == "") {
		alert("제목을 5글자 이상 적어주세요.");
		title.focus();
		return false;
	}
	if (content.value == "" || content.value.length < 5) {
		alert("내용을 5글자 이상 적어주세요.");
		content.focus();
		return false;
	}
}
</script>
</head>
<body>
	<div id="wrap">
		<header>
			<c:import url="/WEB-INF/views/component/headerInner.jsp" />
		</header>
		<aside>
			<c:import url="/WEB-INF/views/component/lnbNav.jsp" />
		</aside>
		<main>
		
			<div id="naviandtitle">
				<div id="navi">
					<a href="../index.do">Home</a>><strong>공지사항</strong>
				</div>
				<!-- end of navi -->
				<h2>글쓰기</h2>
			</div>
			<!-- end of naviandtitle -->
			
			<form action="noticeWrite.do" method="post" enctype="multipart/form-data" onsubmit="return check();">
				<table>
					 <tr>
				 		<th>제목</th>
				 		<td>
						<textarea class="noticeTitle" id="noticeTitle" name="title"
						placeholder="제목을 입력하세요."></textarea>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
						<textarea class="noticeContent" id="noticeContent" name="content"
						rows="10" placeholder="내용을 입력하세요."></textarea>
						</td>
					</tr>
				</table>
				<input type="file" name="file" id="fileSelect" accept=".gif, .png, .jpg">
				<button type="submit" class="writeBtn">글쓰기</button>
			</form>
		</main>
	</div>
</body>
</html>