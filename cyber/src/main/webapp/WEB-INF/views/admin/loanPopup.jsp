<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>대출현황</title>
	<style>
		body { margin: 0; padding: 0; }
		ul { margin: 0; }
		table { border: 1px solid black; }
		th, td { border: 1px solid black; }
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src='<c:url value="/resources/js/admin/loanPopup.js"/>'></script>
</head>
<body>
	<div id="wrap">
		<input type="hidden" name="memberNo" value="${memberNo }">
		
		<h3>도서검색</h3>
		<table>
			<tr>
				<th>bookNo</th>
				<td>
					<input type="text" name="bookNo">
				</td>
				<td>
					<button type="button" onclick="onclickSearchBtn()">검색</button>
				</td>
			</tr>
		</table>
		<hr>
		<table>
			<tr>
				<th>번호</th>
				<td>표제</td>
				<td>저자</td>
				<td>ISBN</td>
				<td>보유</td>
				<td>대출</td>
				<td>예약</td>
			</tr>
			<tbody id="searchTbody">
				<tr>
					<td>${bookStorageViewDTO.book_no }</td>
					<td>${bookStorageViewDTO.title }</td>
					<td>${bookStorageViewDTO.author }</td>
					<td>${bookStorageViewDTO.isbn }</td>
					<td>${bookStorageViewDTO.max_count }</td>
					<td>${bookStorageViewDTO.loan_count }</td>
					<td>${bookStorageViewDTO.reserve_count }</td>
				</tr>
			</tbody>
		</table>
		<button type="button" onclick="onclickLoanBtn()">대출실행</button>
		<button type="button" onclick="onclickReserveBtn()">예약실행</button>
		
		<h3>대출목록</h3>	
		<table>
			<tr>
				<th>번호</th>
				<th>표제</th>
				<th>저자</th>
				<th>반납일</th>
				<th></th>
			</tr>
			<tbody id="loanTbody">
				<c:forEach items="${loanViewDTOList }" var="l">
				<tr>
					<td>${l.book_no }</td>
					<td>${l.title }</td>
					<td>${l.author }</td>
					<td>${l.return_date }</td>
					<td>
						<c:if test="${l.state == 0 }">
							<button type="button" onclick="onclickReturnBtn(${l.book_no})">반납</button>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>