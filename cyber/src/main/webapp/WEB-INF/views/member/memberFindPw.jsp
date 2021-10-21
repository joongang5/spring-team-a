<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PW 찾기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
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

#findbox {
	background-color: #e3f0ff;
	height: auto;
	width: 400px;
	margin: 0 auto;
}

#inputbox {
	text-align: center;
	padding-top: 15px;
}

input {
	height: 30px;
	width: 200px;
	border-radius: 10px;
	font-family: 'Nanum Gothic', sans-serif;
}

#buttonbox {
	margin-top: 40px;
	text-align: center;
}

button {
	width: 130px;
	height: 35px;
	text-align: center;
	font-family: 'Nanum Gothic', sans-serif;
	/*font-weight: bold;*/
	background-color: white;
	border-radius: 10px;
	margin-bottom: 10px;
}

#check {
	width: 80px;
	height: 25px;
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 14px;
}
</style>
<script type="text/javascript">
	function findPw() {
		var id = $("#id").val();

		if (id == "") {
			alert("ID를 입력해주세요.");
			return false;

		} else {
			$.ajax({
				url : "./memberFindPw.do",
				type : "POST",
				cache : false,
				dataType : "html",
				data : {
					'id' : id
				},
				success : function(data) {
					if (data == "") {
						alert("잘못된 ID입니다.");

					} else {
						alert("비밀번호는" + " " + data + "입니다.");
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
			<div id="findbox">
				<h4 style="text-align: center; position: relative; top: 10px;">
					비밀번호를 찾기 위해 <br> 가입한 ID와 이름, 이메일을 입력해주세요.
				</h4>
				<div id="inputbox">
					<div>
						<input type="text" id="id" name="id" required="required"
							placeholder="아이디" style="margin-bottom: 10px;"> <input
							type="text" id="name" name="name" required="required"
							placeholder="이름" style="margin-bottom: 10px;"> <br>
						<input type="text" id="email" name="email" required="required"
							placeholder="Email">
					</div>
					<div id="buttonbox">
						<button type="submit" onclick="findPw()">비밀번호 찾기</button>
						<button type="submit">임시 비밀번호 발급</button>
					</div>
				</div>
			</div>
		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div>
</body>
</html>