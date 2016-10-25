package com.buonaccorsi.webcrawler.service;

import java.util.concurrent.Callable;




public class LinkCall implements Callable<LinkResult> {
	
	String url;
	public LinkCall(String inUrl) {
		this.url=inUrl;
	
	}

	@Override
	public LinkResult call() throws Exception {
		// TODO Auto-generated method stub
		
		return new LinkResult(url);
	}


	
}
