<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">
	#headerInner { height: 100%; }
 	#headerInner ul { list-style: none; margin: 0; padding: 0; height: 29px; }
	#headerInner li { float: left; }
	#siteMenu { height: 30px; background-color: #0067A3; }
	#logo { margin: 0; padding: 20px; float: left; display: inline; }
	#gnbNav { float: left; }
	#siteMenu .join::before { content: ' | '; }
	#headerInner a { color: white; }
	#headerInner a:link { text-decoration: none; }
	#headerInner a:visited { text-decoration: none; color: white; }
	#gnbNav a:hover { color: #0067A3; }
</style>

<div id="headerInner">
	<div id="siteMenu">
		<ul>
			<li class="login">
				<a href="memberLogin.do">로그인</a>
			</li>
			<li class="join">
				<a href="memberJoinRegist.do">회원가입</a>
			</li>
		</ul>
	</div>
	<h1 id="logo">
		<a href="index.do">사이버도서관</a>
	</h1>
	<nav id="gnbNav">
		<ul>
			<li>
				<a href="ebookMain.do">전자책</a>
			</li>
			<li>
				<a href="ebookMain.do">소통마당</a>
			</li>
			<li>
				<a href="myPage.do">나만의도서관</a>
			</li>
			<li>
				<a href="listNotice.do">공지사항</a>
			</li>
		</ul>
	</nav>
</div>