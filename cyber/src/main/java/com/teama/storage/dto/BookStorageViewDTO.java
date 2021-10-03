package com.teama.storage.dto;

public class BookStorageViewDTO extends BookStorageDTO {

	private String title;
	private String vol;
	private String author;
	private String ea_isbn;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVol() {
		return vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEa_isbn() {
		return ea_isbn;
	}

	public void setEa_isbn(String ea_isbn) {
		this.ea_isbn = ea_isbn;
	}
}
