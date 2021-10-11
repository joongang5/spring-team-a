<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나만의도서관</title>
<style>
html {
	font-family: 'NanumSquare', serif;
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
.serviceBox {
	margin: 0 auto;
	padding: 0;
	width: 90%;
	height: 40%;
	background-color: #ebf7fa;
	
}
.b {
	color: #0066B3;
}
.ewqBox {
	margin: 0 auto;
	margin-top: 30px;
	padding: 0;
	width: 90%;
}
.ebBox {
	float: left;
	width: 30%;
	height: 200px;
	box-sizing: border-box;
	border: 1px solid #e1e1e1;
	background-color: #FFFFFF;
	text-align: center;
}
.wbBox {
	float: left;
	margin-left: 5%;
	width: 30%;
	height: 200px;
	box-sizing: border-box;
	border: 1px solid #e1e1e1;
	background-color: #FFFFFF;
	text-align: center;
}
.qtBox {
	float: right;
	width: 30%;
	height: 200px;
	box-sizing: border-box;
	border: 1px solid #e1e1e1;
	background-color: #FFFFFF;
	text-align: center;
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
			<c:choose>
				<c:when test="${sessionScope.id ne null}">
					<div class="serviceBox">
						<h2>나의 서비스 이용현황</h2>
						<b>${sessionScope.name }</b>님은 <b>전자책</b> 서비스를 이용하실 수 있습니다.
					</div>
					<div class="ewqBox">
						<div class="ebBox">
							<a href="ebookLoanList.do" style="text-decoration:none; color: black;">
								<img src="../resources/img/monitor.png"><br>
								전자책 대출현황<br>
								대출권수<br>
								예약권수
							</a>
						</div>
						<div class="wbBox">
							<a href="" style="text-decoration:none; color: black;">
								<img src="../resources/img/book2.png"><br>
								희망전자책 신청현황<br>
								신청권수
							</a>
						</div>
						<div class="qtBox">
							<a href="" style="text-decoration:none; color: black;">
								<img src="../resources/img/qna.png"><br>
								묻고답하기 문의현황<br>
								문의건수
							</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<a class="noLogin" href="/cyber/member/memberLogin.do">로그인해주세요.</a>
				</c:otherwise>
			</c:choose>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>