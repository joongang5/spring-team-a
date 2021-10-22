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
<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
<script type="text/javascript" src="./resources/js/calendar.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".slideDiv").not(".active").hide();
		setInterval(nextSlide, 3200); //슬라이드 넘어가는 속도 : 3.2초
	});

	function prevSlide() {
		$(".slideDiv").hide();
		var allSlide = $(".slideDiv");
		var currentIndex = 0;

		$(".slideDiv").each(function(index, item) {
			if ($(this).hasClass("active")) {
				currentIndex = index;
			}
		});

		var newIndex = 0;

		if (currentIndex <= 0) {
			newIndex = allSlide.length - 1;

		} else {
			newIndex = currentIndex - 1;
		} //무한 루프 구간

		$(".slideDiv").removeClass("active");
		$(".slideDiv").eq(newIndex).addClass("active");
		$(".slideDiv").eq(newIndex).show();
	}

	function nextSlide() {
		$(".slideDiv").hide();
		var allSlide = $(".slideDiv");
		var currentIndex = 0;

		$(".slideDiv").each(function(index, item) {
			if ($(this).hasClass("active")) {
				currentIndex = index;
			}
		});

		var newIndex = 0;

		if (currentIndex >= allSlide.length - 1) {
			newIndex = 0;

		} else {
			newIndex = currentIndex + 1;
		}

		$(".slideDiv").removeClass("active");
		$(".slideDiv").eq(newIndex).addClass("active");
		$(".slideDiv").eq(newIndex).show();
	}
</script>
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
				<div class="slideshow-container">
					<div class="slideImg">
						<div class="slideDiv fade active">
							<img src="./resources/img/request.jpg">
						</div>
						<div class="slideDiv fade">
							<img src="./resources/img/report.jpg">
						</div>
						<div class="slideDiv fade">
							<img src="./resources/img/survey.jpg">
						</div>
						<div class="slideDiv fade">
							<img src="./resources/img/notice.jpg">
						</div>
					</div>
					<span></span>
				</div>

				<div style="position: relative; left: 800px; top: -300px;">
					<img src="./resources/img/calender.png"> <br>추가 예정...
				</div>

				<div id="bestList">
					<div id="title">
						<h1
							style="text-align: center; margin-bottom: -70px; margin-top: -90px;">Best</h1>
					</div>
					<div class="slider">
						<input type="radio" name="slide" id="slide1" checked> <input
							type="radio" name="slide" id="slide2"> <input
							type="radio" name="slide" id="slide3"> <input
							type="radio" name="slide" id="slide4"> <input
							type="radio" name="slide" id="slide5"> <span
							style="float: left; position: relative; top: 140px;"><img
							src="/cyber/resources/img/left.png" height="70px;" width="10px;"></span><span
							style="float: right; position: relative; top: 140px;"><img
							src="/cyber/resources/img/right.png" height="70px;" width="10px;"></span>

						<ul id="imgholder" class="imgs" style="float: left">
							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[0].isbn }'" style="cursor:pointer;">
							<img src="${bookStorageViewDTOList[0].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[0].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[0].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[0].author }</p></li>

							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[1].isbn }'" style="cursor:pointer;">
							<img src="${bookStorageViewDTOList[1].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[1].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[1].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[1].author }</p></li>

							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[2].isbn }'" style="cursor:pointer;">
							<img src="${bookStorageViewDTOList[2].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[2].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[2].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[2].author }</p></li>

							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[3].isbn }'" style="cursor:pointer;">
							<img src="${bookStorageViewDTOList[3].title_url }"
								style="width: 100px"><br> <c:if
									test="${bookStorageViewDTOList[3].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${bookStorageViewDTOList[3].title }&nbsp;
								<p>|&nbsp;${bookStorageViewDTOList[3].author }</p></li>

							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[4].isbn }'" style="cursor:pointer;">
							<img src="${bookStorageViewDTOList[4].title_url }"
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
				<div id="newList">
					<div id="title">
						<h1
							style="text-align: center; margin-bottom: -60px; margin-top: 30px;">New</h1>
					</div>
					<div id="newImgList">
						<ul id="imgholder" class="newImgs" style="float: left">
							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[0].isbn }'" style="cursor:pointer;">
							<img src="${ebookList[0].title_url }"><br> <c:if
									test="${ebookList[0].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[0].title }&nbsp;
								<p>|&nbsp;${ebookList[0].author }</p></li>
							
							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[1].isbn }'" style="cursor:pointer;">
							<img src="${ebookList[1].title_url }"><br> <c:if
									test="${ebookList[1].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[1].title }&nbsp;
								<p>|&nbsp;${ebookList[1].author }</p></li>

							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[2].isbn }'" style="cursor:pointer;">
							<img src="${ebookList[2].title_url }"><br> <c:if
									test="${ebookList[2].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[2].title }&nbsp;
								<p>|&nbsp;${ebookList[2].author }</p></li>

							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[3].isbn }'" style="cursor:pointer;">
							<img src="${ebookList[3].title_url }"><br> <c:if
									test="${ebookList[3].title_url eq '' }">
									<img src="/cyber/resources/img/thumbnail.gif">
									<br>
								</c:if> ${ebookList[3].title }&nbsp;
								<p>|&nbsp;${ebookList[3].author }</p></li>

							<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[4].isbn }'" style="cursor:pointer;">
							<img src="${ebookList[4].title_url }"><br> <c:if
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
			<footer>
				<c:import url="/WEB-INF/views/component/footer.jsp" />
			</footer>
		</div>
	</div>
</body>
</html>