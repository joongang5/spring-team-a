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
			<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
		</header>
		<main>
			<form name="bookInfo" method="post">
				<h3>도서 재고 관리</h3>
				<table>
					<tr>
						<th>
							<select name="searchType">
								<option value="Title" <c:if test="${searchType eq 'Title' }">selected="selected"</c:if>>제목</option>
								<option value="ISBN" <c:if test="${searchType eq 'ISBN' }">selected="selected"</c:if>>ISBN</option>
							</select>
						</th>
						<td>
							<input type="text" name="searchValue" value="${infoMap.searchValue }">
						</td>
					</tr>
				</table>
				<input type="submit" value="검색" formaction="/cyber/admin/searchBook.do">
				<input type="submit" value="초기화">
			</form>
			<hr>
			
			<h3>검색 결과</h3>
			<table>
				<tr>
					<th>번호</th>
					<td>표제</td>
					<td>권차</td>
					<td>총서명</td>
					<td>저자</td>
					<td>ISBN</td>
					<td>보유</td>
					<td>대출</td>
					<td>예약</td>
				</tr>
				<c:forEach items="${list }" var="l">
					<tr>
						<td>${l.no}</td>
						<td>${l.title}</td>
						<td>${l.vol}</td>
						<td>${l.series_title}</td>
						<td>${l.author}</td>
						<td>${l.ea_isbn}</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
					</tr>
				</c:forEach>
			</table>
		</main>
	</div>
</body>
</html>