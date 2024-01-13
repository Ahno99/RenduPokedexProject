package com.example.pokedex.services;

import com.example.pokedex.models.Pokemon;

/**
 * A service interface for retrieving Pokémon data.
 */
public interface PokemonDataService {

    /**
     * Retrieves a Pokémon by its ID.
     *
     * @param id The ID of the Pokémon to retrieve.
     * @return The Pokémon object if found; otherwise, returns null.
     */
    Pokemon getPokemonById(int id);

    /**
     * Retrieves a Pokémon by its ID from a local database.
     *
     * @param id           The ID of the Pokémon to retrieve.
     * @param databaseFile The path to the local database file.
     * @return The Pokémon object if found; otherwise, returns null.
     */
    Pokemon getPokemonById(int id, String databaseFile);
}
