package com.scraping;

import java.math.BigDecimal;

public class Item {
	private String title ; 
    private BigDecimal price ;
    private String url ;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Item [title=" + title + ", price=" + price + ", url=" + url + "]\n";
	}
	
    
}
