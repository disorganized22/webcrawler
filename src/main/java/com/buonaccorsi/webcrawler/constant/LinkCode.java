package com.buonaccorsi.webcrawler.constant;

public enum LinkCode {
	
	SKIPPED("SKIPPED"),
	SENT("SENT"),
	SUCCESS("SUCCESS"),
	FAILURE("FAILURE");

	private LinkCode(String code) {
		this.value = code;
	}
    private String value;
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



}
