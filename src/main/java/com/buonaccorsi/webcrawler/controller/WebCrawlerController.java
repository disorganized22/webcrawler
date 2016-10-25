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

import com.buonaccorsi.webcrawler.constant.LinkCode;
import com.buonaccorsi.webcrawler.model.Payload;
import com.buonaccorsi.webcrawler.model.Resp;
import com.buonaccorsi.webcrawler.service.CrawlService;

@Stateless
@LocalBean
@Path("/crawl")
public class WebCrawlerController {

	  // POST
    @POST
    @Consumes("application/json")
    @Produces("application/json")
	public Resp post(Payload payload, @Context final HttpServletResponse response,  @Context final HttpServletRequest req) {
    	Resp resp = new Resp();
    	CrawlService crawlSvc = new CrawlService();
    	crawlSvc.buildUrlList(payload.getPages());
    	crawlSvc.callPages();
    	resp.setSkipped(crawlSvc.getSkipped());
    	resp.setSuccess(crawlSvc.getResults(LinkCode.SUCCESS.getValue()));
       	resp.setError(crawlSvc.getResults(LinkCode.FAILURE.getValue()));
    	
    	
    	return resp;
    }

}
