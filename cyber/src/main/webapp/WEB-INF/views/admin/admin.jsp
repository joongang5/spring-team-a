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
			<form name="bookInfo" method="post">
				<h3>도서 일괄 등록</h3>
				<input type="submit" value="인기" formaction="/cyber/admin/registBestBook.do">
				<input type="submit" value="사서추천" formaction="/cyber/admin/registRecommendBook.do">
				<hr>
				
				<h3>도서 개별 등록</h3>
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
					<tr>
						<th>저자</th>
						<td>
							<span>${infoMap.author }</span>
						</td>
					</tr>
				</table>
				<input type="submit" value="검색" formaction="/cyber/admin/searchBook.do">
				<input type="submit" value="등록" formaction="/cyber/admin/registBook.do">
				<input type="submit" value="초기화">
			</form>
			<hr>
			
			<h3>등록된 도서 목록</h3>
			<table>
				<tr>
					<th>번호</th>
					<td>표체</td>
					<td>권차</td>
					<td>총서명</td>
					<td>총서편차</td>
					<td>저자</td>
					<td>ISBN</td>
					<td>ISBN 부가기호</td>
					<td>세트 ISBN</td>
					<td>세트 ISBN 부가기호</td>
					<td>세트표현</td>
					<td>발행처</td>
					<td>판사항</td>
					<td>예정가격</td>
					<td>한국십진분류</td>
					<td>듀이십진분류</td>
					<td>페이지</td>
					<td>책크기</td>
					<td>발행제본형태</td>
					<td>출판예정일</td>
					<td>주제</td>
					<td>전자책여부</td>
					<td>CIP 신청여부</td>
					<td>CIP 제어번호</td>
					<td>표지이미지 URL</td>
					<td>목차</td>
					<td>책소개</td>
					<td>책요약</td>
					<td>출판사 홈페이지 URL</td>
					<td>등록날짜</td>
					<td>수정날짜</td>
				</tr>
				<c:forEach items="${list }" var="l">
					<tr>
						<td>${l.no}</td>
						<td>${l.title}</td>
						<td>${l.vol}</td>
						<td>${l.series_title}</td>
						<td>${l.series_no}</td>
						<td>${l.author}</td>
						<td>${l.ea_isbn}</td>
						<td>${l.ea_add_code}</td>
						<td>${l.set_isbn}</td>
						<td>${l.set_add_code}</td>
						<td>${l.set_expression}</td>
						<td>${l.publisher}</td>
						<td>${l.edition_stmt}</td>
						<td>${l.pre_price}</td>
						<td>${l.kdc}</td>
						<td>${l.ddc}</td>
						<td>${l.page}</td>
						<td>${l.book_size}</td>
						<td>${l.form}</td>
						<td>${l.publish_predate}</td>
						<td>${l.subject}</td>
						<td>${l.ebook_yn}</td>
						<td>${l.cip_yn}</td>
						<td>${l.control_no}</td>
						<td>${l.title_url}</td>
						<td>${l.book_tb_cnt_url}</td>
						<td>${l.book_introduction_url}</td>
						<td>${l.book_summary_url}</td>
						<td>${l.publisher_url}</td>
						<td>${l.input_date}</td>
						<td>${l.update_date}</td>
					</tr>
				</c:forEach>
			</table>
		</main>
	</div>
</body>
</html>