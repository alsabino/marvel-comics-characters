package com.andre.comics.marvel.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.andre.comics.marvel.model.MarvelCharacter;
import com.andre.comics.marvel.model.MarvelJsonDataWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@PropertySource("file:application.yml")
public class CharactersAPI {

	@Value("${marvel.domain}")
	private String marvelDomain;
	@Value("${marvel.charactersPath}")
	private String marvelCharactersPath;
	@Value("${user.publicKey}")
	private String publicKey;
	@Value("${user.privateKey}")
	private String privateKey;

	/**
	 * Find all IDs from Marvel API
	 * 
	 * @return Set<Long> marvelCharacterIDs
	 */
	public Set<Long> resquestIdsFromAPI() {
		Set<Long> marvelCharacterIDs = new HashSet<>();
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			long offset = 0;
			long total = 1;
			
			interateCharactersIDs(marvelCharacterIDs, client, offset, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marvelCharacterIDs;
	}

	private void interateCharactersIDs(Set<Long> marvelCharacterIDs, CloseableHttpClient client, long offset,
			long total) throws URISyntaxException, IOException {
		while (offset < total) {
			String ts = Long.toString(System.currentTimeMillis());
			URI uri = new URIBuilder(marvelDomain + marvelCharactersPath).addParameter("limit", "100")
					.addParameter("offset", Long.toString(offset)).addParameter("apikey", publicKey)
					.addParameter("ts", ts).addParameter("hash", DigestUtils.md5Hex(ts + privateKey + publicKey))
					.build();
			HttpGet request = new HttpGet(uri);

			try (CloseableHttpResponse response = client.execute(request)) {
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new HttpResponseException(response.getStatusLine().getStatusCode(),
							"Connection error code " + response.getStatusLine().getStatusCode() + ".");
				}
				HttpEntity entity = response.getEntity();
				if (entity == null) {
					throw new NullPointerException("Response entity is null:\n " + response.toString());
				}
				String result = EntityUtils.toString(entity);

				ObjectMapper mapper = new ObjectMapper();
				MarvelJsonDataWrapper jsonWrapper = mapper.readValue(result, MarvelJsonDataWrapper.class);

				total = jsonWrapper.getCharData().getTotal();
				offset += jsonWrapper.getCharData().getCount();
				jsonWrapper.getCharData().getResults().forEach(character -> marvelCharacterIDs.add(character.getId()));
			} catch (ClientProtocolException e) {
				throw new ClientProtocolException(e.getMessage());
			}
		}
	}

	/**
	 * Get a Character by ID
	 * 
	 * @param id
	 * @return MarvelCharacter
	 */
	public MarvelCharacter getCharacterFromAPI(long id) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			String ts = Long.toString(System.currentTimeMillis());
			URI uri = new URIBuilder(marvelDomain + marvelCharactersPath + "/" + id).addParameter("apikey", publicKey)
					.addParameter("ts", ts).addParameter("hash", DigestUtils.md5Hex(ts + privateKey + publicKey))
					.build();
			HttpGet request = new HttpGet(uri);

			try (CloseableHttpResponse response = client.execute(request)) {
				HttpEntity entity = response.getEntity();
				if (entity == null) {
					throw new RuntimeException("Response entity is null. Response: [" + response.toString() + "].");
				}
				String result = EntityUtils.toString(entity);

				// response entity expected to be JSON formatted to /data objects
				ObjectMapper mapper = new ObjectMapper();
				MarvelJsonDataWrapper characterJsonDataCompiler = mapper.readValue(result, MarvelJsonDataWrapper.class);

				return characterJsonDataCompiler.getCharData().getResults().get(0);
			}
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException("Failed when querying Marvel API.", e);
		}
	}
}