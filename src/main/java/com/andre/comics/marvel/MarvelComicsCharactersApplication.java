package com.andre.comics.marvel;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andre.comics.marvel.model.MarvelCharacterIds;
import com.andre.comics.marvel.service.CharactersAPI;

@SpringBootApplication
public class MarvelComicsCharactersApplication {
	
	@Autowired
	CharactersAPI charactersRequestAPI ;
	
	@Autowired
	private MarvelCharacterIds charIds;

	public static void main(String[] args) {
		SpringApplication.run(MarvelComicsCharactersApplication.class, args);
	}
	
	@PostConstruct
	public void loadAllCharacterIds() {
		charIds.setIds(charactersRequestAPI.resquestIdsFromAPI());
	}
}
