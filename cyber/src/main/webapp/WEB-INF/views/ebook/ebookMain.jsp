<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 전자책</title>
<link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
<style>
table {
	width: 100%;
}

th, td {
	border-bottom: 1px solid #c5c5c5;
}

td img {
	width: 100px;
}

#SearchTarget {
	background-color: #f2f4f7;
	padding: 10px;
	text-align: center;
}

#searchTarget {
	width: 50px;
	height: 30px;
}

#searchValue {
	width: 300px;
	height: 25px;
}

#search {
	width: 50px;
	height: 30px;
	background-color: #534f4f;
	color: white;
	border: 0;
	outline: 0;
}
</style>
</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	function getParameterByName(name) {
		name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
				.exec(location.search);
		return results === null ? "" : decodeURIComponent(results[1].replace(
				/\+/g, " "));
	}
	function linkPage(pageNo) {
		var searchTarget = getParameterByName('searchTraget');
		var searchValue = getParameterByName('searchValue');
		if (!searchTarget && !searchValue) {
			location.href = "./ebookMain.do?pageNo=" + pageNo;
		} else {
			location.href = "./ebookMain.do?pageNo=" + pageNo + '&searchTarge='
					+ searchTarget + '&searchValue=' + searchValue;
		}
	}
</script>
<body>
	<header>
		<c:import url="/WEB-INF/views/component/headerInner.jsp" />
	</header>
	
	<div class="container">
		<div class="row">
			<aside>
				<c:import url="/WEB-INF/views/component/lnbNav.jsp" />
			</aside>
			<main>
				<div id="SearchTarget">
					<form action="./ebookMain.do" method="GET">
						<select name="searchTarget" id="searchTarget">
							<option value='title'
								<c:if test="${searchTarget eq 'title'}">selected="selected"</c:if>>서명</option>
							<option value='author'
								<c:if test="${searchTarget eq 'author'}">selected="selected"</c:if>>저자</option>
							<option value='publisher'
								<c:if test="${searchTarget eq 'publisher'}">selected="selected"</c:if>>출판사</option>
							<option value='isbn'
								<c:if test="${searchTarget eq 'isbn'}">selected="selected"</c:if>>ISBN</option>
						</select> <input name="searchValue" id ="searchValue" value="${searchValue}" />
						<button id="search" type="submit">검색</button>
					</form>
				</div>
				<c:choose>
					<c:when test="${fn:length(EbookList) gt 0 }">
						<div id="mainTable">
							<c:if test="${searchValue ne null }">
								<h2>도서 수 : ${EbookList.get(0).totalCount }</h2>
								<h3>페이지 번호 : ${pageNo}</h3>
								<h3>검색어 : ${searchValue }</h3>
							</c:if>
		
							<table>
								<c:forEach items="${EbookList }" var="l">
								
									<td>
									<div style="position: absolute; border:1px solid #c5c5c5; ">
									<a href="/cyber/ebook/ebookDetail.do?isbn=${l.isbn }">
									<img src="${l.title_url}"> <c:if
												test="${l.title_url eq null }">
												<img src="/cyber/resources/img/thumbnail.gif">
									</c:if>
									</a>
									</div>
									<div style="margin-left: 125px;line-height:2;">
									<strong><a href="/cyber/ebook/ebookDetail.do?isbn=${l.isbn }"> ${l.title}
												<c:if test="${l.title eq null }">
							제목없음
							</c:if>
							</a>
							</strong>
							<br>
										${l.author}<br>
										출판사 : ${l.publisher}<br>
										발행일 : ${l.datetime}<br>
										ISBN : ${l.isbn}<br>
									</div>
									</td>
								
									<tr onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${l.isbn }'" style="cursor:pointer;">
										
									</tr>
								</c:forEach>
							</table>
						</div>
						<div>
							<ul id="pagination">
								<ui:pagination paginationInfo="${paginationInfo }" type="text"
									jsFunction="linkPage" />
							</ul>
						</div>
					</c:when>
					<c:otherwise>
				검색된 도서가 없습니다.<br>
				잠시후 전자책 페이지로 이동합니다.
				<meta http-equiv="refresh"
							content="3; url='/cyber/ebook/ebookMain.do'">
					</c:otherwise>
				</c:choose>
			</main>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/component/footer.jsp" />
	</footer>
</body>
</html>