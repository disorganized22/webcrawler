package com.buonaccorsi.webcrawler.service;

import com.buonaccorsi.webcrawler.Internet;
import com.buonaccorsi.webcrawler.constant.LinkCode;
import com.buonaccorsi.webcrawler.model.Page;

public class LinkResult {
	   public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String url;
	 Internet iNet;

	    public LinkResult(String inUrl,  Internet inet) {
	        url=inUrl;
	        iNet=inet;
	    }

	    public Page getPage() {
	    	Page page = new Page();
	    	page.setAddress(url);
	    	if (iNet.getPage(url)!=null)
	    		page.setLinks(iNet.getPage(url).getLinks());
	    	String respCode=LinkCode.FAILURE.getValue();
	    	if (this.url!=null){
	    		respCode=LinkCode.SUCCESS.getValue();
	    	}
	    		return page;
	    	}
	        
	    }