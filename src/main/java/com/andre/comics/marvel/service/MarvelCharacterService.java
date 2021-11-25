package com.andre.comics.marvel.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andre.comics.marvel.exception.MarvelCharcterIdNotFoundException;
import com.andre.comics.marvel.model.MarvelCharacter;
import com.andre.comics.marvel.model.MarvelCharacterIds;
import com.andre.comics.marvel.utils.Translator;

@Component
public class MarvelCharacterService {

	@Autowired
	private Translator translator;

	@Autowired
	private MarvelCharacterIds marvelCharacterIDs;

	@Autowired
	private CharactersAPI marvelAPIConnector;

	public Set<Long> getMarvelCharactersIdSet() {
		return marvelCharacterIDs.getIds();
	}

	public MarvelCharacter getMarvelCharacterWithTranslation(long characterId, String languageCode) {
		if (!marvelCharacterIDs.getIds().contains(characterId)) {
			throw new MarvelCharcterIdNotFoundException("Character id not found: " + characterId);
		}
		MarvelCharacter marvelCharacter = marvelAPIConnector.getCharacterFromAPI(characterId);

		marvelCharacter.setDescription(translator.translate(languageCode, marvelCharacter.getDescription()));
		return marvelCharacter;
	}
}
