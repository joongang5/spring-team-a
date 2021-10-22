<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 도서관</title>
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

.libtn {
	background-color: #74adea;
	font-family: sans-serif;
	font: bold;
	color: white;
	position: relative;
	left: 10px;
	margin-bottom: 10px;
}

.infoBox {
	margin: 0 auto;
	padding: 0;
	background-color: #f2f2f2;
	font-family: sans-serif;
	margin-left: 10px;
	margin-top: 10px;
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

#paging {
	text-align: center;
	margin-top: 10px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	function linkPage(pageNo){
		location.href="./ebookReviewList.do?pageNo="+pageNo;
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
			<div>
				<c:choose>
					<c:when test="${sessionScope.id ne null}">
						<a class="libtn" href="ebookLoanList.do">내 서재</a>
						<a class="libtn">나의별점/리뷰</a>
						<a class="libtn" href="">관심 목록</a>
						<div class="infoBox">
							- 올바른 리뷰 문화를 지켜주세요.<br>
						</div>
						<hr>
						<table>
							<tr>
								<th>표지</th>
								<th>제목</th>
								<th>별점</th>
								<th>리뷰</th>
								<th>작성일</th>
							</tr>
						</table>
						<table>
							<c:choose>
								<c:when test="${fn:length(reviewDTOList) gt 0 }">
									<c:forEach items="${reviewDTOList }" var="r">
										<tr>
											<td><img alt="book" src="${r.title_url }"
												style="width: 50px; height: 70px; vertical-align: middle;"></td>
											<td>${r.title }</td>
											<td>${r.author }</td>
											<td><img alt="star"
							src="/cyber/resources/img/star${r.rating }.png"></td>
											<td>${r.reviewCmt }</td>
											<td>${r.date }</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
								작성한 리뷰가 없습니다.
							</c:otherwise>
							</c:choose>
						</table>
					</c:when>
					<c:otherwise>
						<a class="noLogin" href="/cyber/member/memberLogin.do">로그인
							해주세요.</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<ul id="paging">
					<ui:pagination paginationInfo="${paginationInfo }" type="text"
						jsFunction="linkPage" />
				</ul>
			</div>
		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div>
</body>
</html>