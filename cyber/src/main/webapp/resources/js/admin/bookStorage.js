function onclickSearchBtn() {
	var bookNo = $("input[name=bookNo]").val();
	
	$.ajax({
		url : "getBookAJAX.do",
		type : "POST",
		dataType : "json",
		data : { "bookNo" : bookNo },
		success : function(data) {
			rewriteTbody(data);
		},
		error : function(request, status, error) {
			alert("error : " + error);
		}
	});
}

function rewriteTbody(data) {
	$("#tbody").empty();
	
	var html = "";
	var list = data.bookStorageViewDTOList;
	for (var i = 0; i < list.length; i++) {
		var l = list[i];
		
		html += "<tr>";
		html += 	"<td>" + l.book_no + "</td>";
		html += 	"<td>" + l.title + "</td>";
		html += 	"<td>" + l.author + "</td>";
		html += 	"<td>" + l.isbn + "</td>";
		html += 	"<td><input type='text' id='maxCount" + l.book_no + "' value='" + l.max_count + "'></td>";
		html += 	"<td><input type='text' id='loanCount" + l.book_no + "' value='" + l.loan_count + "'></td>";
		html += 	"<td><input type='text' id='reserveCount" + l.book_no + "' value='" + l.reserve_count + "'></td>";
		html += 	"<td><input type='button' value='수정' onclick='onclickUpdateBtn(" + l.book_no + ")'></td>";
		html += "</tr>";
	}
	
	$("#tbody").append(html);
}

function onclickSearchUnregisteredBtn() {
	$.ajax({
		url : "unregisteredBookAJAX.do",
		type : "POST",
		dataType : "json",
		success : function(data) {
			rewriteTbody(data);
			rewritePagination(data);
		},
		error : function(request, status, error) {
			alert("error : " + error);
		}
	});
}

function onclickUpdateBtn(bookNo) {
	var maxCount = $("#maxCount" + bookNo).val();
	var loanCount = $("#loanCount" + bookNo).val();
	var reserveCount = $("#reserveCount" + bookNo).val();
	
	$.ajax({
		url : "/cyber/admin/storage/updateBook.do",
		type : "POST",
		dataType : "html",
		data : { "bookNo" : bookNo, "maxCount" : maxCount, "loanCount" : loanCount, "reserveCount" : reserveCount },
		success : function(data) {
			if (data == "0") {
				alert("수정에 실패했습니다.");
				return;
			}
			alert("수정에 성공했습니다.");
		},
		error : function(request, status, error) {
			alert("error : " + error);
		}
	});	
}

function linkPage(pageNo) {
	var pathname = window.location.pathname;
	location.href = pathname + "?pageNo=" + pageNo;
}

function linkPageAJAX(pageNo) {
	var pathname = window.location.pathname;
	var url = pathname.replace(".do", "AJAX.do");
	
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		data : { "pageNo" : pageNo },
		success : function(data) {
			rewriteTbody(data);
			rewritePagination(data);
		},
		error : function(request, status, error) {
			alert("error : " + error);
		}
	});
}

function rewritePagination(data) {
	$("#pagination").empty();
	
	var html = data.pagination;
	
	$("#pagination").append(html);
}

function onclickAutoLoanBtn() {
	$.ajax({
		url : "/cyber/admin/storage/autoLoan.do",
		type : "POST",
		dataType : "html",
		success : function(errorMessage) {
			if (errorMessage != "") {
				alert(errorMessage);
				return;
			}
			alert("자동 대출에 성공했습니다.");
		},
		error : function(request, status, error) {
			alert("error : " + error);
		}
	});	
}