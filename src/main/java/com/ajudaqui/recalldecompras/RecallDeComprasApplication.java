package com.ajudaqui.recalldecompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RecallDeComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecallDeComprasApplication.class, args);
		
		
	}

}
