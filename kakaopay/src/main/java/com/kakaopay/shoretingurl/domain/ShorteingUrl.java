package com.kakaopay.shoretingurl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShorteingUrl {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(length = 100000,unique=true)
	private String url;
	
	@Column(length = 8,unique=true)
	private String shortUrl;

	public ShorteingUrl() {
    }

    public ShorteingUrl(String shortUrl, String url) {
    	this.shortUrl = shortUrl;
        this.url = url;
    }
    
    public int getId() {
        return id;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getShortUrl() {
        return shortUrl;
    }
    
}
