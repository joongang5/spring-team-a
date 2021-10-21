<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
#lnbNav {
	text-decoration: none;
	background-color: #e3f0ff;
	width: 200px;
}

#lnb {
	list-style: none;
	margin: 0 auto;
	padding: 0 auto;
	background: #e3f0ff;
	text-decoration: none;
}

#lnbWrap {
	text-decoration: none;
	background-color: white;
}

#lnbTitle {
	text-decoration: none;
	background-color: #e3f0ff;
	margin: 0;
}

a {
	text-decoration: none;
	color: black;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
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
		if (pathname.indexOf("/cyber/member/") != -1) {
			return getMemberNaviMap();
		} else if (pathname.indexOf("/cyber/myPage/") != -1) {
			return getMyPageNaviMap();
		}
		return getEbookNaviMap();
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
			"/cyber/ebook/ebookChild.do" : "&nbsp;어린이 전자책",
			"/cyber/ebook/ebookSearchDetail.do" : "&nbsp;전자책 상세검색",
			"/cyber/bbs/listNotice.do" : "&nbsp;공지사항"
		};
		return map;
	}

	function getMyPageNaviMap() {
		var map = {
			"/cyber/myPage/myPage.do" : "&nbsp;서비스 이용현황",
			"/cyber/myPage/ebookLoanList.do" : "&nbsp;전자책",
			"/cyber/myPage/myBbsPostList.do" : "&nbsp;문의사항",
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
		<ul id="lnb" style="font-size: 18px; text-align: left;">
		</ul>
	</div>
</div>