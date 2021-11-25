package com.andre.comics.marvel.utils;

import org.springframework.beans.factory.annotation.Value;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;

public class Translator {
	private static final String VERSION = "2018-05-01";
	@Value("${ibm.apiKey}")
	private String apiKey;
	@Value("${ibm.serviceUrl}")
	private String serviceUrl;

	public String translate(String langTo, String text) {
		if (text.isEmpty() || langTo.equals(Language.ENGLISH)) {
			return text;
		} else {
			Authenticator authenticator = new IamAuthenticator.Builder().apikey(apiKey).build();
			LanguageTranslator service = new LanguageTranslator(VERSION, authenticator);
			service.setServiceUrl(serviceUrl);
			TranslateOptions translateOptions = new TranslateOptions.Builder().addText(text)
					.target(langTo.isEmpty() ? Language.PORTUGUESE : langTo).build();
			TranslationResult translationResult = service.translate(translateOptions).execute().getResult();

			return translationResult.getTranslations().get(0).getTranslation();

		}
	}

}