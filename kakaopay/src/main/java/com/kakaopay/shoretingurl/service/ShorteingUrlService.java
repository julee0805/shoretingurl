package com.kakaopay.shoretingurl.service;

import com.kakaopay.shoretingurl.domain.ShorteingUrl;

public interface ShorteingUrlService {
	
	public ShorteingUrl getShorteingUrlByUrl(String url);
	
	public ShorteingUrl getShorteingUrlByShortUrl(String shortUrl);	
	
	public ShorteingUrl setShorteingUrl(String url) throws Exception ;

	public String getShorteingUrl() throws Exception ;

	
}
