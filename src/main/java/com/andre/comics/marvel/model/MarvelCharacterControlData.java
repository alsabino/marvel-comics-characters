package com.andre.comics.marvel.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelCharacterControlData {
	@JsonProperty("results")
	public List<MarvelCharacter> results;

	@JsonProperty("count")
	public long count;

	@JsonProperty("total")
	public long total;

}
