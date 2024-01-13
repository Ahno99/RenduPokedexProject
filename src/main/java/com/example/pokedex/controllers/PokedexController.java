package com.example.pokedex.controllers;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.services.PokemonDataService;
import com.example.pokedex.views.PokedexView;
import com.example.pokedex.utilities.OutputFormat;

/**
 * The controller class responsible for managing and displaying Pokémon information.
 */
public class PokedexController {
    private PokemonDataService dataService;
    private PokedexView pokedexView;

    /**
     * Constructs a new PokedexController with the provided data service and view.
     *
     * @param dataService The data service for fetching Pokémon data.
     * @param pokedexView The view for displaying Pokémon information.
     */
    public PokedexController(PokemonDataService dataService, PokedexView pokedexView) {
        this.dataService = dataService;
        this.pokedexView = pokedexView;
    }

    /**
     * Displays Pokémon information based on the specified Pokémon ID, database file, and output format.
     *
     * @param id           The ID of the Pokémon to retrieve and display.
     * @param databaseFile The path to the database file (if applicable, null or empty if not using a local database).
     * @param outputFormat The desired output format (text, HTML, or CSV).
     */
    public void displayPokemonInfo(int id, String databaseFile, OutputFormat outputFormat) {
        if (databaseFile != null && !databaseFile.isEmpty()) {
            // Use LocalPokemonDataService to fetch data from the local database
            Pokemon pokemon = dataService.getPokemonById(id, databaseFile);
            if (pokemon != null) {
                // Check the output format and use the appropriate method from the view
                if (outputFormat == OutputFormat.HTML) {
                    pokedexView.generateHTML(pokemon);
                } else if (outputFormat == OutputFormat.CSV) {
                    pokedexView.generateCSV(pokemon);
                } else {
                    pokedexView.generateHumanReadableText(pokemon);
                }
            } else {
                System.err.println("Error: Failed to fetch Pokémon data from the local database.");
            }
        } else {
            // Use the existing data service to fetch data from the API
            Pokemon pokemon = dataService.getPokemonById(id);
            if (pokemon != null) {
                // Check the output format and use the appropriate method from the view
                if (outputFormat == OutputFormat.HTML) {
                    pokedexView.generateHTML(pokemon);
                } else if (outputFormat == OutputFormat.CSV) {
                    pokedexView.generateCSV(pokemon);
                } else {
                    pokedexView.generateHumanReadableText(pokemon);
                }
            } else {
                System.err.println("Error: Failed to fetch Pokémon data from the API.");
            }
        }
    }
}
