<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#headerInner {
	height: 100%;
}

#headerInner ul {
	list-style: none;
	margin: 0;
	padding: 0;
	height: 29px;
}

#headerInner li {
	float: left;
}

#siteMenu {
	height: 30px;
	background-color: #0067A3;
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
	color: white;
}

#headerInner a:link {
	text-decoration: none;
}

#headerInner a:visited {
	text-decoration: none;
	color: #94b5e0;
	font-family: 'NanumSquare', serif;
}

#gnbNav a:hover {
	color: #0067A3;
}
</style>

<div id="headerInner">
	<div id="siteMenu">
		<ul>
			<li class="login"><c:choose>
					<c:when test="${sessionScope.name ne null}">
						<a href="/cyber/member/logout.do">로그아웃&nbsp;</a>
					</c:when>
					<c:otherwise>
						<a href="/cyber/member/memberLogin.do">로그인&nbsp;</a>
					</c:otherwise>
				</c:choose></li>
			<li class="join"><a href="/cyber/member/memberJoinRegist.do">회원가입&nbsp;</a>
			</li>
			<li class="admin"><a href="/cyber/admin/home.do">관리자</a></li>
		</ul>
	</div>
	<h1 id="logo">
		<a href="/cyber/index.do">사이버 도서관</a>
	</h1>
	<nav id="gnbNav">
		<ul>
			<li><a href="/cyber/ebook/ebookMain.do">전자책&nbsp;&nbsp;</a></li>
			<li><a href="/cyber/ebook/ebookMain.do">소통마당&nbsp;&nbsp;</a></li>
			<li><a href="/cyber/bbs/listNotice.do">공지사항&nbsp;&nbsp;</a></li>
			<li><a href="/cyber/myPage/myPage.do">내 도서관&nbsp;&nbsp;</a></li>
		</ul>
	</nav>
</div>