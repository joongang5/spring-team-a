<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
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

				<hr>
				책소개<br> ${detail.detail0 }<br>
				<hr>
				저자<br> ${detail.detail1 }<br>
				<hr>
				목차<br> ${detail.detail2 }<br>
				<hr>
			</div>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>