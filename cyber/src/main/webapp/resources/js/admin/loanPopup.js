function onclickSearchBtn() {
	var bookNo = $("input[name=bookNo]").val();
	
	$.ajax({
		url : "/cyber/admin/loan/searchBookAJAX.do",
		type : "POST",
		dataType : "json",
		data : { "bookNo" : bookNo },
		success : function(bookStorageViewDTO) {
			rewriteSearchTbody(bookStorageViewDTO);
		},
		error : function(request, status, error) {
			alert("error : " + error);
		}
	});
}

function rewriteSearchTbody(bookStorageViewDTO) {
	$("#searchTbody").empty();
	
	var html = "";
	html += "<tr>";
	html += 	"<td>" + bookStorageViewDTO.book_no + "</td>";
	html += 	"<td>" + bookStorageViewDTO.title + "</td>";
	html += 	"<td>" + bookStorageViewDTO.vol + "</td>";
	html += 	"<td>" + bookStorageViewDTO.author + "</td>";
	html += 	"<td>" + bookStorageViewDTO.ea_isbn + "</td>";
	html += 	"<td>" + bookStorageViewDTO.max_count + "</td>";
	html += 	"<td>" + bookStorageViewDTO.loan_count + "</td>";
	html += 	"<td>" + bookStorageViewDTO.reserve_count + "</td>";
	html += "</tr>";

	$("#searchTbody").append(html);
}

function onclickLoanBtn() {
	var bookNo = $("input[name=bookNo]").val();
	var memberNo = $("input[name=memberNo]").val();
	
	$.ajax({
		url : "/cyber/admin/loan/loanAJAX.do",
		type : "POST",
		dataType : "json",
		data : { "bookNo" : bookNo, "memberNo" : memberNo },
		success : function(data) {
			if (data.loanResult == false) {
				alert("대출에 실패했습니다.");
				return;
			}
			rewriteLoanTbody(data);
		},
		error : function(request, status, error) {
			alert("error : " + error);
		}
	});
}

function rewriteLoanTbody(data) {
	$("#loanTbody").empty();
	
	var html = "";
	var list = data.loanViewDTOList;
	for (var i = 0; i < list.length; i++) {
		var l = list[i];
		
		html += "<tr>";
		html += 	"<td>" + l.no + "</td>";
		html += 	"<td>" + l.title + "</td>";
		html += 	"<td>" + l.author + "</td>";
		html += 	"<td>" + l.vol + "</td>";
		html += 	"<td>" + l.return_date + "</td>";
		html += "</tr>";
	}
	
	$("#loanTbody").append(html);
}