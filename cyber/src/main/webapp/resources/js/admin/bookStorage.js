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
		html += "<tr>";
		html += 	"<td>" + list[i].no + "</td>";
		html += 	"<td>" + list[i].book_no + "</td>";
		html += 	"<td>" + list[i].title + "</td>";
		html += 	"<td>" + list[i].vol + "</td>";
		html += 	"<td>" + list[i].author + "</td>";
		html += 	"<td>" + list[i].ea_isbn + "</td>";
		html += 	"<td>" + list[i].max_count + "</td>";
		html += 	"<td>" + list[i].loan_count + "</td>";
		html += 	"<td>" + list[i].reserve_count + "</td>";
		html += 	"<td><input type='button' value='수정' onclick='updateStoredBook(" + list[i].no + ")'></td>";
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