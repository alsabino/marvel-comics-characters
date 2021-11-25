package com.andre.comics.marvel.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.andre.comics.marvel.model.MarvelCharacter;
import com.andre.comics.marvel.service.MarvelCharacterService;

@RestController
public class MarvelCharactersController {

	@Autowired
	private MarvelCharacterService marvelCharacterService;

	@GetMapping("/characters")
	@ResponseBody
	public Set<Long> findAllCharacters() {
		return marvelCharacterService.getMarvelCharactersIdSet();
	}

	@GetMapping(value = "/characters/{characterId}")
	@ResponseBody
	public MarvelCharacter getCharacter(@PathVariable Long characterId,
			@RequestParam(required = false, defaultValue = "en") String language) {
		return marvelCharacterService.getMarvelCharacterWithTranslation(characterId, language);
	}
}
