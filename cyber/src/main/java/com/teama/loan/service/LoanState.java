package com.teama.loan.service;

public enum LoanState {
	loan(0),
	reserve(1),
	returned(2);
	
	private final int value;
	
	LoanState(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
