<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세보기</title>
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
		<c:if test="${detail.preNum != null}">
			location.href='./boardDetail.do?no=${detail.preNum }';
		</c:if>
		<c:if test="${detail.preNum == null}">
			location.href='./boardDetail.do?no=${detail.no }';
		</c:if>
	}

	//다음글
	function nextMove() {
		<c:if test="${detail.nextNum != null}">
			location.href='./boardDetail.do?no=${detail.nextNum }';
		</c:if>
		<c:if test="${detail.nextNum == null}">
			location.href='./boardDetail.do?no=${detail.no }';
		</c:if>
	}
	
	//삭제 확인
	function boardDelete() {
		if(confirm("삭제하시겠습니까?")) {
			location.href='./boardDelete.do?no=${detail.no }';
			alert("게시글이 삭제되었습니다.");
		} else {
			location.href='./boardDetail.do?no=${detail.no }';
		}
	}
	
	//수정 확인
	function boardUpdate() {
		if(confirm("수정하시겠습니까?")) {
			location.href='./boardUpdate.do?no=${detail.no }';
		} else {
			location.href='./boardDetail.do?no=${detail.no }';
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
			<!-- 게시글 상세보기 -->
			<div id="detailBoard">
				<b>번호 |</b> ${detail.no } <b>제목 |</b> ${detail.title } <b>작성자 |</b>
				${detail.id }(${detail.name }) <b>등록일 |</b> ${detail.date }
				<c:if test="${sessionScope.id ne null }">
					<c:if test="${sessionScope.id eq detail.id }">
						<button onclick="boardDelete()">삭제하기</button>
						<button onclick="boardUpdate()">수정하기</button>
					</c:if>
				</c:if>
				<hr>
				${detail.content }
			</div> 
			<!-- end of detailBoard -->
			
			<hr>
			
			<!-- 댓글 불러오기 -->
			<div id="commentBoard">
				<c:forEach items="${commentList }" var="c">
					<tr>
						<td>${c.no }</td>
						<td>${c.id }(${c.name })</td>
						<td>${c.comment }</td>
						<td>${c.date }</td>
					</tr>
					<br>
				</c:forEach>
			</div> 
			<!-- end of commentBoard -->
			
			<!-- 댓글쓰기 -->
			<c:choose>
				<c:when test="${sessionScope.id ne null }">
					<form action="commentWrite.do" method="post">
						<textarea class="commentTitle" id="commentTitle" name="comment"
							placeholder="댓글을 입력하세요."></textarea>
						<input type="hidden" name="no" value="${detail.no}">
						<br>
						<button type="submit" class="commetWriteBtn">댓글쓰기</button>
					</form>
				</c:when>
				<c:otherwise>
					로그인 후 이용해주세요.
				</c:otherwise>
			</c:choose>
			<!-- end of 댓글쓰기 -->
			
			<hr>
			
			<!-- 이전글, 다음글 -->
			<c:if test="${detail.preTitle != null }">
				이전글 | <button onclick="preMove()">${detail.preTitle }</button><br>
			</c:if>
			<c:if test="${detail.preTitle == null }">
				이전글이 없습니다.<br>
			</c:if>
			<c:if test="${detail.nextTitle != null }">
				다음글 | <button onclick="nextMove()">${detail.nextTitle }</button>
			</c:if>
			<c:if test="${detail.nextTitle == null }">
				다음글이 없습니다.
			</c:if>
			<!-- end of 이전글, 다음글 -->
			
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>