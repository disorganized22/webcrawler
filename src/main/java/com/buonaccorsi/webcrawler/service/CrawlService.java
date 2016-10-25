package com.buonaccorsi.webcrawler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.buonaccorsi.webcrawler.model.Page;

public class CrawlService	 {

	private HashMap<String, Integer> links;
	private HashMap<String, String> results;
	private List<Future<LinkResult>> futures;
	
	public CrawlService(){
		links = new HashMap<String, Integer>();
		results = new HashMap<String, String>();
		futures = new ArrayList<Future<LinkResult>>();
	}
	public void buildUrlList(Page[] pages) {
		for (Page inPage: pages) {
			for (String link: inPage.getLinks()) {
				if (links.containsKey(link)){
					links.put(link, new Integer(((Integer)links.get(link)).intValue()+1));
				} else {
					links.put(link, new Integer(1));				
				}
			}		
		}
	}
	public void callPages(){
		Set<String> sent = new HashSet<String>();

		Iterator<String> linkItr = (links.keySet().iterator());
		ExecutorService executor = Executors.newFixedThreadPool(1); 
		
		while (linkItr.hasNext()) {
			//send calls 
				
				String link=(String)linkItr.next();
				// Have one (or more) threads ready to do the async tasks. Do this during startup of your app.
				// Fire a request.
				futures.add(executor.submit(new LinkCall(link)));
				sent.add(link);
		
				// Shutdown the threads during shutdown of your app.
			}
		// Do your other tasks here (will be processed immediately, current thread won't block).
		// ...
		try {
		// Get the response (here the current thread will block until response is returned).
			// change the loop to use a stack to pop 
			while(sent.size()>0){
				for (Future<LinkResult> fut: futures){
					if (fut.isDone()) {
						sent.remove(fut.get().getUrl());
						results.put(fut.get().getUrl(), fut.get().getRespCode());
					} else {
						
					}
				}
			}
		// ...
		
		}catch (Exception e) {
			e.printStackTrace();
		}

		executor.shutdown();
	
		
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

	
}
