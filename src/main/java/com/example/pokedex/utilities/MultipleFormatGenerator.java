package com.example.pokedex.utilities;

import com.example.pokedex.models.Pokemon;

/**
 * An interface for generating multiple formats of output for Pokémon data.
 */
public interface MultipleFormatGenerator {

    /**
     * Generates HTML format output for the provided Pokémon data.
     *
     * @param pokemon The Pokémon object for which HTML output is generated.
     */
    void generateHTML(Pokemon pokemon);

    /**
     * Generates CSV format output for the provided Pokémon data.
     *
     * @param pokemon The Pokémon object for which CSV output is generated.
     */
    void generateCSV(Pokemon pokemon);

    /**
     * Generates human-readable text format output for the provided Pokémon data.
     *
     * @param pokemon The Pokémon object for which human-readable text output is generated.
     */
    void generateHumanReadableText(Pokemon pokemon);
}
