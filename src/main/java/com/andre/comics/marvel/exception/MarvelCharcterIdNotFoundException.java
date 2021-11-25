package com.andre.comics.marvel.exception;

public class MarvelCharcterIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3951938809582520929L;

	public MarvelCharcterIdNotFoundException(String exception) {
        super(exception);
    }

}
