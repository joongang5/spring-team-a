<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>정보수정</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.loginWrap {
			margin: 0 auto;
			padding: 40px 100px 50px;
			max-width: 300px;
			border: 1px solid #ccc;
			border-radius: 8px;
		}
		
		.loginWrap h4 {
			text-align: center;
		}
		
		.loginWrap .inputWrap {
			width: 100%;
			box-sizing: border-box;
			border: 1px solid #ccc;
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
					<h4>비밀번호를 입력하세요.</h4>
					<div class="inputWrap">
						<label>비밀번호</label>
						<input type="password" name="pw">
					</div>
					<p>
						<button type="submit">확인</button>
					</p>
				</form>
			</div>
		</main>
	</div>
</body>
</html>