<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap')
	;

html {
	font-family: 'Nanum Gothic', sans-serif;
	color: #black;
	width: 100%;
}

body {
	margin: 0;
	padding: 0;
	width: 100%;
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
	height: 900px;
	background: #white;
}

footer {
	clear: both;
	width: 1000px;
	height: 100px;
	background: #cee5fe;
}

@media ( max-width : 760px) {
	#wrap div {
		width: 500px;
		float: none;
	}
}

.slider {
	width: 640px;
	height: 400px;
	position: relative;
	margin: 0 auto;
}

.slider input[type=radio] {
	display: none;
}

ul.imgs {
	padding: 0;
	margin: 0;
	position: absolute;
	top: 100px;
	left: 35%;
}

ul.imgs li {
	position: absolute;
	opacity: 0;
	list-style: none;
	padding: 0;
	margin: 0;
	transition-delay: 0.01s;
	width: 200px;
}

.bullets {
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	bottom: 20px;
	z-index: 2;
	top: 350px;
}

.bullets label {
	display: inline-block;
	border-radius: 50%;
	background-color: rgba(0, 0, 0, 0.55);
	width: 20px;
	height: 20px;
	cursor: pointer;
}

.slider input[type=radio]:nth-child(1):checked ~.bullets>label:nth-child(1)
	{
	background-color: #d3dae3;
}

.slider input[type=radio]:nth-child(2):checked ~.bullets>label:nth-child(2)
	{
	background-color: #d3dae3;
}

.slider input[type=radio]:nth-child(3):checked ~.bullets>label:nth-child(3)
	{
	background-color: #d3dae3;
}

.slider input[type=radio]:nth-child(4):checked ~.bullets>label:nth-child(4)
	{
	background-color: #d3dae3;
}

.slider input[type=radio]:nth-child(5):checked ~.bullets>label:nth-child(5)
	{
	background-color: #d3dae3;
}

.slider input[type=radio]:nth-child(1):checked ~ul.imgs>li:nth-child(1)
	{
	opacity: 1;
	transition: 0.1s;
	z-index: 1;
}

.slider input[type=radio]:nth-child(2):checked ~ul.imgs>li:nth-child(2)
	{
	opacity: 1;
	transition: 0.1s;
	z-index: 1;
}

.slider input[type=radio]:nth-child(3):checked ~ul.imgs>li:nth-child(3)
	{
	opacity: 1;
	transition: 0.1s;
	z-index: 1;
}

.slider input[type=radio]:nth-child(4):checked ~ul.imgs>li:nth-child(4)
	{
	opacity: 1;
	transition: 0.1s;
	z-index: 1;
}

.slider input[type=radio]:nth-child(5):checked ~ul.imgs>li:nth-child(5)
	{
	opacity: 1;
	transition: 0.1s;
	z-index: 1;
}

ul.newImgs {
	padding: 0;
	margin: 0;
	position: absolute;
}

ul.newImgs li {
	position: relative;
	top: 100px;
	width: 200px;
	padding: 0;
	margin: 0;
	list-style: none;
}

li {
	text-align: center;
}

main img {
	width: 100px;
}
</style>
</head>
<body>
	<div id="wrap">
		<div>
			<header>
				<c:import url="/WEB-INF/views/component/headerInner.jsp" />
			</header>
		</div>
		<div>
			<main>
				<div id="bestList">
					<div id="title">
						<h1
							style="text-align: center; margin-bottom: -80px; margin-top: 120px;">Best</h1>
					</div>
					<div class="slider">
						<input type="radio" name="slide" id="slide1" checked> <input
							type="radio" name="slide" id="slide2"> <input
							type="radio" name="slide" id="slide3"> <input
							type="radio" name="slide" id="slide4"> <input
							type="radio" name="slide" id="slide5">

						<ul id="imgholder" class="imgs" style="float: left">
							<li><img src="${bookStorageViewDTOList[0].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[0].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[0].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[0].author }</p></li>

							<li><img src="${bookStorageViewDTOList[1].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[1].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[1].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[1].author }</p></li>

							<li><img src="${bookStorageViewDTOList[2].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[2].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[2].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[2].author }</p></li>

							<li><img src="${bookStorageViewDTOList[3].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[3].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[3].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[3].author }</p></li>

							<li><img src="${bookStorageViewDTOList[4].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[4].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[4].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[4].author }</p></li>
						</ul>

						<div class="bullets">
							<label for="slide1">&nbsp;</label> <label for="slide2">&nbsp;</label>
							<label for="slide3">&nbsp;</label> <label for="slide4">&nbsp;&nbsp;</label>
							<label for="slide5">&nbsp;</label>
						</div>
					</div>
				</div>
				<div id="newList" style="width: 1000px;">
					<div id="title">
						<h1
							style="text-align: center; margin-bottom: -60px; margin-top: 90px;">New</h1>
					</div>
					<div id="newImgList">
						<ul id="imgholder" class="newImgs" style="float: left">
							<li><img src="${ebookList[0].title_url }"><br> <c:if
									test="${ebookList[0].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[0].title }&nbsp;
								<p>|&nbsp;${ebookList[0].author }</p></li>

							<li><img src="${ebookList[1].title_url }"><br> <c:if
									test="${ebookList[1].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[1].title }&nbsp;
								<p>|&nbsp;${ebookList[1].author }</p></li>

							<li><img src="${ebookList[2].title_url }"><br> <c:if
									test="${ebookList[2].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[2].title }&nbsp;
								<p>|&nbsp;${ebookList[2].author }</p></li>

							<li><img src="${ebookList[3].title_url }"><br> <c:if
									test="${ebookList[3].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[3].title }&nbsp;
								<p>|&nbsp;${ebookList[3].author }</p></li>

							<li><img src="${ebookList[4].title_url }"><br> <c:if
									test="${ebookList[4].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[4].title }&nbsp;
								<p>|&nbsp;${ebookList[4].author }</p></li>
						</ul>
					</div>
				</div>
			</main>
		</div>
		<div style="position: relative; bottom: 0;">
			<footer>이용약관</footer>
		</div>
	</div>
</body>
</html>