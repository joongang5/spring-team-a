<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="<c:url value="/resources/css/headerInner.css"/>">
<style type="text/css">
#headerInner {
	height: 100%;
}

ul {
	text-decoration: none;
	list-style: none;
	margin: 0;
	padding: 0;
	height: 29px;
}

li {
	float: left;
	list-style: none;
	margin: 1px;
}

#gnbNav li a {
	display: block;
	width: 246px;
	height: 40px;
	background: #c9f3c6;
	color: #black;
	border: 1px solid white;
	font-size: 20px;
	font-weight: bold;
	text-align: center;
	padding-top: 18px;
	text-decoration: none;
}

#gnbNav li a span {
	display: block;
}

#gnbNav li a:hover {
	background: #a3d9a0;
	text-decoration: none;
}

#siteMenu {
	height: 40px;
	background-color: #cee5fe;
}

#logo {
	margin: 0;
	padding: 20px;
	float: left;
	display: inline;
}

#gnbNav {
	float: left;
}

#siteMenu .join::before {
	content: ' | ';
}

#siteMenu .admin::before {
	content: ' | ';
}

#headerInner a {
	color: black;
}

#headerInner a:link {
	text-decoration: none;
}

#headerInner a:visited {
	text-decoration: none;
	font-family: 'NanumSquare', serif;
}

#siteMenu a {
	text-align: right;
}

#gnbNav a:hover {
	color: #black;
}

a {
	color: black;
}

img {
	vertical-align: middle;
}

@media ( max-width : 760px) {
	#wrap div {
		width: 500px;
		float: none;
	}
}
</style>
<div id="headerInner">
	<div id="siteMenu">
		<ul style="position: relative; top: 8px; left: 790px;">
			<li class="login"><c:choose>
					<c:when test="${sessionScope.name ne null}">
						<a href="/cyber/member/logout.do">&nbsp;로그아웃&nbsp;</a>
					</c:when>
					<c:otherwise>
						<a href="/cyber/member/memberLogin.do">&nbsp;로그인&nbsp;</a>
					</c:otherwise>
				</c:choose></li>
			<li class="join"><a href="/cyber/member/memberJoinRegist.do">회원가입&nbsp;</a>
			</li>
			<li class="admin"><a href="/cyber/admin/home.do">관리자</a></li>
		</ul>
	</div>
	<div>
		<h1 id="logo">
			<img alt="임시로고" src="./resources/img/library.png"
				onerror="this.src='../resources/img/library.png'"> <a
				href="/cyber/index.do">&nbsp;A book</a>
		</h1>
	</div>
</div>
<div id="gnbNav">
	<nav>
		<ul>
			<li><a href="/cyber/ebook/ebookMain.do">전자책&nbsp;&nbsp;</a></li>
			<li><a href="/cyber/bbs/listBoard.do">소통마당&nbsp;&nbsp;</a></li>
			<li><a href="/cyber/bbs/listNotice.do">공지사항&nbsp;&nbsp;</a></li>
			<li><a href="/cyber/myPage/myPage.do">내 도서관&nbsp;&nbsp;</a></li>
		</ul>
	</nav>
</div>