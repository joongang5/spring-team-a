<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<style>
body {
	margin: 0;
	padding: 0;
}

ul {
	margin: 0;
}

table {
	border: 1px solid black;
}

th, td {
	border: 1px solid black;
}

td img {
	width: 60px;
}

.content {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

function linkPage(pageNo){
/* 	var searchOption = getParameterByName('searchOption');
	var search = getParameterByName('search'); */
	/* if(!searchOption && !search){
		location.href="./ebookMain.do?pageNo="+pageNo;
	}else{ */
		location.href="./home.do?pageNo="+pageNo;
	//}
}
$(function(){
	var chkObj = document.getElementsByName("RowCheck");
	var rowCnt = chkObj.length;
	
	$("input[name='allCheck']").click(function(){
		var chk_listArr = $("input[name='RowCheck']");
		for (var i=0; i<chk_listArr.length; i++){
			chk_listArr[i].checked = this.checked;
		}
	});
	$("input[name='RowCheck']").click(function(){
		if($("input[name='RowCheck']:checked").length == rowCnt){
			$("input[name='allCheck']")[0].checked = true;
		}
		else{
			$("input[name='allCheck']")[0].checked = false;
		}
	});
});
function bookAdd(){
	var valueArr = new Array();
    var list = $("input[name='RowCheck']");
    for(var i = 0; i < list.length; i++){
        if(list[i].checked){ //선택되어 있으면 배열에 값을 저장함
            valueArr.push(list[i].value);
        }
    }
    if (valueArr.length == 0){
    	alert("선택된 도서가 없습니다.");
    }
    else{
		var chk = confirm(valueArr.length + "개의 도서를 등록하시겠습니까?");				 
		$.ajax({
		    url : "registBook.do",                    // 전송 URL
		    type : 'POST',                // POST 방식
		    traditional : true,
		    data : {
		    	valueArr : valueArr        // 보내고자 하는 data 변수 설정
		    },
		});
	}
}
</script>

</head>
<body>
	<div id="wrap">
		<header>
			<c:import url="/WEB-INF/views/admin/component/headerInner.jsp" />
		</header>
		<main>
			<form method="post" id="form">
				<h3>도서 일괄 등록</h3>
				<input type="submit" value="인기"
					formaction="/cyber/admin/ebook/registBestBook.do"> <input
					type="submit" value="사서추천"
					formaction="/cyber/admin/ebook/registRecommendBook.do">
				<hr>

				<h3>도서 개별 등록</h3>
				<table>
					<tr>
						<th><select name="searchType">
								<option value="title"
									<c:if test="${commandMap.searchType eq 'title' }">selected="selected"</c:if>>제목</option>
								<option value="isbn"
									<c:if test="${commandMap.searchType eq 'isbn' }">selected="selected"</c:if>>ISBN</option>
						</select></th>
						<td><input type="text" name="searchValue"
							value="${commandMap.searchValue }"></td>
					</tr>
					<tr>
						<th>타이틀</th>
						<td><span>${bookInfo.title }</span></td>
						<th>저자</th>
						<td><span>${bookInfo.author }</span></td>
					</tr>
				</table>
				<input type="submit" value="검색"
					formaction="/cyber/admin/ebook/searchBook.do"> <input
					type="submit" value="등록" onclick="bookAdd();">
					<input
					type="submit" value="초기화">
		
			<c:if test="${bookInfoList ne null }">
				<h4>검색 도서 목록</h4>
				<table>
					<tr>
						<td><input id="allCheck" type="checkbox" name="allCheck"/></td>
						<td>서명</td>
						<td>표지 이미지</td>
						<td>저자</td>
						<td>발행처</td>
						<td>ISBN</td>
						<td>출판일</td>
						<td>가격</td>
						<td>페이지 수</td>
						<td>책 크기</td>
					</tr>
					<c:forEach items="${bookInfoList }" var="l">
						<tr>
							<td><input name="RowCheck" type="checkbox" value="${l.isbn}"/></td>
							<td>${l.title}</td>
							<td><img src="${l.title_url}"></td>
							<td>${l.author}</td>
							<td>${l.publisher}</td>
							<td>${l.isbn}</td>
							<td>${l.datetime}</td>
							<td>${l.price}</td>
							<td>${l.page}</td>
							<td>${l.book_size}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<hr>
		</form>
			<h3>등록된 도서 목록</h3>
			<table>
				<tr>
					<th>번호</th>
					<td>카테고리</td>
					<td>서명</td>
					<td>표지 이미지</td>
					<td>저자</td>
					<td>발행처</td>
					<td>ISBN</td>
					<td>출판일</td>
					<td>가격</td>
					<td>페이지 수</td>
					<td>책 크기</td>
				</tr>
				<c:forEach items="${ebookDTOList }" var="l">
					<tr>
						<td>${l.no}</td>
						<td>${l.category}</td>
						<td>${l.title}</td>
						<td><img src="${l.title_url}"></td>
						<td>${l.author}</td>
						<td>${l.publisher}</td>
						<td>${l.isbn}</td>
						<td>${l.datetime}</td>
						<td>${l.price}</td>
						<td>${l.page}</td>
						<td>${l.book_size}</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<ul id="paging">
					<ui:pagination paginationInfo="${paginationInfo }" type="text"
						jsFunction="linkPage" />
				</ul>
			</div>
		</main>
	</div>
</body>
</html>