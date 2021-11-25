package com.andre.comics.marvel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelCharacter {

	@JsonProperty("id")
	public long id;

	@JsonProperty("name")
	public String name;

	@JsonProperty("description")
	public String description;

	@JsonProperty("thumbnail")
	public MarvelCharacterThumbnail thumbnail;

}
