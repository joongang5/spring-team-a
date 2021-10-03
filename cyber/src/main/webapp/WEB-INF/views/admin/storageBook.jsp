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
	<script>
		function updateStoredBook(no) {
			var maxCount = document.getElementById('maxCount' + no).value;
			var loanCount = document.getElementById('loanCount' + no).value;
			var reserveCount = document.getElementById('reserveCount' + no).value;
			location.href = '/cyber/admin/storage/updateBook.do?bookNo=' + no + '&maxCount=' + maxCount + "&loanCount=" + loanCount + "&reserveCount=" + reserveCount;
		}
	</script>
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
							<input type="submit" value="검색" formaction="/cyber/admin/storage/getBook.do">
						</td>
					</tr>
				</table>
				<input type="submit" value="미등록 데이터 검색" formaction="/cyber/admin/storage/unregisteredBook.do">
			</form>
			<hr>	
		
			<h3>검색 결과</h3>
			<table>
				<tr>
					<th>저장번호</th>
					<th>책번호</th>
					<td>표제</td>
					<td>권차</td>
					<td>저자</td>
					<td>ISBN</td>
					<td>보유</td>
					<td>대출</td>
					<td>예약</td>
					<td></td>
				</tr>
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
			</table>
		</main>
	</div>
</body>
</html>