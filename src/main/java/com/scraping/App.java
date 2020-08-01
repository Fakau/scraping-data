package com.scraping;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String searchQuery = "Iphone 6s" ;

    	WebClient client = new WebClient();  
    	client.getOptions().setCssEnabled(false);  
    	client.getOptions().setJavaScriptEnabled(false);  
    	try {  
    	  String searchUrl = "https://newyork.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");
    	  HtmlPage page = client.getPage(searchUrl);
    	  List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//li[@class='result-row']") ;
    	  List<Item> itemsList = new ArrayList<Item>();
    	  if(items.isEmpty()){
    	    System.out.println("No items found !");
    	  }else{
    		  for(HtmlElement htmlItem : items){
      			HtmlAnchor itemAnchor = ((HtmlAnchor) htmlItem.getFirstByXPath(".//p[@class='result-info']/a"));
      			HtmlElement spanPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//a/span[@class='result-price']")) ;

      			// It is possible that an item doesn't have any price, we set the price to 0.0 in this case
      			String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText() ;
      			Item item = new Item();
      			item.setTitle(itemAnchor.asText());
      			//item.setUrl( baseUrl + itemAnchor.getHrefAttribute());
      			item.setPrice(new BigDecimal(itemPrice.replace("$", "")));
      			itemsList.add(item);
      		}
    		  System.out.println(itemsList);
    	  }
    	}catch(Exception e){
    	  e.printStackTrace();
    	}
    	
    }
}
