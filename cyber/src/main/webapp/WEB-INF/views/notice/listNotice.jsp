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
//페이징, 검색
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
<link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
<style>
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

#noticeBoard {
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
					<a href="../index.do">Home</a>><strong>공지사항</strong>
				</div> <!-- end of navi -->
				<h1>공지사항</h1>
			</div> <!-- end of naviandtitle -->

			<!-- 검색 기능 -->
			<div id="searchBox">
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
			</div> <!-- end of searchBox -->

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
								<td id="title"><a href="./noticeDetail.do?no=${l.no }">${l.title }</a></td>
								<td>${l.date }</td>
								<td>${l.count }</td>
								<td>${l.id }(${l.name })</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- 페이징-->
				<div id="pagination">
					<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="linkPage"/>
				</div> <!-- end of pagination -->

				<!-- 글쓰기 버튼 관리자(9등급)만 보이게 -->
				<c:if test="${sessionScope.grade eq 9 }">
					<a href="./noticeWrite.do"><button id="writebutton">글쓰기</button></a>
				</c:if>

			</div> <!-- end of noticeBoard -->

		</main>
		
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div> <!-- end of wrap -->
</body>
</html>