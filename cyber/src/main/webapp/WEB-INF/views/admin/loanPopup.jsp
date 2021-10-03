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
</head>
<body>
	<div id="wrap">
		<h3>도서검색</h3>
		<form method="post">
			<table>
				<tr>
					<th>bookNo</th>
					<td>
						<input type="text" name="bookNo">
					</td>
					<td>
						<input type="submit" value="검색" formaction="/cyber/admin/loan/getStoredBook.do">
					</td>
				</tr>
			</table>
			<input type="hidden" name="memberNo" value="${memberNo }">
		</form>
		
		<form method="post">	
			<table>
				<tr>
					<th>번호</th>
					<td>표제</td>
					<td>권차</td>
					<td>저자</td>
					<td>ISBN</td>
					<td>보유</td>
					<td>대출</td>
					<td>예약</td>
					<td></td>
				</tr>
				<tr>
					<td>${bookStorageDTO.book_no}</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>${bookStorageDTO.max_count }</td>
					<td>${bookStorageDTO.loan_count }</td>
					<td>${bookStorageDTO.reserve_count }</td>
					<td>
						<button formaction="/cyber/admin/loan/loan.do">대출실행</button>
					</td>
				</tr>
			</table>
			<input type="hidden" name="memberNo" value="${memberNo }">
			<input type="hidden" name="bookNo" value="${bookStorageDTO.book_no }">
		</form>
		
		<h3>대출목록</h3>	
		<table>
			<tr>
				<th>번호</th>
				<th>표제</th>
				<th>저자</th>
				<th>권차</th>
				<th>반납일</th>
			</tr>
			<c:forEach items="${loanViewDTOList }" var="l">
			<tr>
				<td>${l.no }</td>
				<td>${l.title }</td>
				<td>${l.author }</td>
				<td>${l.vol }</td>
				<td>${l.return_date }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>