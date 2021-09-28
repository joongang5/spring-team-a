package com.teama.admin.service;

public enum SearchType {

	Title(0),
	ISBN(1);
	
	private final int value;
	
	SearchType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
