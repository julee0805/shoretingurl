package com.kakaopay.shoretingurl.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.shoretingurl.KakaopayApplication;
import com.kakaopay.shoretingurl.domain.ShorteingUrl;

@SpringBootTest
@ContextConfiguration(classes={KakaopayApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ShorteingUrlRepositoryTest {

	private String url ="http://www.kakaopay.com";
	private String shortUrl ="wWN4rub";
	
	@Autowired
	private ShorteingUrlRepository shorteingUrlRepository;
	
	public ShorteingUrl shorteingUrl;
	public ShorteingUrl createShorteingUrl;

	@BeforeAll
	public void setUp() {
		shorteingUrl = new ShorteingUrl(shortUrl,url);
		createShorteingUrl = shorteingUrlRepository.save(shorteingUrl);
	}
	
	@Test
	public void saveShorteingUrl_Test() throws Exception{
		
		assertThat(createShorteingUrl , is(shorteingUrl));
		
	}
	
	@Test
	public void getShorteingUrl_Test() throws Exception{
			
		assertThat(createShorteingUrl.getShortUrl(),is(shortUrl));
		assertThat(createShorteingUrl.getUrl(),is(url));
	}
	
}
