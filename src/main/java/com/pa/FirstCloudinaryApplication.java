package com.pa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
public class FirstCloudinaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstCloudinaryApplication.class, args);
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}
	
	@Bean
	public Cloudinary cloudinary() {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dmpp9gnpa",
				"api_key", "179783166464825",
				"api_secret", "DyySrRpcsbI1jvrtic8rwOJ_oW0",
				"secure",true));
		return cloudinary;
	}
}
