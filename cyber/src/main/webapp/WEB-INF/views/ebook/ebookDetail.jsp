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

#review fieldset {
	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
	direction: rtl; /* 이모지 순서 반전 */
	border: 0; /* 필드셋 테두리 제거 */

}

#review fieldset legend {
	text-align: left;
}

#review input[type=radio] {
	display: none; /* 라디오박스 감춤 */
}

#review label {
	font-size: 1.5em; /* 이모지 크기 */
	color: transparent; /* 기존 이모지 컬러 제거 */
	text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */

}

#review label:hover {
	text-shadow: 0 0 0 #ffed00; /* 마우스 호버 */
}

#review label:hover ~ label {
	text-shadow: 0 0 0 #ffed00; /* 마우스 호버 뒤에오는 이모지들 */
}

#review input[type=radio]:checked ~ label {

	text-shadow: 0 0 0 #ffed00; /* 마우스 클릭 체크 */
}
#reviewArea{width:100%;margin:0 auto;overflow:hidden;}
					#reviewArea div{float:left;}
						#reviewText{width:90%;text-align:left;}
							#reviewText textarea{width:97%;height:75px;overflow:auto;resize: none; margin-left: 8px;}
							#reviewBtn{width:100%;height:80px;line-height:80px;cursor:pointer;}
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
	function content3() {
		document.getElementById("content1").style.display = "none";
		document.getElementById("content2").style.display = "none";
	}
	function checkReview() {
	    var rating = document.review.rating;
	    if(rating.value == '' ) {
	        alert("별점을 클릭하세요.");
	       	return false;
	    }
	    var review = document.getElementById('reviewCmt');
	    if(document.review.reviewCmt.value == ''){
	        alert('내용을 입력하세요.');
	        return false;
	    }
	}

	function onclickLoanBtn(bookNo) {
		var result = confirm("대출한 전자책은 대출일로부터 5일 후 자정까지 가능하합니다.\n계속하시겠습니까?");
		if (result == false)
			return;

		$.ajax({
			url : "loanAJAX.do",
			type : "POST",
			dataType : "json",
			data : { "bookNo" : bookNo },
			success : function(data) {
				if (data.errorMessage != "") {
					alert(data.errorMessage);
					return;
				}

				$("#bookStorage").text("도서현황 : 대출(" +
						data.bookStorageDTO.loan_count + "/" + data.bookStorageDTO.max_count + "), 예약(" +
						data.bookStorageDTO.reserve_count + "/" + data.bookStorageDTO.max_count + ")");

				alert("대출에 성공했습니다.");
			},
			error : function(request, status, error) {
				alert("error : " + error);
			}
		});
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
				별점 : <img alt="star" src="/cyber/resources/img/star${ebookReview.get(0).ratingSum }.png"> 리뷰(<c:if test="${ebookReview.get(0).reviewCount eq null }">0</c:if>${ebookReview.get(0).reviewCount })<br>
				페이지 : ${ebookDetail.page}<br> 책 크기 : ${ebookDetail.book_size }<br>
				<div id="bookStorage">
					도서현황 : 대출(${bookStorageDTO.loan_count }/${bookStorageDTO.max_count }), 예약(${bookStorageDTO.reserve_count }/${bookStorageDTO.max_count })
				</div>

				<div class="btnGroup">
					<button class="btn list" id="listBtn" onclick="location.href='/cyber/ebook/ebookMain.do';">목록</button>
					<button class="btn themeBtn" onclick="onclickLoanBtn(${ebookDetail.no})">대여</button>
					<button class="btn themeBtn2" onclick="">관심목록 담기</button>
				</div>
				<input type="button" onclick="content1()" value="책소개" /> <input
					type="button" onclick="content2()" value="목차" /> <input
					type="button" onclick="content3()" value="별점/리뷰" />
				<div id="content1" class="content2" style="">
					<hr>
					<b>책소개</b><br> ${detail.detail0 }<br>
					<hr>
					<b>저자</b><br> ${detail.detail1 }<br>
					<hr>
				</div>
				<div id="content2" class="content2" style="display: none;">
					<hr>
					<b>목차</b><br> ${detail.detail2 }
				</div>
				<div id="reviewArea">
				<form name="review" id="review" method="post" action="./ebookReview.do" onsubmit="return checkReview()">

					<fieldset>
						<legend>별점/리뷰</legend>
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
					<div id="reviewText">
					<textarea id="reviewCmt" name="reviewCmt"<c:if test="${sessionScope.id eq null }">placeholder="  로그인 후 작성 가능합니다."</c:if>></textarea>
					<input type="hidden" name="book_no" value="${ebookDetail.no }">
					<input type="hidden" name="isbn" value="${ebookDetail.isbn }">
					</div>
					<div id="reviewSend">
					<input type="submit" id="reviewBtn" value = "댓글 작성"></input>
					</div>
				</form>
				</div>
				<div id = reviewList>
					<hr>
					<c:forEach items="${ebookReview }" var="review" >
					<img alt="star" src="/cyber/resources/img/star${review.rating }.png">
					${review.reviewCmt }
					${review.date }
					${review.id }
					${review.name }
					<hr>
					</c:forEach>
				</div>

			</div>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>
