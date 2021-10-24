function openLoanPopup(no) {
	var url = 'showPopup.do?memberNo=' + no;
	var name = 'loanPopup';
	var option = 'width=800, height=600, scrollbars = yes';
	window.open(url, name, option);
}

function linkPage(pageNo) {
	var pathname = window.location.pathname;
	location.href = pathname + "?pageNo=" + pageNo;
}