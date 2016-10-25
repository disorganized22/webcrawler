package com.buonaccorsi.webcrawler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.buonaccorsi.webcrawler.model.Page;

public class Internet {

		
		   private List<Page> pages = null;
		   
		   public List<Page> getPages() {
			return pages;
		}

		public void setPages(List<Page> pages) {
			this.pages = pages;
		}

		public Internet() {
			   pages = new ArrayList<Page>();

		   }

		public List<String> getAddresses() {
			ArrayList<String> addr = new ArrayList<String>();
			for (Page p: pages){
				addr.add(p.getAddress());
			}
			return addr;
		}
		public Page getPage(String address) {
			Page retPage=null;
			for (Page p: pages){
				if(p.getAddress().equalsIgnoreCase(address))
					return p;
				
			}
			return retPage;
		}

}
