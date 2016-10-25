package com.buonaccorsi.webcrawler.model;

import java.util.List;

public class Resp {
	
	private List<String> success;
	private List<String> skipped;
	private List<String> error;
	public List<String> getSuccess() {
		return success;
	}
	public void setSuccess(List<String> success) {
		this.success = success;
	}
	public List<String> getSkipped() {
		return skipped;
	}
	public void setSkipped(List<String> skipped) {
		this.skipped = skipped;
	}
	public List<String> getError() {
		return error;
	}
	public void setError(List<String> error) {
		this.error = error;
	}
	

}
