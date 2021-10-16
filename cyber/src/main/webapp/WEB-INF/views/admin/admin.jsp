<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/base.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/admin.css"/>">
</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
	</header>
	<main>
		<div class="container">
			<div class="row">
				<div class="summary_wrap">
					<ul class="box_wrap">
						<li>
							<dl>
								<dt>오늘 대출 건수</dt>
								<dd>${todayLoanCount }</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>오늘 예약 건수</dt>
								<dd>${todayReserveCount }</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>오늘 반납 건수</dt>
								<dd>${todayReturnCount }</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>오늘 가입한 회원</dt>
								<dd>${todayJoinCount }</dd>
							</dl>
						</li>
					</ul>
				</div>
				<div class="contents">
					<h3>최근 대출 기록 (5)</h3>
					<table>
						<tr>
							<th>book_no</th>
							<th>표제</th>
							<th>저자</th>
							<th>대출일</th>
							<th>상태</th>
						</tr>
						<c:if test="${fn:length(loanViewDTOList) gt 0 }">
							<c:forEach items="${loanViewDTOList }" var="l">
							<tr>
								<td>${l.book_no }</td>
								<td>${l.title }</td>
								<td>${l.author }</td>
								<td>${l.loan_date }</td>
								<td>
									<c:if test="${l.state eq 0 }">
										대출
									</c:if>
									<c:if test="${l.state eq 1 }">
										예약
									</c:if>
									<c:if test="${l.state eq 2 }">
										반납
									</c:if>
								</td>
							</tr>
							</c:forEach>
						</c:if>
					</table>
					<c:if test="${fn:length(loanViewDTOList) le 0 }">
						<div class="data_empty">대출 내역이 없습니다.</div>
					</c:if>
					
					<h3>최근 가입 기록 (5)</h3>
					<table>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>이메일</th>
							<th>가입일</th>
							<th>등급</th>
						</tr>
						<c:if test="${fn:length(memberDTOList) gt 0 }">
							<c:forEach items="${memberDTOList }" var="l">
							<tr>
								<td>${l.no }</td>
								<td>${l.id }</td>
								<td>${l.name }</td>
								<td>${l.email }</td>
								<td>${l.join_date }</td>
								<td>${l.grade }</td>
							</tr>
							</c:forEach>
						</c:if>
					</table>
					<c:if test="${fn:length(memberDTOList) le 0 }">
						<div class="data_empty">등록된 회원이 없습니다.</div>
					</c:if>	
				</div>
			</div>
		</div>
	</main>
</body>
</html>