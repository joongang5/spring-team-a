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
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/admin.css?1"/>">
</head>
<body>
	<div id="wrap">
		<header>
			<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
		</header>
		<main>관리자 홈</main>
	</div>
</body>
</html>