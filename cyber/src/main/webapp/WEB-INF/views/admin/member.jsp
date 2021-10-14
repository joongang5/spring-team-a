<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자</title>
	<script src="<c:url value="/resources/js/admin/member.js"/>"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/member.css"/>">
</head>
<body>
	<div id="wrap">
		<header>
			<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
		</header>
		<main>
			<div class="container">
				<div class="row">
					<h3>회원 목록</h3>
					<form action="searchMember.do" method="post">
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
									<select name="searchType" class="">
										<option value="all">전체</option>
										<option value="id">아이디</option>
										<option value="name">이름</option>
										<option value="email">이메일</option>
										<option value="join_date">가입일</option>
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
											<option value="gall">전체</option>
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
						<c:if test="${fn:length(memberDTOList) gt 0 }">
							<c:forEach items="${memberDTOList }" var="l">
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
							</tr>
							</c:forEach>
						</c:if>
					</table>
					<c:if test="${fn:length(memberDTOList) le 0 }">
						<div class="nomember">등록된 회원이 없습니다.</div>
					</c:if>	
				</div>
			</div>
		</main>
	</div>
</body>
</html>