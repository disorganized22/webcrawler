package com.buonaccorsi.webcrawler.service;

import java.util.concurrent.Callable;

import com.buonaccorsi.webcrawler.Internet;




public class LinkCall implements Callable<LinkResult> {
	
	String url;
	Internet iNet;
	public LinkCall(String inUrl, Internet inet) {
		this.url=inUrl;
		iNet=inet;
	
	}

	@Override
	public LinkResult call() throws Exception {
		// TODO Auto-generated method stub
		
		return new LinkResult(url, iNet);
	}


	
}
