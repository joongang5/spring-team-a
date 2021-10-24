<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
<style type="text/css">
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

/* 본문 */

table {
	border-collapse: collapse;
	border-spacing: 0;
	background-color: white;
	height: 100px;
}

th {
	border-bottom: 1px solid #dfe2e6;
	background-color: #e3f0ff;
	padding: 10px;
	width: 100px;
}

td {
	border-bottom: 1px solid #dfe2e6;
	text-align: left;
	width: 500px;
}

#detailNo {
	border-top: 2px solid #534f4f;
}

#detailFile {
	margin-top: 20px;
	padding: 10px;
}

#detailContent {
	margin-top: 30px;
}

#prePage {
	border-top: 1px solid #dfe2e6;
	border-bottom: 1px solid #dfe2e6;
	padding: 10px;
}

#nextPage {
	border-bottom: 1px solid #dfe2e6;
	padding: 10px;
}

button:hover {
	text-decoration: underline;
	cursor: pointer;
}

#backList {
	width: 50px;
	height: 30px;
	background-color: #534f4f;
	color: white;
	border: 0;
	outline: 0;
	padding: 10px;
}

#backListBox {
	text-align: center;
	margin-top: 20px;
}

h2 {
	color: #4c85d6;
}
</style>
<script type="text/javascript">
	//이전글
	function preMove() {
		location.href = './noticeDetail.do?no=${preNextPage.preNum }';
	}

	//다음글
	function nextMove() {
		location.href = './noticeDetail.do?no=${preNextPage.nextNum }';
	}

	//삭제 확인
	function noticeDelete() {
		if (confirm("삭제하시겠습니까?")) {
			location.href = './noticeDelete.do?no=${detail.no }';
			alert("게시글이 삭제되었습니다.");
		} else {
			location.href = './noticeDetail.do?no=${detail.no }';
		}
	}

	//수정 확인
	function noticeUpdate() {
		if (confirm("수정하시겠습니까?")) {
			location.href = './noticeUpdate.do?no=${detail.no }';
		} else {
			location.href = './noticeDetail.do?no=${detail.no }';
		}
	}
</script>
</head>
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
		
				<div id="naviandtitle">
					<div id="navi">
						<a href="../index.do">Home</a>><strong>공지사항</strong>
					</div> <!-- end of navi -->
					<h2>공지사항</h2>
				</div> <!-- end of naviandtitle -->
		
				<!-- 공지사항 상세보기 list -->
				<div id="detailBoard">
					<table>
						<tr id="detailNo">
							<th>번호</th>
							<td>${detail.no }</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>${detail.title }</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${detail.id }<small>(${detail.name })</small></td>
						</tr>
						<tr>
							<th>등록일</th>
							<td>${detail.date }</td>
						</tr>
					</table>
		
					<!-- 삭제, 수정 버튼 관리자(9등급)만 보이게 -->
					<c:if test="${sessionScope.grade eq 9 }">
						<!-- 삭제, 수정 버튼 본인글은 본인만 보이게 -->
						<c:if test="${sessionScope.id eq detail.id }">
							<button id="writebtn" onclick="noticeDelete()">삭제하기</button>
							<button id="writebtn" onclick="noticeUpdate()">수정하기</button>
						</c:if>
					</c:if>
					
					<!-- 파일 보이게 -->
					<div id="detailFile">
						<c:if test="${detail.file != null }">
							<img alt="이미지" src="../resources/upfile/${detail.file }">
						</c:if>
						<div id="detailContent">${detail.content }
						</div> <!-- end of detailContent -->
					</div> <!-- end of detailFile -->
		
					<!-- 이전글, 다음글 -->
					<div id="prePage" style="margin-top: 30px;">
						<c:if test="${preNextPage.preNum != null }">
							<c:if test="${preNextPage.preTitle != null }">
								<b>이전글 |</b>
								<button onclick="preMove()">${preNextPage.preTitle }</button>
								<br>
							</c:if>
						</c:if>
						<c:if test="${preNextPage.preNum == null }">
							<c:if test="${preNextPage.preTitle == null }">
							이전글이 없습니다.<br>
							</c:if>
						</c:if>
					</div>
					<div id="nextPage">
						<c:if test="${preNextPage.nextNum != null }">
							<c:if test="${preNextPage.nextTitle != null }">
								<b>다음글 |</b>
								<button onclick="nextMove()">${preNextPage.nextTitle }</button>
							</c:if>
						</c:if>
						<c:if test="${preNextPage.nextNum == null }">
							<c:if test="${preNextPage.nextTitle == null }">
							다음글이 없습니다.
						</c:if>
						</c:if>
					</div> <!-- end of 이전글, 다음글 -->
					
					<!-- 목록 -->
					<div id="backListBox">
						<a href="./listNotice.do" id="backList">목록</a>
					</div> <!-- end of backListBox -->
		
				</div> <!-- end of detailBoard -->
				
			</main>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/component/footer.jsp" />
	</footer>
</body>
</html>