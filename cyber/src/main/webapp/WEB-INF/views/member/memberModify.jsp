<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>정보수정</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("#updateBtn").click(function () {
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
				<div style="margin: 0 auto; width: 300px;">
					<h1>회원정보수정</h1>
					<hr>
				</div>
				<div style="margin: 0 auto; width: 300px;">
					아이디 : ${memberDTO.id }<br>
					이름 : ${memberDTO.name }<br>
					비밀번호 : <input type="text" name="pw"><br>
					이메일 : <input type="text" name="email" value="${memberDTO.email }">
				</div>
				<div style="margin: 0 auto; width: 300px;">
					<input type="submit" value="회원정보수정" id="updateBtn">
				</div>
			</form>
		</main>
	</div>
</body>
</html>