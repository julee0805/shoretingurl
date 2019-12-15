package com.kakaopay.shoretingurl.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.kakaopay.shoretingurl.KakaopayApplication;

@SpringBootTest
@ContextConfiguration(classes={KakaopayApplication.class})
class Base62Test {
	
	private long time = 1576397251240L;
	private String base62Str="wWN4rub";
	
	@Test
	public void encodeTest() {
		
		String encodStr = Base62.encodeToLong(time);	
		
		assertThat(base62Str , is(encodStr));
		
	}
	
	@Test
	public void decodeTest() {
		
		long decodeTime = Base62.decodeToLong(base62Str);	
		
		assertThat(time , is(decodeTime));
		
	}

}
