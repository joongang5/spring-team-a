<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소통마당 수정하기</title>
<style type="text/css">
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

#detailBoard {
	width: 600px;
	height: 300px;
}
</style>
<script type="text/javascript">
	function check() {
		var title = document.getElementById("boardTitle");
		var content = document.getElementById("boardContent");
		
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
			<form action="boardUpdate.do" method="post" onsubmit="return check();">
				<input class="boardTitle" id="boardTitle" name="title"
					value="${detail.title}"><br>
				<textarea class="boardContent" id="boardContent" name="content"
					rows="10">${detail.content}</textarea>
				<br> <input type="hidden" name="no" value="${detail.no}">
				<button type="submit" class="writeBtn">수정하기</button>
			</form>
		</main>
	</div>
</body>
</html>