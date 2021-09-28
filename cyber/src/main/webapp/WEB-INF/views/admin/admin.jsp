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
		#wrap { width: 1000px; margin: 0 auto; }
		header { width: 1000px; height: 130px; background: #111; }
		main { float: left; width: 1000px; height: 600px; background: #222; }
		footer	{ clear: both; width: 1000px; height: 100px; background: #333; }
		ul { margin: 0; }
		table { border: 1px solid black; }
		th, td { border: 1px solid black; }
	</style>
	<script type="text/javascript">
		
	</script>
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
				<input type="submit" value="인기" formaction="/cyber/admin/bookBest.do">
				<input type="submit" value="사서추천" formaction="/cyber/admin/bookRecommend.do">
				<hr>
				<h3>도서 개별 등록</h3>
				<table>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title">
						</td>
					</tr>
					<tr>
						<th>ISBN</th>
						<td>
							<input type="text" name="isbn">
						</td>
					</tr>
					<tr>
						<th>저자</th>
						<td>
							<span></span>
						</td>
					</tr>
				</table>
				<input type="submit" value="검색" formaction="/cyber/admin/bookSearch.do">
				<input type="submit" value="등록" formaction="/cyber/admin/bookRegist.do">
				<input type="submit" value="초기화">
			</form>
			<hr>	
			<h3>도서 목록</h3>
			<table>
				<tr>
					<th>번호</th>
					<th>ISBN</th>
				</tr>
				<c:forEach items="${list }" var="l">
					<tr>
						<td>${l.no }</td>
						<td>${l.isbn }</td>
					</tr>
				</c:forEach>
			</table>
		</main>
		<footer>footer</footer>
	</div>
</body>
</html>