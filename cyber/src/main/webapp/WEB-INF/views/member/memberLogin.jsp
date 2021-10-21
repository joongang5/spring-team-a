<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap')
	;

html {
	font-family: 'Nanum Gothic', sans-serif;
	color: #003857;
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

#loginbox {
	background-color: #e3f0ff;
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
}

#buttonbox {
	margin-top: 30px;
	text-align: center;
}

button {
	width: 130px;
	height: 35px;
	text-align: center;
	/*font-weight: bold;*/
	background-color: white;
	border-radius: 10px;
	margin-bottom: 10px;
}

#idSaveCheck {
	width: 15px;
	height: 15px;
	margin-left: 100px;
	margin-top: 10px;
	vertical-align: -3px;
}
</style>
<link rel="stylesheet" type="text/css" href="./resources/css/base.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var userInputId = getCookie("userInputId");
		$("input[name='id']").val(userInputId);

		if ($("input[name='id']").val() != "") {
			$("#idSaveCheck").attr("checked", true);
		}

		$("#idSaveCheck").change(function() {
			if ($("#idSaveCheck").is(":checked")) {
				var userInputId = $("input[name='id']").val();
				setCookie("userInputId", userInputId, 7);
			} else {
				deleteCookie("userInputId");
			}
		});

		$("input[name='id']").keyup(function() {
			if ($("#idSaveCheck").is(":checked")) {
				var userInputId = $("input[name='id']").val();
				setCookie("userInputId", userInputId, 7);
			}
		});
	});

	$(document).keydown(function(event) {
		if (event.keyCode == 13)
			onclickLoginBtn();
	});

	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}

	function onclickLoginBtn() {
		var id = $("input[name=id]").val();
		var pw = $("input[name=pw]").val();

		$.ajax({
			url : "memberLogin.do",
			type : "POST",
			dataType : "html",
			data : {
				"id" : id,
				"pw" : pw
			},
			success : function(errorMessage) {
				if (errorMessage != "") {
					alert(errorMessage);
					return;
				}
				location.href = "/cyber/index.do";
			},
			error : function(request, status, error) {
				alert("error : " + error);
			}
		});
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
			<div id="loginbox">
				<img alt="book" src="../resources/img/book.png"
					style="margin-left: 170px; margin-top: 10px; margin-bottom: 20px;">
				<div id="inputbox">
					<div>
						<input type="text" name="id" placeholder="아이디"
							style="margin-bottom: 10px;" value="${rememberId.getValue() }">
						<br> <input type="password" name="pw" placeholder="비밀번호">
					</div>
				</div>
				<div style="font-family: 'NanumSquare', serif; font-size: 12px;">
					<input type="checkbox" id="idSaveCheck"> 아이디 기억하기
				</div>
				<div id="buttonbox">
					<button type="button" onclick="onclickLoginBtn()">로그인</button>
					<br> <a href="/cyber/member/memberJoinRegist.do"><button
							type="submit">회원 가입</button></a> <br> <a
						href="/cyber/member/memberFindId.do"><button type="submit"
							style="width: 100px; margin-right: 1px;">ID찾기</button></a><a
						href="/cyber/member/memberFindPw.do"><button type="submit"
							style="width: 100px; margin-left: 1px;">PW 찾기</button></a>
				</div>
				<div id="naverLogin"
					style="text-align: center; padding-bottom: 10px;">
					<form action="naverAuth.do" method="post">
						<input type="image" src="../resources/img/naverLogin.png"
							style="width: 190px; height: 40px;">
					</form>
				</div>
				<div id="kakaoLogin"
					style="text-align: center; padding-bottom: 10px;">
					<form action="kakaoAuth.do" method="post">
						<input type="image" src="../resources/img/kakaoLogin.png"
							style="width: 190px; height: 40px;">
					</form>
				</div>
			</div>
		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div>
</body>
</html>