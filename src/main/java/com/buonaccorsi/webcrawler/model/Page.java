package com.buonaccorsi.webcrawler.model;

import java.util.List;

public class Page {
	
	private String address;
	private List<String> links;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getLinks() {
		return links;
	}
	public void setLinks(List<String> links) {
		this.links = links;
	}
	
	
}
