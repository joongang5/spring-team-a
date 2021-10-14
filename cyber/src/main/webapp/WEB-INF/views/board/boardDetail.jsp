<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소통마당 상세보기</title>
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
	height: auto;
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
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
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
	
	//댓글 삭제 확인
	function commentDelete(no, comment_no) {
		if(confirm("삭제하시겠습니까?")) {
			location.href='./commentDelete.do?no='+no+'&comment_no='+comment_no;
			alert("게시글이 삭제되었습니다.");
		} else {
			location.href='./boardDetail.do?no='+no;
		}
	}
	
	//댓글 수정
	$(document).ready(function() {
	$("button[name='commentUpdate']").click(function(e) {
		var comment = e.target.closest(".updateInput");
		var no = $(comment).children(".no").text();
		var comment_no = $(comment).children(".comment_no").text();
		$(comment).parent().html(
			"<form action='./commentUpdate.do' method='post'>"
			+"<textarea name='comment'></textarea>"
			+"<input type='hidden' name='no' value='"+no+"'>"
			+"<input type='hidden' name='comment_no' value='"+comment_no+"'>"
			+"<button>수정하기</button>"
			+"</form>"
			+"<div class='cancle'>"
			+"<button name='updateCancle'>수정취소</button>"
			+"</div>");
		$("button[name='updateCancle']").click(function(){ //수정취소를 하면
			location.reload();
		});
	});
});
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
			
			<!-- 댓글 불러오기, 삭제, 수정 -->
			<c:choose>
				<c:when test="${fn:length(commentList) > 0 }">
					<c:forEach items="${commentList }" var="c">
						<div id="commentInfo">
							<div id="commentId">
								${c.comment_no } / ${c.name }(<small>${c.id }</small>)
							</div> <!-- end of commentId -->
							<div id="commentDate">
					 			${c.date }
					 		</div> <!-- end of commentDate -->
						</div> <!-- end of commentInfo -->
					 	<div class="updateBox">
					 	<div class="updateInput">
					 	<div class="comment">${c.comment }</div>
					 	<div class="no" style="display: none;">${c.no }</div>
					 	<div class="comment_no" style="display: none;">${c.comment_no }</div>
					 	
					 	<c:choose>
					 		<c:when test="${c.id eq sessionScope.id }">
					 			<button onclick="commentDelete(${c.no }, ${c.comment_no })" id="commentDeleteBtn">삭제하기</button>
					 			<button name="commentUpdate" id="commentUpdateBtn">수정하기</button>
							</c:when>
					 		<c:otherwise>
					 		</c:otherwise>
					 	</c:choose>
						</div> <!-- end of updateInput -->
						 </div> <!-- end of updateBox -->
					</c:forEach>	
						</c:when>
							<c:otherwise>
								댓글이 없습니다.
							</c:otherwise>
			</c:choose>
			
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