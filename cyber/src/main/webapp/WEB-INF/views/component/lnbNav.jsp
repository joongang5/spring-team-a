<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
#lnbNav {
	text-decoration: none;
	background-color: white;
	background: white;
	width: 200px;
	margin-top: 15px;
}

#lnb {
	list-style: none;
	margin: 0 auto;
	padding: 0 auto;
	background: white;
	text-decoration: none;
	font-size: 18px;
	text-align: left;
	vertical-align: bottom;
}

#lnbWrap {
	text-decoration: none;
	background-color: white;
	position: relative;
	left: 25px;
}

#lnbTitle {
	text-decoration: none;
	background-color: #e3f0ff;
	margin: 0;
	margin-left: 20px;
	width: 200px;
	padding-bottom: 1px;
	padding-top: 1px;
	text-align: center;
	border-radius: 20px;
}

a {
	height: 40px;
	text-decoration: none;
	color: black;
	padding-top: 9px;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

#lnbWrap li a {
	display: block;
	border-left: 5px solid;
	position: relative;
	z-index: 2;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	border-left: 10px solid #ffe4f3;
}

#lnbWrap li a:hover {
	width: 150px;
	border-bottom: 0px;
	color: white;
	background: #ffe4f3;
	transition: 0.5s;
}
</style>
<script>
	$(document).ready(function() {
		drawLnb();
	});

	function drawLnb() {
		var html = "";
		var map = getNaviMap();
		for ( var key in map) {
			html += "<p><a href='" + key + "'>" + map[key] + "</a></p>";
		}
		$("#lnb").append(html);
	}

	function getNaviMap() {
		var pathname = window.location.pathname;
		if (pathname.indexOf("/cyber/ebook/") != -1) {
			return getEbookNaviMap();
		} else if (pathname.indexOf("/cyber/myPage/") != -1) {
			return getMyPageNaviMap();
		}
		return getMemberNaviMap();
	}

	function getMemberNaviMap() {
		var map = {
			"/cyber/member/memberLogin.do" : "&nbsp;로그인",
			"/cyber/member/memberJoinRegist.do" : "&nbsp;회원가입",
			"/cyber/member/memberFindId.do" : "&nbsp;아이디 찾기",
			"/cyber/member/memberFindPw.do" : "&nbsp;비밀번호 찾기"
		};
		return map;
	}

	function getEbookNaviMap() {
		var map = {
			"/cyber/ebook/ebookMain.do" : "&nbsp;일반 전자책",
		};
		return map;
	}

	function getMyPageNaviMap() {
		var map = {
			"/cyber/myPage/myPage.do" : "&nbsp;서비스 이용현황",
			"/cyber/myPage/ebookLoanList.do" : "&nbsp;전자책",
			"/cyber/myPage/memberModify.do" : "&nbsp;회원정보 수정"
		};
		return map;
	}
</script>
<div id="lnbNav">
	<div id="lnbTitle">
		<h2>&nbsp;Menu</h2>
	</div>
	<div id="lnbWrap">
		<ul>
			<li id="lnb"></li>
		</ul>
	</div>
</div>