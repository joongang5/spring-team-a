<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="<c:url value="/resources/css/admin/component/headerInner.css"/>">

<div id="headerInner">
	<div id="siteMenu">
		<div class="container">
			<div class="row">
				<div class="siteMenu">
					<a href="/cyber/index.do">도서관 홈</a>
					<a href="/cyber/admin/home.do">관리자 홈</a>
				</div>
			</div>
		</div>
	</div>
	<nav id="nav">
		<div class="container">
			<div class="row">
				<div class="nav">
					<h2 class="ir_su">반응형 사이트 전체 메뉴</h2>
					<div>
						<h3>
							<a href="/cyber/admin/ebook/home.do">신규 도서 검색/등록</a>
						</h3>
					</div>
					<div>
						<h3>
							<a href="/cyber/admin/storage/home.do">도서 재고 관리</a>
						</h3>
					</div>
					<div class="last">
						<h3>
							<a href="/cyber/admin/member/home.do">회원 관리</a>
						</h3>
					</div>
				</div>
			</div>
		</div>
	</nav>
</div>