package com.kakaopay.shoretingurl.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.shoretingurl.KakaopayApplication;
import com.kakaopay.shoretingurl.domain.ShorteingUrl;
import com.kakaopay.shoretingurl.repository.ShorteingUrlRepository;
import com.kakaopay.shoretingurl.service.impl.ShorteingUrlServiceImpl;

@SpringBootTest
@ContextConfiguration(classes={KakaopayApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ShorteingUrlServiceTest {
	
	private String url ="https://www.kakaopay.com";
	private String shortUrl ="gR9ahgdE";

	@Autowired
	private ShorteingUrlRepository shorteingUrlRepository;
	
	@Autowired
	private ShorteingUrlServiceImpl shorteingUrlService;
	
	@BeforeAll
	@Rollback(true)
	public void setUp() {
		shorteingUrlRepository.save(new ShorteingUrl(shortUrl,url));
	}
	
	@Test
	public void getShorteingUrlByUrl_Test() throws Exception{
		
		ShorteingUrl shorteingUrl = shorteingUrlService.getShorteingUrlByShortUrl(shortUrl);
		String getUrl = shorteingUrl.getUrl();
		
		assertThat(url,is(getUrl));
		
	}
	
	@Test
	public void getShorteingUrlByShortUrl_Test() throws Exception{
		
		ShorteingUrl shorteingUrl = shorteingUrlService.getShorteingUrlByUrl(url);
		String getShortUrl = shorteingUrl.getShortUrl();
		
		assertThat(shortUrl,is(getShortUrl));
		
	}
	
	@Test
	@Rollback(true)
	public void setShorteingUrl_Test() throws Exception{
		
		url = "http://www.kakaopay.com";
		ShorteingUrl shorteingUrl = shorteingUrlService.setShorteingUrl(url);
		String getUrl = shorteingUrl.getUrl();
		
		assertThat(url,is(getUrl));	
	}

}
