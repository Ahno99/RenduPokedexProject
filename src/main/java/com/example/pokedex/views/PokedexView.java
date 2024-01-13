package com.example.pokedex.views;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.LocalPokemon;
import com.example.pokedex.utilities.MultipleFormatGenerator;

import java.nio.charset.StandardCharsets;

/**
 * A view class responsible for generating different formats of output for Pokémon data.
 */
public class PokedexView implements MultipleFormatGenerator {

    /**
     * Generates human-readable text format output for the provided Pokémon data.
     *
     * @param pokemon The Pokémon object for which human-readable text output is generated.
     */
    @Override
    public void generateHumanReadableText(Pokemon pokemon) {
        System.out.println("=============================");
        System.out.println("Pokémon #" + pokemon.getId());
        System.out.println("Name: " + new String(pokemon.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        System.out.println("Height: " + pokemon.getHeight());
        System.out.println("Weight: " + pokemon.getWeight());

        if (pokemon instanceof LocalPokemon) {
            LocalPokemon localPokemon = (LocalPokemon) pokemon;
            System.out.println("Description: " + localPokemon.getDescription());
        }
        System.out.println("=============================");
    }

    /**
     * Generates HTML format output for the provided Pokémon data.
     *
     * @param pokemon The Pokémon object for which HTML output is generated.
     */
    @Override
    public void generateHTML(Pokemon pokemon) {
        System.out.println("<h1>" + new String(pokemon.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8) + "</h1>");
        System.out.println("<ul>");
        System.out.println("<li>Id: " + pokemon.getId() + "</li>");
        System.out.println("<li>Height: " + pokemon.getHeight() + "</li>");
        System.out.println("<li>Weight: " + pokemon.getWeight() + "</li>");

        if (pokemon instanceof LocalPokemon) {
            LocalPokemon localPokemon = (LocalPokemon) pokemon;
            System.out.println("<li>Description: " + localPokemon.getDescription() + "</li>");
        }

        System.out.println("</ul>");
    }

    /**
     * Generates CSV format output for the provided Pokémon data.
     *
     * @param pokemon The Pokémon object for which CSV output is generated.
     */
    @Override
    public void generateCSV(Pokemon pokemon) {
        System.out.print("Id;Name;Height;Weight;Description;\n");
        System.out.print(pokemon.getId() + ";");
        System.out.print("\"" + new String(pokemon.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8) + "\";");
        System.out.print(pokemon.getHeight() + ";");
        System.out.print(pokemon.getWeight() + ";");

        if (pokemon instanceof LocalPokemon) {
            LocalPokemon localPokemon = (LocalPokemon) pokemon;
            System.out.print("\"" + localPokemon.getDescription() + "\";");
        } else {
            System.out.print(";");
        }

        System.out.println();
    }
}
