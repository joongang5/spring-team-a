package com.teama.storage.dto;

public class BookStorageDTO {

	private int no;
	private int book_no;
	private int max_count;
	private int loan_count;
	private int reserve_count;

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

	public int getMax_count() {
		return max_count;
	}

	public void setMax_count(int max_count) {
		this.max_count = max_count;
	}

	public int getLoan_count() {
		return loan_count;
	}

	public void setLoan_count(int loan_count) {
		this.loan_count = loan_count;
	}

	public int getReserve_count() {
		return reserve_count;
	}

	public void setReserve_count(int reserve_count) {
		this.reserve_count = reserve_count;
	}
}
