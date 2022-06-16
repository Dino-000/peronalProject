package com.axonactive.personalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
public class DinoProjectApplication extends SpringServletContainerInitializer {


	protected SpringApplicationBuilder configure(SpringApplicationBuilder app){
		return app.sources(DinoProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DinoProjectApplication.class, args);
		String encryptedPassWord = new BCryptPasswordEncoder().encode("1234");
		System.out.println(encryptedPassWord);
	}

}
