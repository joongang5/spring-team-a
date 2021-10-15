package com.teama.ebook.dto;

public class EbookReviewDTO {
	private int review_no, book_no, member_no, rating, ratingSum, reviewCount;
	public int getReviewCount() {
		return reviewCount;
	}
	public int getRatingSum() {
		return ratingSum;
	}
	public void setRatingSum(int ratingSum) {
		this.ratingSum = ratingSum;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	private String id, name, reviewCmt, date;
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReviewCmt() {
		return reviewCmt;
	}
	public void setReviewCmt(String reviewCmt) {
		this.reviewCmt = reviewCmt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
