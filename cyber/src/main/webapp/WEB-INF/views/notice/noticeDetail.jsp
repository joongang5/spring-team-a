<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<style type="text/css">
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
	position: relative;
	left: 10px;
	top: 10px;
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

#writebtn {
	width: 100px;
	height: 30px;
	text-align: center;
	font-family: 'Nanum Gothic', sans-serif;
	background-color: white;
	border-color: #e3f0ff;
	border-radius: 10px;
	margin-top: 10px;
}

button {
	width: 400px;
	height: 30;
	font-family: 'Nanum Gothic', sans-serif;
	text-align: left;
	background-color: white;
	border: none;
	font-size: 15px;
}
</style>
<script type="text/javascript">	
	//이전글
	function preMove() {
		<c:if test="${detail.preNum != null}">
			location.href='./noticeDetail.do?no=${detail.preNum }';
		</c:if>
		<c:if test="${detail.preNum == null}">
			location.href='./noticeDetail.do?no=${detail.no }';
		</c:if>
	}
	
	//다음글
	function nextMove() {
		<c:if test="${detail.nextNum != null}">
			location.href='./noticeDetail.do?no=${detail.nextNum }';
		</c:if>
		<c:if test="${detail.nextNum == null}">
			location.href='./noticeDetail.do?no=${detail.no }';
		</c:if>
	}
	
	//삭제 확인
	function noticeDelete() {
		if(confirm("삭제하시겠습니까?")) {
			location.href='./noticeDelete.do?no=${detail.no }';
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
				${detail.id }(${detail.name }) <b>등록일 |</b> ${detail.date }<br>
				<!-- 삭제, 수정 버튼 관리자(9등급)만 보이게 -->
				<c:if test="${sessionScope.grade eq 9 }">
					<button id="writebtn" onclick="noticeDelete()">삭제하기</button>
					<button id="writebtn" onclick="noticeUpdate()">수정하기</button>
				</c:if>
				<hr>
				${detail.content }
			</div>
			<!-- end of detailBoard -->

			<!-- 이전글, 다음글 -->
			<div style="margin-bottom: 5px;">
				<c:if test="${detail.preTitle != null }">
				이전글 | <button onclick="preMove()">${detail.preTitle }</button>
					<br>
				</c:if>
				<c:if test="${detail.preTitle == null }">
				이전글이 없습니다.<br>
				</c:if>
			</div>
			<div>
				<c:if test="${detail.nextTitle != null }">
				다음글 | <button onclick="nextMove()">${detail.nextTitle }</button>
				</c:if>
				<c:if test="${detail.nextTitle == null }">
				다음글이 없습니다.
			</c:if>
			</div>
			<!-- end of 이전글, 다음글 -->

		</main>
		<footer>footer</footer>
	</div>
</body>
</html>