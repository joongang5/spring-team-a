<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정하기</title>
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
	margin-left: 10px;
	margin-top: 10px;
}
</style>
<script type="text/javascript">
	function check() {
		var title = document.getElementById("noticeTitle");
		var content = document.getElementById("noticeContent");

		if (title.value.length < 5 || title.valu
	e == "") {
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
			<form action="noticeUpdate.do" method="post"
				onsubmit="return check();">
				<input class="noticeTitle" id="noticeTitle" name="title"
					style="font-family: 'Nanum Gothic', sans-serif; margin: 10px;"
					value="${detail.title}"><br>
				<textarea class="noticeContent" id="noticeContent" name="content"
					rows="10"
					style="font-family: 'Nanum Gothic', sans-serif; margin-left: 10px;">${detail.content}</textarea>
				<br> <input type="hidden" name="no" value="${detail.no}">
				<input type="file" name="file" accept=".gif, .png, .jpg"
					style="margin-left: 10px;"><br>
				<button type="submit" class="writeBtn">수정하기</button>
			</form>
		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div>
</body>
</html>