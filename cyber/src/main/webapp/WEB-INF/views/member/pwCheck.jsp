<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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

.loginWrap {
	margin: 0 auto;
	padding: 40px 100px 50px;
	max-width: 300px;
	border: 1px solid #ccc;
	border-radius: 8px;
	margin-top: 5px;
}

.loginWrap h4 {
	text-align: center;
}

.loginWrap .inputWrap {
	width: 100%;
	box-sizing: border-box;
	border: 1px solid #ccc;
	padding: 2px;
}

.loginWrap label {
	padding: 10px 0;
	width: 20%;
}

.loginWrap input {
	padding: 10px 0;
	width: 70%;
}

.loginWrap button {
	padding: 10px 0;
	width: 100%;
	line-height: 20px;
	font-size: 15px;
}

button {
	background-color: #ffe4f3;
	border-radius: 10px;
	border: none;
}
</style>
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
			<div class="loginWrap">
				<form action="memberModify.do" method="post">
					<h3 style="text-align: center;">비밀번호를 입력하세요.</h3>
					<div class="inputWrap">
						<label>비밀번호</label> <input type="password" name="pw"
							style="border-color: white; text-align: center;">
					</div>
					<p>
						<button type="submit">확인</button>
					</p>
				</form>
			</div>
		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div>
</body>
</html>