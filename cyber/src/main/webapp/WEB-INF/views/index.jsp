<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<link rel="stylesheet" type="text/css" href="./resources/css/index.css">
</head>
<body>
	<div id="wrap">
		<header>
			<c:import url="/WEB-INF/views/component/headerInner.jsp" />
		</header>
		<main>
			<div id="newList">
				<h2>이 달의 신작</h2>
			</div>
			<div id="bestList">
				<h2>많이 본 책</h2>
			</div>
		</main>
		<footer>이용약관</footer>
	</div>
</body>
</html>