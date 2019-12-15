package com.kakaopay.shoretingurl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.kakaopay.shoretingurl.KakaopayApplication;
import com.kakaopay.shoretingurl.domain.ShorteingUrl;
import com.kakaopay.shoretingurl.repository.ShorteingUrlRepository;
import com.kakaopay.shoretingurl.service.impl.ShorteingUrlServiceImpl;

@SpringBootTest
@ContextConfiguration(classes={KakaopayApplication.class})
@AutoConfigureMockMvc
class ShorteingUrlControllerTest {
	
	@Autowired
	private ShorteingUrlRepository shorteingUrlRepository;

	@Autowired
	private ShorteingUrlServiceImpl shorteingUrlService;
	 
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void indexTest() throws Exception{
		
		this.mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(view().name("index"));
		
	}

	@Test
	public void getErrorShortUrlTest() throws Exception{
		
		String url ="testurl";
		
		this.mockMvc.perform(get("/"+url))
		 	.andDo(print())
		 	.andExpect(status().is(HttpStatus.OK.value()))
		 	.andExpect(view().name("index"))
		 	.andExpect(model().attributeExists("msg"))
		 	.andExpect(model().attribute("msg","유효하지 않습니다. 변경하실 URL을 입력하여 주세요"));
		
	}	
	
	@Test
	public void setShortUrlTest() throws Exception{
		
		String url ="https://www.kakaopay.com";
		
		this.mockMvc.perform(post("/setShortUrl").param("url",url))
					.andDo(print())
					.andExpect(status().is(HttpStatus.OK.value()))
					.andExpect(view().name("index"))
					.andExpect(model().attributeExists("msg"))
					.andExpect(model().attribute("msg","url : "+shorteingUrlService.getShorteingUrlByUrl(url).getShortUrl()));
	
	}
	
	@Test
	public void redirectShortUrlTest() throws Exception{
		
		String url ="https://www.kakaopay.com";
		String shortUrl ="gR9ahgdE";
		
		shorteingUrlRepository.save(new ShorteingUrl(shortUrl,url));
	
		this.mockMvc.perform(get("/"+shortUrl))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl(url));

	}
	
}