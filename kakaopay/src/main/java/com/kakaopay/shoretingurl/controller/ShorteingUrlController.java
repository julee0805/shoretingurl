package com.kakaopay.shoretingurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.validator.routines.UrlValidator;
import com.kakaopay.shoretingurl.domain.ShorteingUrl;
import com.kakaopay.shoretingurl.service.impl.ShorteingUrlServiceImpl;


@Controller
public class ShorteingUrlController {

	private final ShorteingUrlServiceImpl shorteingUrlService;
	
	public ShorteingUrlController(ShorteingUrlServiceImpl shorteingUrlService) {
		this.shorteingUrlService = shorteingUrlService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){	
    	model.addAttribute("msg", "변경하실 URL을 입력하여 주세요");
        return "index";
    }
    
	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    public String getShortUrl(@PathVariable("shortUrl")String shortUrl, Model model) {

    	String returnUrl = "index";

    	ShorteingUrl shorteingUrl = shorteingUrlService.getShorteingUrlByShortUrl(shortUrl);

    	if(ObjectUtils.isEmpty(shorteingUrl)) {
        	model.addAttribute("msg", "유효하지 않습니다. 변경하실 URL을 입력하여 주세요");
    	}else{
    		returnUrl="redirect:"+shorteingUrl.getUrl();
    	}

    	return returnUrl;
    }
 
    @RequestMapping(value="/setShortUrl", method=RequestMethod.POST)
    public String setShortUrl(@RequestParam("url")String url, Model model) {
    	try {
    		UrlValidator urlValidator = new UrlValidator();
    	
    		ShorteingUrl shorteingUrl = shorteingUrlService.getShorteingUrlByUrl(url);
    			
    		if(urlValidator.isValid(url)) {
    			if(ObjectUtils.isEmpty(shorteingUrl)) shorteingUrl = shorteingUrlService.setShorteingUrl(url);
        			model.addAttribute("msg","url : " + shorteingUrl.getShortUrl());
    		}else {
    			model.addAttribute("msg","유효하지 않은 주소입니다. 다시 확인해주세요  ex) https://www.daum.net");
    		}

    	}catch(Exception e){
    		model.addAttribute("msg",e.getMessage().toString());
    	}
        
    	return "index";
    	
    }

}