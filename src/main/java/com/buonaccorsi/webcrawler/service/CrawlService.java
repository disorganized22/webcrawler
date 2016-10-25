package com.buonaccorsi.webcrawler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.buonaccorsi.webcrawler.Internet;
import com.buonaccorsi.webcrawler.constant.LinkCode;
import com.buonaccorsi.webcrawler.model.Page;

public class CrawlService	 {

	private HashMap<String, Integer> links;
	private HashMap<String, String> results;
	
	public CrawlService(){
		links = new HashMap<String, Integer>();
		results = new HashMap<String, String>();
		
	}
	
	public void crawl(Internet inet){
		for (String address: inet.getAddresses()){
			crawl(address, inet);
		}
	}
	public void crawl(String url,Internet inet){
		
			ArrayList<String> tmpLinks = new ArrayList<String>();
			tmpLinks.add(url);
			callPage(tmpLinks, inet);
			
		
	}
	
	public Page callPage(List<String> list, Internet inet){
		Page returnPage=null;
		HashMap<String, Future<LinkResult>> sent = new HashMap<String,Future<LinkResult>>();
		ExecutorService executor = Executors.newFixedThreadPool(1); 
		if (list!=null){
		for (String link: list) {
			//send calls if you haven't visited already 
				if (checkUrl(link)==0){	
					// Fire a request. in a callback method
					sent.put(link,executor.submit(new LinkCall(link, inet)));
				}
		
				// Shutdown the threads during shutdown of your app.
			}
		// Do your other tasks here (will be processed immediately, current thread won't block).
		// ...
		try {
		// Get the response (here the current thread will block until response is returned).
			// change the loop to use a stack to pop 
			while(sent.size()>0){
				Iterator<String> returns = (sent.keySet().iterator());
				while(returns.hasNext()){
					Future<LinkResult> fut = sent.get(returns.next());
					
					if (fut.isDone()) {
						sent.remove(fut.get().getUrl());
						returnPage=fut.get().getPage();
						if (returnPage!=null){
							results.put(fut.get().getUrl(), LinkCode.SUCCESS.getValue());
						} else {
							results.put(fut.get().getUrl(), LinkCode.FAILURE.getValue());
						}
					} 
				}
			}
		// ...
		if (returnPage!=null){
			//If the link returned contains links crawl to those pages recursively.
			callPage(returnPage.getLinks(), inet);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		executor.shutdown();
		}
		return returnPage;
		
	}
	public List<String> getSkipped() {
		
		ArrayList<String> skipped=new ArrayList<String>();
		Iterator<String> linkItr = (links.keySet().iterator());
		String link;
		Integer times;
		while (linkItr.hasNext()) {
			link=(String)linkItr.next();
			times =(Integer)links.get(link);
			if (times!=null && times.intValue()>1){
				skipped.add(link);
			}
		}	
		return skipped;
	}
	public List<String> getResults(String type) {
		
		ArrayList<String> result=new ArrayList<String>();
		Iterator<String> linkItr = (results.keySet().iterator());
		String link;
		while (linkItr.hasNext()) {
			link=(String)linkItr.next();
			if (results.get(link).equalsIgnoreCase(type))
				result.add(link);
		}
			
		return result;
	}

	private int checkUrl(String link){
		int retVal=0;
		if (links.containsKey(link)){
			retVal=((Integer)links.get(link)).intValue()+1;
			links.put(link, new Integer(retVal));
		} else {
			links.put(link, new Integer(1));				
		}
		return retVal;
	}

	
}
