package com.buonaccorsi.webcrawler.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.buonaccorsi.webcrawler.Internet;
import com.buonaccorsi.webcrawler.constant.LinkCode;
import com.buonaccorsi.webcrawler.model.Resp;
import com.buonaccorsi.webcrawler.service.CrawlService;

@Stateless
@LocalBean
@Path("/crawl")
public class WebCrawlerController {

	  // POST
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	public Resp get(Internet inet, @Context final HttpServletResponse response,  @Context final HttpServletRequest req) {
    	Resp resp = new Resp();
    	CrawlService crawlSvc = new CrawlService();

    	// Call a handle to the  internet to crawls.
    	crawlSvc.crawl(inet);
     	resp.setSkipped(crawlSvc.getSkipped());
    	resp.setSuccess(crawlSvc.getResults(LinkCode.SUCCESS.getValue()));
       	resp.setError(crawlSvc.getResults(LinkCode.FAILURE.getValue()));
    	
    	
    	return resp;
    }

}
