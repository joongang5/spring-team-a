<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
	background: white;
}

main {
	width: 1000px;
	height: 600px;
	background: #white;
}

footer {
	clear: both;
	width: 1000px;
	height: 100px;
	background: #cee5fe;
}

@media ( max-width : 960px) {
	.header {
		float: none;
		width: 100%;
	}
	.main>div {
		width: width:calc(50% - 10px);
	}
	.footer {
		width: width:calc(50% - 10px);
	}
}

.section input[id*="slide"] {
	display: none;
}

.section .slidewrap {
	max-width: 1000px;
	margin: 0 auto;
	overflow: hidden;
}

.section .slidelist {
	white-space: nowrap;
	font-size: 0;
}

.section .slidelist>li {
	display: inline-block;
	vertical-align: middle;
	width: 100%;
	transition: all .5s;
}

.section .slidelist>li>a {
	display: block;
	position: relative;
}

.section .slidelist>li>a img {
	width: 100%;
}

.section .slidelist label {
	position: absolute;
	z-index: 10;
	top: 50%;
	transform: translateY(-50%);
	padding: 50px;
	cursor: pointer;
}

.section .slidelist .left {
	left: 30px;
	background: url("./resources/img/left.png") center center/100% no-repeat;
}

.section .slidelist .right {
	right: 30px;
	background: url("./resources/img/right.png") center center/100%
		no-repeat;
}

.section [id="slide01"]:checked ~ .slidewrap .slidelist>li {
	transform: translateX(0%);
}

.section [id="slide02"]:checked ~ .slidewrap .slidelist>li {
	transform: translateX(-100%);
}

.section [id="slide03"]:checked ~ .slidewrap .slidelist>li {
	transform: translateX(-200%);
}

.section [id="slide04"]:checked ~ .slidewrap .slidelist>li {
	transform: translateX(-300%);
}

.section [id="slide05"]:checked ~ .slidewrap .slidelist>li {
	transform: translateX(-400%);
}
</style>
</head>
<body>
	<div id="wrap">
		<div class="header clearfix">
			<header>
				<c:import url="/WEB-INF/views/component/headerInner.jsp" />
			</header>
		</div>
		<div class="main clearfix">
			<main>
				<div id="newList">
					<h2>이 달의 신작</h2>
				</div>
				<div id="bestList">
					<h2>많이 본 책</h2>
					<c:forEach items="${bookStorageViewDTOList }" var="l">
						<div class="section">
							<input type="radio" name="slide" id="slide01" checked> <input
								type="radio" name="slide" id="slide02"> <input
								type="radio" name="slide" id="slide03"> <input
								type="radio" name="slide" id="slide04"> <input
								type="radio" name="slide" id="slide05">
						</div>
						<div class="slidewrap">
							<ul class="slidelist">
								<li><a> <label for="slide05" class="left"></label> <img
										src="${EbookList.title_url }" style="width: 100px"> <c:if
											test="${EbookList.title_url eq null }">
											<img src="/cyber/resources/img/thumbnail.gif">
										</c:if> ${l.title }&nbsp;|&nbsp;${l.author } <label for="slide04"
										class="right"></label></a></li>
							</ul>
						</div>
					</c:forEach>
				</div>
			</main>
		</div>
		<div class="footer clearfix">
			<footer>이용약관</footer>
		</div>
	</div>
</body>
</html>