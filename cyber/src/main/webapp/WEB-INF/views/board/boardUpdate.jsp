<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소통마당 수정하기</title>
<link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
<style type="text/css">
button {
	width: 100px;
	height: 35px;
	text-align: center;
	font-family: 'Nanum Gothic', sans-serif;
	background-color: white;
	border-color: #e3f0ff;
	border-radius: 10px;
	margin-bottom: 10px;
}

/* 본문 */

table {
	margin: 0 auto;
	margin-top: 10px;
}

#boardTitle {
	width: 600px;
	height: 30px;
}

#boardContent {
	width: 600px;
	height: 500px;
}

th {
	background-color: #e3f0ff;
	padding: 10px;
}

#writeBtnBox {
	text-align: center;
}

h2 {
	color: #4c85d6;
}

#naviandtitle {
	margin: 10px;
}
</style>
<script type="text/javascript">
	function check() {
		var title = document.getElementById("boardTitle");
		var content = document.getElementById("boardContent");

		if (title.value.length < 5 || title.value == "") {
			alert("제목을 5글자 이상 적어주세요.");
			title.focus();
			return false;
		}
		if (content.value == "" || content.value.length < 5) {
			alert("내용을 5글자 이상 적어주세요.");
			content.focus();
			return false;
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
						<a href="../index.do">Home</a>><strong>소통마당</strong>
					</div> <!-- end of navi -->
					<h2>수정하기</h2>
				</div> <!-- end of naviandtitle -->
		
				<form action="boardUpdate.do" method="post" onsubmit="return check();">
					<table>
						<tr>
							<th>제목</th>
							<td><input class="boardTitle" id="boardTitle" name="title"
								value="${detail.title}"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="boardContent" id="boardContent"
									name="content" rows="10">${detail.content}</textarea></td>
						</tr>
					</table>
					<input type="hidden" name="no" value="${detail.no}">
					<div id="writeBtnBox">
						<button type="submit" class="writeBtn">수정하기</button>
					</div> <!-- end of writeBtnBox -->
				</form>
				
			</main>
		</div>
	</div>
	
	
	<footer>
		<c:import url="/WEB-INF/views/component/footer.jsp" />
	</footer>
</body>
</html>