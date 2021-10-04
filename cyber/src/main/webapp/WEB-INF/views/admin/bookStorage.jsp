<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
    
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src='<c:url value="/resources/js/admin/bookStorage.js"/>'></script>
</head>
<body>
	<div id="wrap">
		<header>
			<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
		</header>
		<main>
			<form method="post">
				<h3>도서 재고 관리</h3>
				<table>
					<tr>
						<th>book_no</th>
						<td>
							<input type="text" name="bookNo" value="${bookNo }">
						</td>
						<td>
							<input type="button" id="searchBtn" value="검색" onclick="onclickSearchBtn()">
						</td>
					</tr>
				</table>
				<input type="button" id="searchUnregisterdBtn" value="미등록 데이터 검색" onclick="onclickSearchUnregisteredBtn()">
			</form>
			<hr>	
		
			<h3>검색 결과</h3>
			<table>
				<thead>
					<tr>
						<th>저장번호</th>
						<th>책번호</th>
						<th>표제</th>
						<th>권차</th>
						<th>저자</th>
						<th>ISBN</th>
						<th>보유</th>
						<th>대출</th>
						<th>예약</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${bookStorageViewDTOList }" var="l">
					<tr>
						<td>${l.no}</td>
						<td>${l.book_no}</td>
						<td>${l.title}</td>
						<td>${l.vol}</td>
						<td>${l.author}</td>
						<td>${l.ea_isbn}</td>
						<td>
							<input type="text" id="maxCount${l.no }" value="${l.max_count }">
						</td>
						<td>
							<input type="text" id="loanCount${l.no }" value="${l.loan_count }">
						</td>
						<td>
							<input type="text" id="reserveCount${l.no }" value="${l.reserve_count }">
						</td>
						<td>
							<input type="button" value="수정" onclick="updateStoredBook(${l.no })">
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="linkPage"/>
		</main>
	</div>
</body>
</html>