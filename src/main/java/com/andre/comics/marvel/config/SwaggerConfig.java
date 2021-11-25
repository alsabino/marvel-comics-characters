package com.andre.comics.marvel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.andre.comics.marvel"))
				.paths(PathSelectors.any()).build().useDefaultResponseMessages(false)
				.apiInfo(apiInfo());
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Marvel Characters API").description(
				"The Marvel Comics API allows developers to access information about Marvel's vast library of comics.\n"
				+ "This Character API allow us to access this data and check specific characters informations, including their description.")
				.version("0.0.1-SNAPSHOT").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("Andre Luis Sabino", "https://github.com/alsabino", "andrelsab@outlook.com"))
				.build();
	}
}
