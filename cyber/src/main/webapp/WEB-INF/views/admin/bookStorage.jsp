<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/admin/bookStorage.js"/>"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/base.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/bookStorage.css"/>">
</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
	</header>
	<main>
		<div class="container">
			<div class="row">
				<form action="getBook.do" method="post">
					<table class="table_search">
						<tr>
							<th>검색어</th>
							<td>
								<select id="searchType" name="searchType">
									<option value="title" <c:if test="${searchType eq 'title'}">selected="selected"</c:if>>title</option>
									<option value="book_no" <c:if test="${searchType eq 'book_no'}">selected="selected"</c:if>>book_no</option>
								</select>
								<input type="text" name="searchValue" value="${searchValue }">
							</td>
						</tr>
					</table>
					<div class="search_btns">
						<button type="submit">검색</button>
						<button type="reset">초기화</button>
					</div>
				</form>
				
				<div class="util_left_btns">
					<button type="button" onclick="onclickSearchUnregisteredBtn()">미등록 데이터 검색</button>
					<button type="button" onclick="onclickRegistUnregisteredBtn()">미등록 데이터 일괄 등록</button>
				</div>
				<div class="util_right_btns">
					<button type="button" onclick="onclickAutoLoanBtn()">자동 대출 실행</button>
					<button type="button" onclick="onclickAutoReturnBtn()">자동 반납 실행</button>
				</div>
				<hr>	
			
				<table class="table_list">
					<thead>
						<tr>
							<th style="width: 5%">book_no</th>
							<th style="width: 45%">표제</th>
							<th style="width: 20%">저자</th>
							<th style="width: 10%">ISBN</th>
							<th style="width: 5%">보유</th>
							<th style="width: 5%">대출</th>
							<th style="width: 5%">예약</th>
							<th style="width: 5%">-</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${bookStorageViewDTOList }" var="l">
						<tr>
							<td>${l.book_no}</td>
							<td>${l.title}</td>
							<td>${l.author}</td>
							<td>${l.isbn}</td>
							<td>
								<input type="text" id="maxCount${l.book_no }" value="${l.max_count }">
							</td>
							<td>
								<input type="text" id="loanCount${l.book_no }" value="${l.loan_count }">
							</td>
							<td>
								<input type="text" id="reserveCount${l.book_no }" value="${l.reserve_count }">
							</td>
							<td>
								<input type="button" value="수정" onclick="onclickUpdateBtn(${l.book_no })">
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="pagination">
					<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="linkPageAJAX"/>
				</div>
			</div>
		</div>
	</main>
</body>
</html>