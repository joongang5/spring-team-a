<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>일반전자책</title>
<style>
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

.btnGroup {
	overflow: hidden;
	position: relative;
	margin: 30px auto;
	text-align: center
}

.btnGroup:after {
	content: "";
	display: block;
	clear: both
}

.btn-link {
	color: #526CA5
} /*링크*/
.btn-link:hover, .btn-link:hover:focus {
	text-decoration: underline
}

.btn {
	display: inline-block;
	min-width: 80px;
	padding: 7px 15px;
	line-height: 20px;
	color: #fff;
	text-align: center;
	letter-spacing: -1px;
	vertical-align: middle;
	box-sizing: border-box;
	background-color: #666
}

.btn+.btn {
	margin-left: 3px
}

#myform fieldset {
	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
	direction: rtl; /* 이모지 순서 반전 */
	border: 0; /* 필드셋 테두리 제거 */
}

#myform fieldset legend {
	text-align: left;
}

#myform input[type=radio] {
	display: none; /* 라디오박스 감춤 */
}

#myform label {
	font-size: 1.5em; /* 이모지 크기 */
	color: transparent; /* 기존 이모지 컬러 제거 */
	text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
}

#myform label:hover {
	text-shadow: 0 0 0 #cee5fe; /* 마우스 호버 */
}

#myform label:hover ~ label {
	text-shadow: 0 0 0 #cee5fe; /* 마우스 호버 뒤에오는 이모지들 */
}

#myform input[type=radio]:checked ~ label {
	text-shadow: 0 0 0 #cee5fe; /* 마우스 클릭 체크 */
}
</style>
</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="./resources/js/data.js"></script>

<script>
	$(document).ready(function() {
		//alert("정상작동");
		//emp();
		paging(1);
		$("#search").click(function() {
			var searchTitle = $('#searchTitle').val();
			$.ajax({
				url : "./ebookMain.do",
				type : "POST",
				cache : false,
				dataType : "json",
				data : {
					"title" : searchTitle
				},
				success : function(data) {
					//alert("정상 : pageNo : " + data.PAGE_NO);
					//alert("정상 : list : " + data.docs);
					alert('??');
					emp(data);
				},
				error : function(requst, status, error) {
					alert("error : " + error);
				}
			});
		});

	});
	function content1() {
		document.getElementById("content1").style.display = "";
		document.getElementById("content2").style.display = "none";
	}

	function content2() {
		document.getElementById("content2").style.display = "";
		document.getElementById("content1").style.display = "none";
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
			<div id="mainTable">
				<img src="${ebookDetail.title_url}" style="width: 100px"><br>
				제목 : ${ebookDetail.title}<br> 저자 : ${ebookDetail.author}<br>
				출판사 : ${ebookDetail.publisher}<br> ISBN : ${ebookDetail.isbn}<br>
				출판일 : ${ebookDetail.datetime}<br> 가격 : ${ebookDetail.price}<br>
				페이지 : ${ebookDetail.page}<br> 책 크기 : ${ebookDetail.book_size }

				<div class="btnGroup">
					<a href="/cyber/ebook/ebookMain.do" class="btn list" id="listBtn">목록</a>
					<a href="" class="btn themeBtn">대여</a> <a href=""
						class="btn themeBtn2">관심목록 담기</a>
				</div>
				<input type="button" onclick="content1()" value="책소개" /> <input
					type="button" onclick="content2()" value="목차" /> <input
					type="button" id="content3" class="content3" value="별점/리뷰" />
				<div id="content1" class="content2" style="">
					<hr>
					책소개<br> ${detail.detail0 }<br>
					<hr>
					저자<br> ${detail.detail1 }<br>
					<hr>
				</div>
				<div id="content2" class="content2" style="display: none;">
					<hr>
					목차<br> ${detail.detail2 }
				</div>
				<form name="myform" id="myform" method="post" action="./ebookRating.do">
					<fieldset>
						<legend>별점</legend>
					<div>
						<input type="radio" name="rating" value="5" id="rate1"><label
							for="rate1">★</label> <input type="radio" name="rating" value="4"
							id="rate2"><label for="rate2">★</label> <input
							type="radio" name="rating" value="3" id="rate3"><label
							for="rate3">★</label> <input type="radio" name="rating" value="2"
							id="rate4"><label for="rate4">★</label> <input
							type="radio" name="rating" value="1" id="rate5"><label
							for="rate5">★</label>
					</div>
					</fieldset>
					<button type="submit">제출</button>
				</form>
			</div>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>