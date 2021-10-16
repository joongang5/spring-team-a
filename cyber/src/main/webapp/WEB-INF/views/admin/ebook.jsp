<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/base.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/admin/bookStorage.css"/>">

<script type="text/javascript">
function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
			.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(
			/\+/g, " "));
}
function linkPage(pageNo){
	var searchType = getParameterByName('searchType');
	var searchValue = getParameterByName('searchValue');
	if(searchType && searchValue){
		location.href="./searchBook.do?pageNo="+pageNo+"&searchType="+searchType+"&searchValue="+searchValue;
	}else{
		location.href="./home.do?pageNo="+pageNo;
}
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
		    success: function(data){
					alert(data + "개 도서 등록 완료");	
					var chk_listArr = $("input[name='RowCheck']");
					for (var i=0; i<chk_listArr.length; i++){
						chk_listArr[i].checked = this.false;
					}
			},
			error: function(request, status, error){
				alert("문제 발생 : " + error);
			}
		});
	}
}
</script>
<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/admin/ebook.css"/>">
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
			</form>
				
		<div class="container">
				<div class="row">
					<form action="searchBook.do" method="get">
						<table class="table_search">
							<tr>
								<th>검색어</th>
								<td>
									<select id="searchType" name="searchType">
										<option value="title" <c:if test="${searchType eq 'title'}">selected="selected"</c:if>>title</option>
										<option value="isbn" <c:if test="${searchType eq 'isbn'}">selected="selected"</c:if>>isbn</option>
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
					<div class="util_btns">
						<button type="button" onclick="bookAdd()">도서 등록</button>
					</div>
					<hr>	
				
					<table class="table_list">
						<thead>
							<tr>
								<th style="width: 5%"><input id="allCheck" type="checkbox" name="allCheck"/></th>
								<th style="width: 35%">서명</th>
								<th style="width: 20%">저자</th>
								<th style="width: 10%">발행처</th>
								<th style="width: 5%">ISBN</th>
								<th style="width: 5%">출판일</th>
								<th style="width: 5%">가격</th>
								<th style="width: 5%">페이지수</th>
								<th style="width: 5%">책크기</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<c:forEach items="${ebookDTOList }" var="l">
							<tr>
							<td><input name="RowCheck" type="checkbox" value="${l.isbn}"/></td>
								<td>${l.title}</td>
								<td>${l.author}</td>
								<td>${l.publisher}</td>
								<td>${l.isbn}</td>
								<td>${l.datetime}</td>
								<td>${l.price}</td>
								<td>${l.page}</td>
								<td>${l.book_size}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div id="pagination">
						<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="linkPage"/>
					</div>
				</div>
			</div>
		
			<%-- <h3>등록된 도서 목록</h3>
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
			</div> --%>
		</main>
	</div>
</body>
</html>