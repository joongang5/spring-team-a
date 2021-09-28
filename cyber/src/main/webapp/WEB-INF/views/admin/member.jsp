<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자</title>
	<style>
		body { margin: 0; padding: 0; }
		ul { margin: 0; }
		table { border: 1px solid black; }
		th, td { border: 1px solid black; }
	</style>
</head>
<body>
	<div id="wrap">
		<header>
			<ul>
				<li>
					<a href="/cyber/index.do">홈으로</a>
				</li>
				<li>
					<a href="/cyber/admin/book.do">도서</a>
				</li>
				<li>
					<a href="/cyber/admin/member.do">회원</a>
				</li>
			</ul>
		</header>
		<main>
			<h3>회원 목록</h3>
			<table>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>이메일</th>
					<th>가입일</th>
					<th>등급</th>
				</tr>
				<c:forEach items="${list }" var="l">
					<tr>
						<td>${l.no }</td>
						<td>${l.name }</td>
						<td>${l.id }</td>
						<td>${l.email }</td>
						<td>${l.joinDate }</td>
						<td>${l.grade }</td>
					</tr>
				</c:forEach>
			</table>
		</main>
	</div>
</body>
</html>