package com.eschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AjapaWebserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AjapaWebserviceApplication.class, args);
	}
	//Code To resolve CORS issue for all methods and URLS
	@Bean
	public WebMvcConfigurer corsConfigurer() {		
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
			}
		};		
	}
}
