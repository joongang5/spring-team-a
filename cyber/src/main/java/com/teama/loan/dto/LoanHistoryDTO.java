package com.teama.loan.dto;

public class LoanHistoryDTO {

	private int no;
	private int book_no;
	private int member_no;
	private int state;
	private String date;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBook_no() {
		return book_no;
	}

	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String loan_date) {
		this.date = loan_date;
	}
}
