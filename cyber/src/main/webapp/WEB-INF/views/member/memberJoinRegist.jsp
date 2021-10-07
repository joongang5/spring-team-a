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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("input").keyup(function() {
			var pw1 = $("#pw").val();
			var pw2 = $("#pw2").val();

			if (pw1 != "" || pw2 != "") {

				if (pw1 == pw2) {
					$("#alert-success").show();
					$("#alert-danger").hide();
					$("#submit").removeAttr("disabled");

				} else {
					$("#alert-success").hide();
					$("#alert-danger").show();
					$("#submit").attr("disabled", "disabled");
				}
			}
		});
	});
</script>
<script type="text/javascript">
	function idCheck() {
		var id = $("#id").val();
		if (id == "" || id.length < 5) {
			alert("ID는 5글자 이상 입력해주세요.");
			return false;

		} else {
			$.ajax({
				url : "./idCheck.do",
				type : "POST",
				cache : false,
				dataType : "html",
				data : {
					'id' : id
				},
				success : function(data) {
					if (data == 0) {
						alert("사용 가능한 ID입니다.");

					} else {
						alert("이미 사용 중인 ID입니다.");
					}
				},
				error : function(request, status, error) {
					alert(error);
				}
			});
		}
		return false;
	}
</script>
<script type="text/javascript">
	function emailCheck() {
		var email = $("#email").val();
		if (email == "" || email.length < 5) {
			alert("Email은 5글자 이상 입력해주세요.");
			return false;

		} else {
			if (email.indexOf("@") != -1) {
				$.ajax({
					url : "./emailCheck.do",
					type : "POST",
					cache : false,
					dataType : "html",
					data : {
						'email' : email
					},
					success : function(data) {
						if (data == 0) {
							alert("사용 가능한 Email입니다.");

						} else {
							alert("이미 사용 중인 Email입니다.");
						}
					},
					error : function(request, status, error) {
						alert(error);
					}
				});
			} else {
				alert("올바른 Email을 입력해주세요.");
			}
		}
		return false;
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
				<img alt="registration" src="../resources/img/registration.png"
					style="margin-left: 170px; margin-top: 10px; margin-bottom: 20px;">
				<div id="inputbox">
					<form action="memberJoinRegist.do" method="post">
						<div>
							<input type="text" id="name" name="name" required="required"
								placeholder="이름" style="margin-bottom: 10px;"> <br>
							<input type="text" id="id" name="id" required="required"
								placeholder="아이디" style="margin-bottom: 10px;"
								onchange="idCheck()"> <br> <input type="password"
								id="pw" name="pw" class="form-control" required="required"
								placeholder="비밀번호" style="margin-bottom: 10px;"><br>
							<input type="password" id="pw2" name="pw" class="form-control"
								required="required" placeholder="비밀번호 확인"
								style="margin-bottom: 10px;">
							<div class="alert alert-success" id="alert-success"
								style="font-family: 'NanumSquare', serif; margin-bottom: 10px;">비밀번호가
								일치합니다.</div>
							<div class="alert alert-danger" id="alert-danger"
								style="font-family: 'NanumSquare', serif; margin-bottom: 10px;">비밀번호가
								일치하지 않습니다.</div>
							<input type="text" id="email" name="email" required="required"
								placeholder="Email" onchange="emailCheck()">
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