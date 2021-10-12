<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<style type="text/css">
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

#detailBoard {
	width: 600px;
	height: 300px;
}

button {
	width: 100px;
	height: 30px;
	text-align: center;
	font-family: 'NanumSquare', serif;
	background-color: white; border-color : #e3f0ff; border-radius : 10px;
	margin-bottom: 10px;
	border-radius: 10px;
	border-radius: 10px;
	border-color: #e3f0ff;
	border-radius: 10px;
}
</style>
<script type="text/javascript">
	//이전글
	function preMove() {
		location.href='./listNotice.do';
	}
	
	//다음글
	function nextMove() {
		location.href='./listNotice.do';
	}
	
	//삭제 확인
	function noticeDelete() {
		if(confirm("삭제하시겠습니까?")) {
			location.href='./delete.do?no=${detail.no }';
			alert("게시글이 삭제되었습니다.");
		} else {
			location.href='./noticeDetail.do?no=${detail.no }';
		}
	}
	
	//수정 확인
	function noticeUpdate() {
		if(confirm("수정하시겠습니까?")) {
			location.href='./noticeUpdate.do?no=${detail.no }';
		} else {
			location.href='./noticeDetail.do?no=${detail.no }';
		}
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
			<div id="detailBoard">
				<b>번호 |</b> ${detail.no } <b>제목 |</b> ${detail.title } <b>작성자 |</b>
				${detail.id }(${detail.name }) <b>등록일 |</b> ${detail.date }
				<c:if test="${sessionScope.id ne null }">
					<button onclick="noticeDelete()">삭제하기</button>
					<button onclick="noticeUpdate()">수정하기</button>
				</c:if>
				<hr>
				${detail.content }
			</div>
			<!-- end of detailBoard -->
			<button onclick="preMove()">이전글</button>
			<button onclick="nextMove()">다음글</button>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>