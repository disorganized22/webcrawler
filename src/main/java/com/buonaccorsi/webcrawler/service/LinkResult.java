package com.buonaccorsi.webcrawler.service;

import com.buonaccorsi.webcrawler.constant.LinkCode;

public class LinkResult {
	   public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String url;

	    public LinkResult(String inUrl) {
	        url=inUrl;
	    }

	    public String getRespCode() {
	    	String respCode=LinkCode.FAILURE.getValue();
	    	if (this.url!=null){
	    		respCode=LinkCode.SUCCESS.getValue();
	    	}
	    		return respCode;
	    	}
	        
	    }