<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 도서관</title>
<link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
<style>
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
	width: 90%;
	padding: 0;
}

.ebBox {
	float: left;
	width: 45%;
	height: 200px;
	box-sizing: border-box;
	border: 1px solid #e1e1e1;
	background-color: #FFFFFF;
	text-align: center;
}

.qtBox {
	float: right;
	width: 45%;
	height: 200px;
	box-sizing: border-box;
	border: 1px solid #e1e1e1;
	background-color: #FFFFFF;
	text-align: center;
}

.ewqBox img {
	margin-bottom: 10px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
						<h2 style="position: relative; left: 10px; top: 10px;">나의 서비스
							이용현황</h2>
						<div style="position: relative; left: 10px;">
							<b>${sessionScope.name }</b>님은 <b>전자책</b> 서비스를 이용하실 수 있습니다.
						</div>
					</div>
					<div class="ewqBox">
						<div class="ebBox">
							<a href="ebookLoanList.do"
								style="text-decoration: none; color: black; position: relative; top: 10px;">
								<img src="../resources/img/monitor.png"><br> 전자책 대출현황<br>
								대출권수 ${ltotalCount }권<br> 예약권수 ${reserveCount }권
							</a>
						</div>
						<div class="qtBox">
							<a href=""
								style="text-decoration: none; color: black; position: relative; top: 10px;">
								<img src="../resources/img/qna.png"><br> 나의 문의사항 현황<br>
								문의건수 건
							</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<h2>
						<a class="noLogin" href="/cyber/member/memberLogin.do">&nbsp;로그인이
							필요합니다.</a>
					</h2>
				</c:otherwise>
			</c:choose>
		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div>
</body>
</html>