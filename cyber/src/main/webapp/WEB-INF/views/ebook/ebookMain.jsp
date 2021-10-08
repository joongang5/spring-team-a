<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반전자책</title>
<style>
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
	background: #111;
}

aside {
	float: left;
	width: 240px;
	height: 600px;
	background: #222;
}

main {
	float: left;
	width: 760px;
	min-height: 600px;
	background: #444;
}

footer {
	clear: both;
	width: 1000px;
	height: 100px;
	background: #555;
}

table {
	width: 100%;
	border: 1px solid #444444;
}

th, td {
	border: 1px solid #444444;
}

td img {
	width: 100px
}
</style>

</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

function linkPage(pageNo){
/* 	var searchOption = getParameterByName('searchOption');
	var search = getParameterByName('search'); */
	/* if(!searchOption && !search){
		location.href="./ebookMain.do?pageNo="+pageNo;
	}else{ */
		location.href="./ebookMain.do?pageNo="+pageNo;
	//}
}
</script>

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
					<select id="searchTarget">
						<option value='title'<c:if test="${searchTarget eq 'title'}">selected="selected"</c:if>>서명</option>
						<option value='author'<c:if test="${searchTarget eq 'author'}">selected="selected"</c:if>>저자</option>
						<option value='publisher'<c:if test="${searchTarget eq 'publisher'}">selected="selected"</c:if>>출판사</option>
						<option value='isbn'<c:if test="${searchTarget eq 'isbn'}">selected="selected"</c:if>>ISBN</option>
					</select>
					<input id="searchValue" value="${search}" /> <button id = "search">검색</button>
			</div>
			<div id="mainTable">
					<h1> 전체 글수 :  ${totalCount}</h1>
					<h2> 페이지 번호 :  ${pageNo}</h2>
					<table>
						<tr>
						<td>서명</td><td>표지</td><td>출판사</td><td>저자</td><td>ISBN</td>
					
					</tr>
			<c:forEach items="${EbookList }" var="l">
			<tr>
						<td><a href="/cyber/ebook/ebookDetail.do?isbn=${l.isbn }">${l.title}
						<c:if test="${l.title eq null }">
						제목없음
						</c:if>
						</a>
						</td>						
						<td>
						<img src="${l.title_url}">
						<c:if test="${l.title_url eq null }">
						<img src="/cyber/resources/img/thumbnail.gif">
						</c:if>
					
						</td>					
						<td>${l.publisher}
						<td>${l.author}</td>
						<td>${l.isbn}</td>
					</tr>
			</c:forEach>
			</table>
			</div>
			<div >
				<ul id="paging">				
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="linkPage"/>			
				</ul>
			</div>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>