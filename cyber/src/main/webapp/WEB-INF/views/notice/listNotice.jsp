<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script type="text/javascript">
function linkPage(pageNo) {
	<c:if test="${searchKeyword != null}">
		var search = "&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}";
		location.href="./listNotice.do?pageNo=" + pageNo + search;
	</c:if>
	<c:if test="${searchCondition == null}">
		location.href="./listNotice.do?pageNo=" + pageNo;
	</c:if>
}
</script>
<style>
html {
	font-family: 'NanumSquare', serif;
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

#pageSearch {
	margin-left: 10px;
}

#pageCategory {
	margin-left: 10px;
}

#noticeBoard {
	margin: 10px;
	width: 600px;
	height: 300px;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
	background-color: white;
}

th {
	background-color: #e3f0ff;
}

tr {
	border-bottom: 1px solid gray;
}

td {
	text-align: center;
}

#title {
	text-align: left;
}

a {
	text-decoration: none;
	color: black;
}

#writebutton {
	width: 100px;
	height: 35px;
	text-align: center;
	font-family: 'NanumSquare', serif;
	background-color: white;
	border-color: #e3f0ff;
	border-radius: 10px;
	margin-bottom: 10px;
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
					<a href="./index.do">Home</a>>공지사항
				</div>
				<!-- end of navi -->
				<h1>공지사항</h1>
			</div>
			<!-- end of naviandtitle -->

			<!-- 검색 기능 -->
			<form action="./listNotice.do" id="pageSearch" method="get">
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

			<!-- 공지사항 list -->
			<div id="noticeBoard">
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
					<tbody id="nbody">
						<c:forEach items="${list }" var="l">
							<tr>
								<td>${l.no }</td>
								<td><a href="./noticeDetail.do?no=${l.no }">${l.title }</a></td>
								<td>${l.date }</td>
								<td>${l.count }</td>
								<td>${l.id }(${l.name })</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- end of noticeBoard -->

			<a href="./noticeWrite.do"><button id="writebutton">글쓰기</button></a>

			<!-- 페이징-->
			<div id="pagination">
				<ui:pagination paginationInfo="${paginationInfo }" type="text"
					jsFunction="linkPage" />
			</div>
			<!-- end of paging -->
		</main>
		<footer>footer</footer>
	</div>
	<!-- end of wrap -->
</body>
</html>