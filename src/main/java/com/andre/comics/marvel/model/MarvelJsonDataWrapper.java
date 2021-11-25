package com.andre.comics.marvel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelJsonDataWrapper {

    @JsonProperty("data")
    public MarvelCharacterControlData charData;
    
}
