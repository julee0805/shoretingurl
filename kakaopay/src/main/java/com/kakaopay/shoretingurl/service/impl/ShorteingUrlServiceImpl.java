package com.kakaopay.shoretingurl.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import com.kakaopay.shoretingurl.domain.ShorteingUrl;
import com.kakaopay.shoretingurl.repository.ShorteingUrlRepository;
import com.kakaopay.shoretingurl.service.ShorteingUrlService;
import com.kakaopay.shoretingurl.util.Base62;


@Service
public class ShorteingUrlServiceImpl implements ShorteingUrlService {
	
	private final ShorteingUrlRepository shorteingUrlRepository;
	
	public ShorteingUrlServiceImpl(ShorteingUrlRepository shorteingUrlRepository) {
		this.shorteingUrlRepository = shorteingUrlRepository;
	}

	@Override
	public ShorteingUrl getShorteingUrlByUrl(String url) {
		
		ShorteingUrl shorteingUrl = shorteingUrlRepository.findByUrl(url);

		return shorteingUrl;
	}

	@Override
	public ShorteingUrl getShorteingUrlByShortUrl(String shortUrl) {
		
		ShorteingUrl shorteingUrl = shorteingUrlRepository.findByShortUrl(shortUrl);

		return shorteingUrl;
	}

	@Override
	@Transactional
	public ShorteingUrl setShorteingUrl(String url) throws Exception {	
				
		String shortUrl =  getShorteingUrl();
		
		ShorteingUrl shorteingUrl = shorteingUrlRepository.findByUrl(url);
		
		if(ObjectUtils.isEmpty(shorteingUrl)){
			shorteingUrlRepository.save(new ShorteingUrl(shortUrl,url));
			shorteingUrl = shorteingUrlRepository.findByUrl(url);
		}
				
		return shorteingUrl;
	}

	@Override
	public String getShorteingUrl() throws Exception {
		
		long shortUrl;
		long date = System.currentTimeMillis()*10;
		long ranNum = (int)(Math.random()*9)+1;
		
		String returnStr;

		do{		
			date = System.currentTimeMillis()*10;
			ranNum = (int)(Math.random()*9)+1;
			shortUrl = date+ranNum;
			returnStr = Base62.encodeToLong(shortUrl);
		
		}while(!ObjectUtils.isEmpty(shorteingUrlRepository.findByShortUrl(returnStr)));
		
		return returnStr;
	}
	

}