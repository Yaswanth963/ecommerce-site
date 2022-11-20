package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
/**
 * 
 * @author yaswanth.perumalla
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.ecommerce.*" })
@OpenAPIDefinition(info = @Info(title = "API doc for e-commerce site", version = "v1.0", description = "These are the custom api's for e-commerce site"))
public class EcommerceSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSiteApplication.class, args);
	}

}
