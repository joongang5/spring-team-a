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
		html += 	"<td>" + l.no + "</td>";
		html += 	"<td>" + l.book_no + "</td>";
		html += 	"<td>" + l.title + "</td>";
		html += 	"<td>" + l.vol + "</td>";
		html += 	"<td>" + l.author + "</td>";
		html += 	"<td>" + l.ea_isbn + "</td>";
		html += 	"<td><input type='text' id='maxCount" + l.no + "' value='" + l.max_count + "'></td>";
		html += 	"<td><input type='text' id='loanCount" + l.no + "' value='" + l.loan_count + "'></td>";
		html += 	"<td><input type='text' id='reserveCount" + l.no + "' value='" + l.reserve_count + "'></td>";
		html += 	"<td><input type='button' value='수정' onclick='updateStoredBook(" + l.no + ")'></td>";
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
		},
		error : function(request, status, error) {
			alert("error : " + error);
		}
	});
}

function updateStoredBook(no) {
	var maxCount = document.getElementById('maxCount' + no).value;
	var loanCount = document.getElementById('loanCount' + no).value;
	var reserveCount = document.getElementById('reserveCount' + no).value;
	location.href = '/cyber/admin/storage/updateBook.do?bookNo=' + no + '&maxCount=' + maxCount + "&loanCount=" + loanCount + "&reserveCount=" + reserveCount;
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
	
	var html = "<ui:pagination paginationInfo='" + data.paginationInfo + "' type='text' jsFunction='linkPageAJAX'/>";
	
	$("#pagination").append(html);
}