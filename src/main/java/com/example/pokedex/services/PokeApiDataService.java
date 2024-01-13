package com.example.pokedex.services;

import com.example.pokedex.models.Pokemon;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * A service class for fetching Pokémon data from the PokeAPI.
 */
public class PokeApiDataService implements PokemonDataService {
    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    /**
     * Fetches Pokémon data by ID from the PokeAPI.
     *
     * @param id The ID of the Pokémon to retrieve.
     * @return The Pokémon object if found; otherwise, returns null.
     */
    @Override
    public Pokemon getPokemonById(int id) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(API_BASE_URL + id);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);

            if (result.getStatusLine().getStatusCode() == 200) {
                String jsonResponse = EntityUtils.toString(result.getEntity(), "UTF-8");
                return parsePokemonFromJson(jsonResponse);
            } else {
                System.err.println("Error: Failed to fetch Pokemon data from the API");
                return null;
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error: An error occurred while fetching or parsing Pokemon data");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method is not used in PokeApiDataService and is an empty implementation.
     *
     * @param id           The ID of the Pokémon to retrieve (not used).
     * @param databaseFile The path to the local database file (not used).
     * @return Always returns null.
     */
    @Override
    public Pokemon getPokemonById(int id, String databaseFile) {
        // This is an empty implementation since we don't use it in PokeApiDataService
        return null;
    }

    /**
     * Parses Pokémon data from a JSON string obtained from the PokeAPI response.
     *
     * @param json The JSON string containing Pokémon data.
     * @return The parsed Pokémon object.
     * @throws ParseException If there is an error parsing the JSON data.
     */
    private Pokemon parsePokemonFromJson(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);

        int id = ((Number) jsonObject.get("id")).intValue();
        String name = (String) jsonObject.get("name");
        double height = ((Number) jsonObject.get("height")).doubleValue();
        double weight = ((Number) jsonObject.get("weight")).doubleValue();

        return new Pokemon(id, name, height, weight);
    }
}
