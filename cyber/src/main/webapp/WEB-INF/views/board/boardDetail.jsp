<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소통마당 상세보기</title>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap')
	;

html {
	font-family: 'Nanum Gothic', sans-serif;
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
	margin: 10px;
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
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	//이전글
	function preMove() {
		location.href='./boardDetail.do?no=${preNextPage.preNum }';
	}

	//다음글
	function nextMove() {
		location.href='./boardDetail.do?no=${preNextPage.nextNum }';
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
			alert("댓글이 삭제되었습니다.");
		} else {
			location.href='./boardDetail.do?no='+no;
		}
	}

	//댓글 수정
	$(document).ready(function() {
		$("button[name='commentUpdate']").click(function(e) {
		var updateInput = e.target.closest(".updateInput");
		var comment = $(updateInput).children(".comment").text();
		var no = $(updateInput).children(".no").text();
		var comment_no = $(updateInput).children(".comment_no").text();
		$(updateInput).parent().html(
			"<form action='./commentUpdate.do' method='post'>"
			+"<textarea name='comment'>"+comment+"</textarea>"
			+"<input type='hidden' name='no' value='"+no+"'>"
			+"<input type='hidden' name='comment_no' value='"+comment_no+"'>"
			+"<button>수정하기</button>"
			+"</form>"
			+"<div class='clear'>"
			+"<button name='updateCancle'>수정취소</button>"
			+"</div>");
		$("button[name='updateCancle']").click(function(){ //댓글 수정 취소
			location.reload();
		});
	});
});
	
	//댓글 페이징
	function commentLinkPage(commentPageNo) {
		location.href="./boardDetail.do?no=${detail.no}&commentPageNo="+commentPageNo;
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
			<!-- 소통마당 상세보기 list -->
			<div id="detailBoard">
				<b>번호 |</b> ${detail.no } <b>제목 |</b> ${detail.title } <b>작성자 |</b>
				${detail.id }(${detail.name }) <b>등록일 |</b> ${detail.date }<br>
				<c:if test="${sessionScope.id eq detail.id }">
					<button id="writebtn" onclick="boardDelete()">삭제하기</button>
					<button id="writebtn" onclick="boardUpdate()">수정하기</button>
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
							</div>
							<!-- end of commentId -->
							<div id="commentDate">${c.date }</div>
							<!-- end of commentDate -->
						</div>
						<!-- end of commentInfo -->
						<div class="updateBox">
							<div class="updateInput">
								<div class="comment">${c.comment }</div>
								<div class="no" style="display: none;">${c.no }</div>
								<div class="comment_no" style="display: none;">${c.comment_no }</div>

								<c:choose>
									<c:when test="${sessionScope.id eq c.id }">
										<button onclick="commentDelete(${c.no }, ${c.comment_no })"
											id="commentDeleteBtn">삭제하기</button>
										<button name="commentUpdate" id="commentUpdateBtn">수정하기</button>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</div>
							<!-- end of updateInput -->
						</div>
						<!-- end of updateBox -->
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div style="margin: 5px;">댓글이 없습니다.</div>
				</c:otherwise>
			</c:choose>

			<!-- 댓글쓰기 -->
			<c:choose>
				<c:when test="${sessionScope.id ne null }">
					<form action="commentWrite.do" method="post">
						<textarea class="commentTitle" id="commentTitle" name="comment"
							placeholder="댓글을 입력하세요."
							style="font-family: 'Nanum Gothic', sans-serif; margin: 4px;"></textarea>
						<input type="hidden" name="no" value="${detail.no}"
							style="margin: 2px;"> <br>
						<button type="submit" class="commetWriteBtn"
							style="margin-bottom: 5px;">댓글쓰기</button>
					</form>
				</c:when>
				<c:otherwise>
					로그인 후 이용해주세요.
				</c:otherwise>
			</c:choose>
			<!-- end of 댓글쓰기 -->

			<!-- 댓글 페이징-->
			<div id="commentPagination" style="margin: 4px;">
				<ui:pagination paginationInfo="${commentPaginationInfo }"
					type="text" jsFunction="commentLinkPage" />
			</div>
			<!-- end of 댓글 paging -->

			<hr>

			<!-- 이전글, 다음글 -->
			<div style="margin-bottom: 5px;">
				<c:if test="${preNextPage.preNum != null }">
					<c:if test="${preNextPage.preTitle != null }">
						이전글 | <button onclick="preMove()">${preNextPage.preTitle }</button>
						<br>
					</c:if>
				</c:if>
				<c:if test="${preNextPage.preNum == null }">
					<c:if test="${preNextPage.preTitle == null }">
						이전글이 없습니다.<br>
					</c:if>
				</c:if>
			</div>
			<div>
				<c:if test="${preNextPage.nextNum != null }">
					<c:if test="${preNextPage.nextTitle != null }">
						다음글 | <button onclick="nextMove()">${preNextPage.nextTitle }</button>
					</c:if>
				</c:if>
				<c:if test="${preNextPage.nextNum == null }">
					<c:if test="${preNextPage.nextTitle == null }">
						다음글이 없습니다.
					</c:if>
				</c:if>
			</div>
			<!-- end of 이전글, 다음글 -->

		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp" />
		</footer>
	</div>
</body>
</html>