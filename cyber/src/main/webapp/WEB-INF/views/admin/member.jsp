<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<style>
* {
	font-family: sans-serif;
	font-size: 12px;
	letter-spacing: 0;
}

body {
	margin: 0;
	padding: 0;
}

ul {
	margin: 0;
}

table {
	margin: 0 auto;
	border: 1px solid #ccc;
}

th, td {
	border: 1px solid #ccc;
}

.table_search {
	border: 1px solid #ccc;
	width: 95%;
	border-collapse: collapse;
	line-height: 2.5;
	border-top: 1px solid #828282;
	table-layout: fixed;
}

.table_search > tbody > tr > th {
	width: 150px;
	text-align: left;
	padding: 7px 10px;
	background: #f1f4fb;
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
	border-left: 1px solid #ccc;
	font-weight: 400;
}

.table_search > tbody > tr > td {
	padding: 7px 10px;
}

.table_search > tbody > tr > td > select, input {
	border: 1px solid #ccc;
}

.table_basic {
	width: 95%;
	border-collapse: collapse;
	line-height: 1.5;
	border-bottom: 1px solid #d7d6db;
	border-right: 1px solid #d7d6db;
	color: #000;
	border-top: 1px solid #828282;
}

.table_basic > thead > tr > th {
	background: #f1f4fb;
	padding: 12px 15px;
}

.search_btn {
	vertical-align: middle;
	margin: 0 auto;
	margin-top: 15px;
	margin-bottom: 15px;
	padding: 0 20px;
	text-align: center;
	font-weight: 400;
	font-size: 13px;
	height: 35px;
	line-height: 34px;
	min-width: 85px;
}

.search_submit {
	border: 1px solid #525f78;
	background: #525f78;
	color: #fff;
	cursor: pointer;
}

.search_reset {
	border: 1px solid #dcdcde;
	background: #FFFFFF;
	color: $666666;
	cursor: pointer;
}
</style>
<script>
	function openLoanPopup(no) {
		var url = '/cyber/admin/loanPopup.do?memberNo=' + no;
		var name = 'loanPopup';
		var option = 'width=800, height=600';
		window.open(url, name, option);
	}
</script>
</head>
<body>
	<div id="wrap">
		<header>
			<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
		</header>
		<main>
			<h3>회원 목록</h3>
			<form action="./searchMember.do" method="post">
				<table class="table_search">
					<tbody>
						<tr>
							<th>
								<label class="" style="display: none;">
									<input type="checkbox" name="" value="">
								</label>
								검색어
							</th>
							<td>
							<select name="search_type" class="">
								<option value="all">전체</option>
								<option value="id">아이디</option>
								<option value="name">이름</option>
								<option value="email">이메일</option>
								<option value="joinDate">가입일</option>
							</select>
							<input type="text" name="keyword" value="" size="80">
							</td>
						</tr>
						<tr>
							<th>
								<span>등급</span>
								<label class="" style="display: none;">
									<input type="checkbox" name="" value="">
								</label>
							</th>
							<td>
								<select name="grade" class="">
									<option value="">전체</option>
									<option value="5">일반</option>
									<option value="9">관리자</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				
				<div class="search_btn">
					<span class="search">
						<button class="search_submit" type="submit">검색</button>
						<button type="reset" class="search_reset" >초기화</button>
					</span>
				</div>
			</form>
			
			
			<table class="table_basic">
			<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>가입일</th>
				<th>등급</th>
				<th>대출관리</th>
			</tr>
			</thead>
			<c:forEach items="${list }" var="l">
				<tr>
					<td>${l.no }</td>
					<td>${l.id }</td>
					<td>${l.name }</td>
					<td>${l.email }</td>
					<td>${l.join_date }</td>
					<td>${l.grade }</td>
					<td>
						<button onclick="openLoanPopup(${l.no })">보기</button>
					</td>
					<c:if test="">
						<td>검색한 회원이 없습니다.</td>
					</c:if>
					<c:if test="">
						<td>등록된 회원이 없습니다.</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		</main>
	</div>
</body>
</html>