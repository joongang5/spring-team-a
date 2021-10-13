<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="<c:url value="/resources/css/admin/component/headerInner.css"/>">

<div id="headerInner">
	<div class="container">
		<div class="row">
			<div class="header">
				<div class="headerMenu">
					<a href="/cyber/index.do">홈으로(index)</a>
					<a href="/cyber/admin/home.do">관리자 홈</a>
					<a href="/cyber/admin/ebook/home.do">신규 도서 검색/등록</a>
					<a href="/cyber/admin/storage/home.do">도서 재고 관리</a>
					<a href="/cyber/admin/member/home.do">회원 관리</a>
				</div>
			</div>
		</div>
	</div>
</div>