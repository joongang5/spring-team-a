<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나만의도서관</title>
	<style>
		body { margin: 0; padding: 0; }
		#wrap { width: 1000px; margin: 0 auto; }
		header { width: 1000px; height: 130px; background: #111; }
		aside { float: left; width: 240px; height: 600px; background: #333; }
		main { float: left; width: 760px; height: 600px; background: #444; }
		footer	{ clear: both; width: 1000px; height: 100px; background: #555; }
		.libtn {
			background-color: #0066b3;
			font-family: sans-serif;
			font: bold;
			color: white;
		}
		.infoBox {
			margin: 0 auto;
			padding: 0;
			background-color: #f2f2f2;
			font-family: sans-serif;
		}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
			<div>
				<c:choose>
					<c:when test="${sessionScope.id ne null}">
						<a class="libtn" href="myPage.do">내서재</a>
						<a class="libtn" href="">나의별점/리뷰</a>
						<a class="libtn" href="">관심목록</a>
						<div class="infoBox">
							- 현재 대출중인 전자책을 확인하고, 전자책을 보거나 반납할 수 있습니다.<br>
							- 대출한 책은 반납예정일이 지나면 자동 반납되며, [반납하기]를 눌러서 미리 반납도 가능합니다.
						</div><hr>
						<table>
							<tr>
								<th>사진</th>
								<th>제목</th>
								<th>저자</th>
								<th>출판사</th>
								<th>대출일</th>
								<th>반납예정일</th>
							</tr>
						</table>
						<table>
						<c:choose>
							<c:when test="${fn:length(loanViewDTOList) gt 0 }">
								<c:forEach items="${loanViewDTOList }" var="l" >
								<tr>
									<td><img alt="book" src="${l.title_url }" style="width: 100px; height: 120px;"></td>
									<td>${l.title }</td>
									<td>${l.author }</td>
									<td>${l.publisher }</td>
									<td>${l.loan_date }</td>
									<td>${l.return_date }</td>
								</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								대출중인 전자책이 없습니다.
							</c:otherwise>
						</c:choose>
						</table>
					</c:when>
					<c:otherwise>
						<a href="/cyber/member/memberLogin.do">로그인 해주세요.</a>
					</c:otherwise>
				</c:choose>
			</div>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>