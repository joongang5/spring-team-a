<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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

#joinbox {
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
	margin-top: 35px;
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

#check {
	width: 80px;
	height: 25px;
	font-family: 'NanumSquare', serif;
	font-size: 14px;
}
</style>
<script type="text/javascript">
	function idCheck() {		
		$.ajax({
			url : "idCheck.do",
			type : "post",
			dataType : 'text',
			data : {
				'id' : $("#id").val()
			},
			success : function(data) {
				if (data == "true") {
					$("#idCheckResult").text("사용 중인 ID입니다."");
					$("#idCheckResult").css("color", "#da2244");
					alert ("사용 중인 ID입니다."");
				} else if (data == "false") {
					$("#idCheckResult").css("color", "#0aca4a");
					$("#idCheckResult").text('사용 가능한 ID입니다.');
				}
			}
		})
	}
	function pwCheck() {
		var pw1 = document.getElementById("pw");
		var pw2 = document.getElementById("pw2");
		var checkMsg = document.getElementById("checkMsg");

		if (pw1 == pw2) {
			$("#checkMsg").text('사용 가능한 비밀번호입니다.');
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
			<div id="joinbox">
				<img alt="registration" src="resources/img/registration.png"
					style="margin-left: 170px; margin-top: 10px; margin-bottom: 20px;">
				<div id="inputbox">
					<form action="memberJoinRegist.do" method="post">
						<div>
							<input type="text" id="name" name="name" required="required"
								placeholder="이름" style="margin-bottom: 10px;"> <br>
							<input type="text" id="id" name="id" required="required"
								placeholder="아이디" style="margin-bottom: 10px;"
								onchange="idCheck()"> <br> <input type="password"
								id="pw" name="pw" required="required" placeholder="비밀번호"
								style="margin-bottom: 10px;"><br> <input
								type="password" id="pw2" name="pw2" required="required"
								placeholder="비밀번호 확인" style="margin-bottom: 10px;"><br>
							<div id="pwCheck" class="checkText"></div>
							<input type="text" id="email" name="email" required="required"
								placeholder="Email">
						</div>
						<div id="buttonbox">
							<button type="submit">가입하기</button>
						</div>
					</form>
				</div>
			</div>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>