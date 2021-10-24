<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
<link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
<style type="text/css">
#updateBtn {
	background-color: #ffe4f3;
	border-radius: 10px;
	border: none;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#updateBtn").click(function() {
			//location.href = "/cyber/myPage/memberUpdate.do";				
		});
	});
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
			<form action="memberUpdate.do" method="post">
				<div style="margin: 0 auto; width: 300px; text-align: center;">
					<h2>회원정보수정</h2>
					<hr>
				</div>
				<div
					style="margin: 5px; width: 300px; position: relative; left: 250px;">
					<div style="margin-bottom: 2px;">아이디 : ${memberDTO.id }</div>
					<div style="margin-bottom: 2px;">이름 : ${memberDTO.name }</div>
					<div style="margin-bottom: 2px;">
						비밀번호 : <input type="text" name="pw">
					</div>
					<div>
						이메일 : <input type="text" name="email" value="${memberDTO.email }">
					</div>
				</div>
				<div
					style="margin-top: 10px; width: 300px; position: relative; left: 330px;">
					<input type="submit" value="회원정보 수정" id="updateBtn"
						style="width: 100px; height: 40px;">
				</div>
			</form>
		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div>
</body>
</html>