<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
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
	background: #111;
}

aside {
	float: left;
	width: 240px;
	height: 600px;
	background: #333;
}

main {
	float: left;
	width: 760px;
	height: 600px;
	background: #444;
}

footer {
	clear: both;
	width: 1000px;
	height: 100px;
	background: #555;
}

#loginbox {
	background-color: #c4c8d2;
	height: auto;
	width: 400px;
	margin: 0 auto;
}

#inputbox {
	text-align: center;
}

input {
	height: 30px;
	width: 200px;
	border-radius: 10px;
	font-family: 'NanumSquare', serif;
}

#buttonbox {
	margin-top: 40px;
	text-align: center;
}

button {
	width: 130px;
	height: 35px;
	text-align: center;
	font-family: 'NanumSquare', serif;
	/*font-weight: bold;*/
	background-color: white;
	border-radius: 10px;
	margin-bottom: 10px;
}

#checkbox {
	width: 15px;
	height: 15px;
	margin-left: 100px;
	margin-top: 10px;
	font-family: 'NanumSquare', serif;
	vertical-align: -3px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
			<div id="loginbox">
				<img alt="book" src="../resources/img/book.png"
					style="margin-left: 170px; margin-top: 10px; margin-bottom: 20px;">
				<div id="inputbox">
					<form action="/cyber/member/memberLogin.do" method="post">
						<c:if test="${not empty rememberCookie.getValue()}">
							<c:set value="checked" var="checked" />
						</c:if>
						<div>
							<input type="text" name="id" placeholder="아이디"
								style="margin-bottom: 10px;" value="${rememberId.getValue() }">
							<br> <input type="password" name="pw" placeholder="비밀번호">
						</div>
				</div>
				<div style="font-family: 'NanumSquare', serif; font-size: 12px;">
					<input type="checkbox" id="checkbox" value="true" ${checked}>
					아이디 기억하기
				</div>
				<div id="buttonbox">
					<button type="submit">로그인</button>
					<br>
					</form>
					<a href="/cyber/member/memberFind.do"><button type="submit">ID/PW 찾기</button></a>
					<br> <a href="/cyber/member/memberJoinRegist.do"><button type="submit">회원
							가입</button></a>
				</div>
			</div>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>