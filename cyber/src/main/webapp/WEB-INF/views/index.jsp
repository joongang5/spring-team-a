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

<link rel="stylesheet" href="<c:url value="/resources/css/base.css?5"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/index.css?5"/>">
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
<script type="text/javascript">
	var today = new Date();
	var date = new Date();

	function prevCalendar() {
		today = new Date(today.getFullYear(), today.getMonth() - 1, today
				.getDate());
		buildCalendar();
	}

	function nextCalendar() {
		today = new Date(today.getFullYear(), today.getMonth() + 1, today
				.getDate());
		buildCalendar();
	}

	function buildCalendar() {
		var doMonth = new Date(today.getFullYear(), today.getMonth(), 1);
		var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
		var tbCalendar = document.getElementById("calendar");
		var tbCalendarYM = document.getElementById("tbCalendarYM");

		tbCalendarYM.innerHTML = today.getFullYear() + "년 "
				+ (today.getMonth() + 1) + "월";

		while (tbCalendar.rows.length > 2) {
			tbCalendar.deleteRow(tbCalendar.rows.length - 1);
		}

		var row = null;
		row = tbCalendar.insertRow();
		var cnt = 0;

		for (i = 0; i < doMonth.getDay(); i++) {
			cell = row.insertCell();
			cnt = cnt + 1;
		}

		for (i = 1; i <= lastDate.getDate(); i++) {
			cell = row.insertCell();
			cell.innerHTML = i;
			cnt = cnt + 1;

			if (cnt % 7 == 1) {
				cell.innerHTML = "<font color=#F79DC2>" + i
			}

			if (cnt % 7 == 0) {
				cell.innerHTML = "<font color=skyblue>" + i
				row = calendar.insertRow();
			}

			if (today.getFullYear() == date.getFullYear()
					&& today.getMonth() == date.getMonth()
					&& i == date.getDate()) {
				cell.bgColor = "#acd1be";
			}
		}
	}
</script>
<script type="text/javascript">
	$(".prev").on("click", function(e) {
		e.preventDefault();
		var imgOn = $(".imgBox").find(".on").index();
		var imgLen = $(".imgBox .img").length;
		console.log(imgOn)

		$(".imgBox .img").eq(imgOn).removeClass("on");
		$(".imgBox .img").eq(imgOn).css("opacity", 0);
		imgOn = imgOn - 1;

		if (imgOn < 0) {
			$(".imgBox .img").eq(imgLen - 1).css("opacity", 1);
			$(".imgBox .img").eq(imgLen - 1).addClass("on");

		} else {
			$(".imgBox .img").eq(imgOn).css("opacity", 1);
			$(".imgBox .img").eq(imgOn).addClass("on");
		}
	});

	$(".next").on("click", function(e) {
		e.preventDefault();
		var imgOn = $(".imgBox").find(".on").index();
		var imgLen = $(".imgBox .img").length;

		$(".imgBox .img").eq(imgOn).removeClass("on");
		$(".imgBox .img").eq(imgOn).css("opacity", 0);

		imgOn = imgOn + 1;

		if (imgOn === imgLen) {
			$(".imgBox .img").eq(0).css("opacity", 1);
			$(".imgBox .img").eq(0).addClass("on");

		} else {
			$(".imgBox .img").eq(imgOn).css("opacity", 1);
			$(".imgBox .img").eq(imgOn).addClass("on");
		}
	});
