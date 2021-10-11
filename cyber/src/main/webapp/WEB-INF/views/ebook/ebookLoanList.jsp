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

.libtn {
	background-color: #0066b3;
	font-family: sans-serif;
	font: bold;
	color: white;
}

.infoBox {
	margin: 0 auto;
	padding: 0;
	background-color: #f2f2f2;
	font-family: sans-serif;
}

table {
	margin: 0 auto;
	padding: 0;
	width: 95%;	
	border-collapse: collapse;
	line-height: 2.5;
	border: 1px solid #828282;
	table-layout: fixed;
	font-size: 10px;
	text-align: center;
}

td {
	border-bottom: 1px solid #828282;
	height: 100px;
}

.noLogin {
	margin: 0 auto;
	padding: 0;
	text-align: center;
}

button {
	vertical-align: middle;
	margin: 0 auto;
	padding: 20 10;
	text-align: center;
	line-height: 20px;
	letter-spacing: 0;
	cursor: pointer;
}

.lobtn {
	background-color: #0066b3;
	border: #0066b3;
	color: #fff;
}

.rebtn {
	background-color: #6E2FC7;
	border: #6E2FC7;
	color: #fff;
}

.extbtn {
	background-color: #fbfbfb;;
	border: #fbfbfb;;
	color: #555;
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
			<div>
				<c:choose>
					<c:when test="${sessionScope.id ne null}">
						<a class="libtn" href="ebookLoanList.do">내서재</a>
						<a class="libtn" href="">나의별점/리뷰</a>
						<a class="libtn" href="">관심목록</a>
						<div class="infoBox">
							- 현재 대출중인 전자책을 확인하고, 전자책을 보거나 반납할 수 있습니다.<br>
							- 대출한 책은 반납예정일이 지나면 자동 반납되며, [반납하기]를 눌러서 미리 반납도 가능합니다.
						</div><hr>
						<table>
							<tr>
								<th>표지</th>
								<th>제목</th>
								<th>저자</th>
								<th>출판사</th>
								<th>대출일</th>
								<th>반납예정일</th>
								<th>반납/보기/연장</th>
							</tr>
						</table>
						<table>
						<c:choose>
							<c:when test="${fn:length(loanViewDTOList) gt 0 }">
								<c:forEach items="${loanViewDTOList }" var="l" >
								<tr>
									<td><img alt="book" src="${l.title_url }" style="width: 50px; height: 70px; vertical-align: middle;"></td>
									<td>${l.title }</td>
									<td>${l.author }</td>
									<td>${l.publisher }</td>
									<td>${l.loan_date }</td>
									<td>${l.return_date }</td>
									<td>
									<button class="lobtn">보기</button><br>
									<button class="rebtn" onclick="retfunction(${l.book_no})">반납하기</button><br>
									<button class="extbtn">연장하기</button>
									</td>
								</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								대출중인 전자책이 없습니다.
							</c:otherwise>
						</c:choose>
						</table>
					</c:when>
					<c:otherwise>
						<a class="noLogin" href="/cyber/member/memberLogin.do">로그인 해주세요.</a>
					</c:otherwise>
				</c:choose>
			</div>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>