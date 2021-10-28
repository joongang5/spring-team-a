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
<link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
<style>
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
	padding-bottom: 10px;
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
					"id" : id
				},
				success : function(data) {
					if (data == "") {
						alert("잘못된 ID입니다.");

					} else {
						alert("임시 비밀번호는" + " " + data + "입니다.");
					}
				},
				error : function(request, status, error) {
					alert("실패");
				}
			});
		}
		return false;

	}
</script>
</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/component/headerInner.jsp" />
	</header>

	<div class="container">
		<div class="row">
			<aside>
				<c:import url="/WEB-INF/views/component/lnbNav.jsp" />
			</aside>
			<main>
				<div id="findbox">
					<h4 style="text-align: center; position: relative; top: 20px;">
						임시 비밀번호 발급을 위해 <br> 가입한 ID와 이름, 이메일을 입력해주세요.
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
							<button type="submit" onclick="findPw()">임시 비밀번호 발급</button>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>

	<footer>
		<c:import url="/WEB-INF/views/component/footer.jsp" />
	</footer>
</body>
</html>