</script>
</head>
<body>
	<div id="wrap">
		<header>
			<c:import url="/WEB-INF/views/component/headerInner.jsp" />
		</header>
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

			<div style="position: relative; left: 300px; top: -400px;">
				<table id="calendar">
					<tr>
						<td><label onclick="prevCalendar()"> < </label></td>
						<td align="center" id="tbCalendarYM" colspan="5">yyyy년 m월</td>
						<td><label onclick="nextCalendar()">> </label></td>
					</tr>
					<tr>
						<td align="center"><font color="#F79DC2">일</font></td>
						<td align="center">월</td>
						<td align="center">화</td>
						<td align="center">수</td>
						<td align="center">목</td>
						<td align="center">금</td>
						<td align="center"><font color="skyblue">토</font></td>
					</tr>
				</table>
				<script type="text/javascript">
					buildCalendar();//
				</script>
			</div>

			<div id="bestList">
				<div id="title">
					<h1
						style="text-align: center; margin-bottom: -70px; margin-top: -330px;">Best</h1>
				</div>
				<div class="slider">
					<input type="radio" name="slide" id="slide1" checked> <input
						type="radio" name="slide" id="slide2"> <input
						type="radio" name="slide" id="slide3"> <input
						type="radio" name="slide" id="slide4"> <input
						type="radio" name="slide" id="slide5"> <span
						style="float: left; position: relative; top: 160px;"><button
							class="prev" style="background: white; border: none;">
							<img src="/cyber/resources/img/left.png" height="20px;"
								width="20px;">
						</button></span><span style="float: right; position: relative; top: 160px;"><button
							style="background: white; border: none;" class="next">
							<img src="/cyber/resources/img/right.png" height="20px;"
								width="20px;">
						</button></span>

					<ul id="imgholder" class="imgs" style="float: left">
						<li
							onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[0].isbn }'"
							style="cursor: pointer;"><img
							src="${bookStorageViewDTOList[0].title_url }"
							style="width: 100px"><br> <c:if
								test="${bookStorageViewDTOList[0].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif">
								<br>
							</c:if> ${bookStorageViewDTOList[0].title }&nbsp;
							<p>|&nbsp;${bookStorageViewDTOList[0].author }</p></li>

						<li
							onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[1].isbn }'"
							style="cursor: pointer;"><img
							src="${bookStorageViewDTOList[1].title_url }"
							style="width: 100px"><br> <c:if
								test="${bookStorageViewDTOList[1].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif">
								<br>
							</c:if> ${bookStorageViewDTOList[1].title }&nbsp;
							<p>|&nbsp;${bookStorageViewDTOList[1].author }</p></li>

						<li
							onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[2].isbn }'"
							style="cursor: pointer;"><img
							src="${bookStorageViewDTOList[2].title_url }"
							style="width: 100px"><br> <c:if
								test="${bookStorageViewDTOList[2].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif">
								<br>
							</c:if> ${bookStorageViewDTOList[2].title }&nbsp;
							<p>|&nbsp;${bookStorageViewDTOList[2].author }</p></li>

						<li
							onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[3].isbn }'"
							style="cursor: pointer;"><img
							src="${bookStorageViewDTOList[3].title_url }"
							style="width: 100px"><br> <c:if
								test="${bookStorageViewDTOList[3].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif">
								<br>
							</c:if> ${bookStorageViewDTOList[3].title }&nbsp;
							<p>|&nbsp;${bookStorageViewDTOList[3].author }</p></li>

						<li
							onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${bookStorageViewDTOList[4].isbn }'"
							style="cursor: pointer;"><img
							src="${bookStorageViewDTOList[4].title_url }"
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
					<h1 style="text-align: center; margin-bottom: -60px; margin-top: 40px;">New</h1>
				</div>
				<div id="newImgList">
					<ul id="imgholder" class="newImgs">
						<li	onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[0].isbn }'" style="cursor: pointer;">
							<img src="${ebookList[0].title_url }"><br>
							<c:if test="${ebookList[0].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif"><br>
							</c:if> ${ebookList[0].title }&nbsp;
							<p>|&nbsp;${ebookList[0].author }</p>
						</li>
						
						<li onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[1].isbn }'" style="cursor: pointer;">
							<img src="${ebookList[1].title_url }"><br> 
							<c:if test="${ebookList[1].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif"><br>
							</c:if> ${ebookList[1].title }&nbsp;
							<p>|&nbsp;${ebookList[1].author }</p>
						</li>
						
						<li
							onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[2].isbn }'"
							style="cursor: pointer;"><img
							src="${ebookList[2].title_url }"><br> <c:if
								test="${ebookList[2].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif">
								<br>
							</c:if> ${ebookList[2].title }&nbsp;
							<p>|&nbsp;${ebookList[2].author }</p></li>

						<li
							onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[3].isbn }'"
							style="cursor: pointer;"><img
							src="${ebookList[3].title_url }"><br> <c:if
								test="${ebookList[3].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif">
								<br>
							</c:if> ${ebookList[3].title }&nbsp;
							<p>|&nbsp;${ebookList[3].author }</p></li>

						<li
							onclick="location.href='/cyber/ebook/ebookDetail.do?isbn=${ebookList[4].isbn }'"
							style="cursor: pointer;"><img
							src="${ebookList[4].title_url }"><br> <c:if
								test="${ebookList[4].title_url eq '' }">
								<img src="/cyber/resources/img/thumbnail.gif">
								<br>
							</c:if> ${ebookList[4].title }&nbsp;
							<p>|&nbsp;${ebookList[4].author }</p></li>
					</ul>
				</div>
			</div>
		</main>
		<footer>
			<c:import url="/WEB-INF/views/component/footer.jsp?1" />
		</footer>
	</div>
</body>
</html>