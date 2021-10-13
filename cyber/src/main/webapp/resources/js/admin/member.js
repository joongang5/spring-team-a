function openLoanPopup(no) {
	var url = 'showPopup.do?memberNo=' + no;
	var name = 'loanPopup';
	var option = 'width=800, height=600';
	window.open(url, name, option);
}