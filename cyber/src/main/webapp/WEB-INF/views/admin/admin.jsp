<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/admin.css"/>">
</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
	</header>
	<article>
		<h2>반응형 사이트 서브 타이틀</h2>
	</article>
	<main>
		<ul>
			<li>최근 대출 목록</li>
			<li>최근 가입 회원</li>
		</ul>
	</main>
</body>
</html>