package com.andre.comics.marvel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.andre.comics.marvel.model.MarvelCharacterIds;
import com.andre.comics.marvel.service.CharactersAPI;
import com.andre.comics.marvel.service.MarvelCharacterService;
import com.andre.comics.marvel.utils.Translator;

@Component
@PropertySource("classpath:application.yml")
public class MarvelAppConfig {

	@Bean
	@Scope("singleton")
	MarvelCharacterIds getIds() {
		return new MarvelCharacterIds();
	}

	@Bean
	CharactersAPI geCharactersRequestAPI() {
		return new CharactersAPI();
	}

	@Bean
	MarvelCharacterService getService() {
		return new MarvelCharacterService();
	}

	@Bean
	Translator getTranslator() {
		return new Translator();
	}

}
