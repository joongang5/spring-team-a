<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소통마당</title>
<script type="text/javascript">
//페이징, 검색
function linkPage(pageNo) {
	<c:if test="${searchKeyword != null}">
		var search = "&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}";
		location.href="./listBoard.do?pageNo=" + pageNo + search;
	</c:if>
	<c:if test="${searchCondition == null}">
		location.href="./listBoard.do?pageNo=" + pageNo;
	</c:if>
}
</script>
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
	height: auto; /* 수정 */
	background: #white;
	position: relative;
	left: 10px;
	top: 10px;
	padding-bottom: 100px;
}

footer {
	clear: both;
	width: 1000px;
	height: 100px;
	background: #cee5fe;
	position: relative;
	margin-top: -100px;
}

#writebutton {
	width: 100px;
	height: 35px;
	text-align: center;
	font-family: 'Nanum Gothic', sans-serif;
	background-color: white;
	border-color: #e3f0ff;
	border-radius: 10px;
	position: relative;
	left: 280px;
	top: 10px;
	margin-top: 20px;
}

/* 본문 */

h1 {
	color: #4c85d6;
}

#searchBox {
	background-color: #f2f4f7;
	padding: 10px;
	text-align: center;
}

#searchCondition {
	width: 50px;
	height: 30px;
}

#searchKeyword {
	width: 300px;
	height: 25px;
}

#searchBtn {
	width: 50px;
	height: 30px;
	background-color: #534f4f;
	color: white;
	border: 0;
	outline: 0;
}

#board {
	margin: 10px;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
	background-color: white;
	width: 700px;
	height: 400px;
}

th {
	border-top: 2px solid #534f4f;
	border-bottom: 1px solid #534f4f;
	background-color: #e3f0ff;
	padding: 10px;
}

td {
	border-bottom: 1px solid #dfe2e6;
	text-align: center;
}

#title {
	text-align: left;
}

a {
	text-decoration: none;
	color: black;
}

#title:hover {
	text-decoration: underline;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src='<c:url value="/resources/js/notice/listNotice.js"/>'></script>
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
		
			<div id="naviandtitle">
				<div id="navi">
					<a href="../index.do">Home</a>><strong>소통마당</strong>
				</div> <!-- end of navi -->
				<h1>소통마당</h1>
			</div> <!-- end of naviandtitle -->

			<!-- 검색 기능 -->
			<div id="searchBox">
				<form action="./listBoard.do" id="pageSearch" method="get">
					<select name="searchCondition" id="searchCondition" title="검색방법 선택">
						<option value="title"
							<c:if test="${searchCondition eq 'title' }">selected="selected"</c:if>>
							제목</option>
						<option value="content"
							<c:if test="${searchCondition eq 'content' }">selected="selected"</c:if>>
							내용</option>
					</select> <input type="text" name="searchKeyword" value="${searchKeyword}"
						id="searchKeyword" title="검색어 입력" placeholder="검색어를 입력해주세요.">
					<button type="submit" id="searchBtn">검색</button>
				</form>
			</div> <!-- end of searchBox -->

			<!-- 소통마당 list -->
			<div id="board">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>등록일</th>
							<th>조회수</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody id="bbody">
						<c:forEach items="${list }" var="l">
							<tr>
								<td>${l.no }</td>
								<td id="title"><a href="./boardDetail.do?no=${l.no }">${l.title }</a></td>
								<td>${l.date }</td>
								<td>${l.count }</td>
								<td>${l.id }(${l.name })</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- 페이징-->
				<div id="pagination" style="position: relative; left: 180px; top: 20px;">
					<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="linkPage"/>
				</div> <!-- end of paging -->

				<!-- 글쓰기 버튼 로그인한 사람만 보이게 -->
				<c:if test="${sessionScope.id ne null }">
					<a href="./boardWrite.do"><button id="writebutton">글쓰기</button></a>
				</c:if>

			</div> <!-- end of board -->

		</main>
		
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div> <!-- end of wrap -->
</body>
</html>