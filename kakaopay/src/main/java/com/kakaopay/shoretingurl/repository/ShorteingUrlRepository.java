package com.kakaopay.shoretingurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.kakaopay.shoretingurl.domain.ShorteingUrl;

public interface ShorteingUrlRepository extends JpaRepository<ShorteingUrl, Long>  {
	
	ShorteingUrl findById(int id);
	
	ShorteingUrl findByUrl(String url);
	
	ShorteingUrl findByShortUrl(String shortUrl);

}
