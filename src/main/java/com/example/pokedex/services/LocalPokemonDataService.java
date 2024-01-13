package com.example.pokedex.services;

import com.example.pokedex.models.LocalPokemon;
import com.example.pokedex.models.Pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A service class for accessing Pokémon data from a local database.
 */
public class LocalPokemonDataService implements PokemonDataService {
    private String databaseUrl;

    /**
     * Constructs a LocalPokemonDataService with the specified database URL.
     *
     * @param databaseUrl The URL of the local database.
     */
    public LocalPokemonDataService(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    /**
     * This method is not used for the local database implementation.
     * You should use the getPokemonById(int id, String databaseFile) method instead.
     *
     * @param id The ID of the Pokémon to retrieve.
     * @return Always returns null.
     */
    @Override
    public Pokemon getPokemonById(int id) {
        return null;
    }

    /**
     * Retrieves a Pokémon by its ID from the local database.
     *
     * @param id           The ID of the Pokémon to retrieve.
     * @param databaseFile The path to the local database file.
     * @return The Pokémon object if found; otherwise, returns null.
     */
    @Override
    public Pokemon getPokemonById(int id, String databaseFile) {
        Connection conn = null;
        try {
            // Create a connection to the local database
            conn = DriverManager.getConnection("jdbc:sqlite:" + databaseFile);

            // Prepare the SQL query to fetch Pokémon data by ID
            String sql = "SELECT id, name, height, weight, description FROM pokemons WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Check if a matching Pokémon record was found
            if (rs.next()) {
                int pokemonId = rs.getInt("id");
                String name = rs.getString("name");
                double height = rs.getDouble("height");
                double weight = rs.getDouble("weight");
                String description = rs.getString("description");

                // Create a LocalPokemon object with the fetched data
                return new LocalPokemon(pokemonId, name, height, weight, description);
            }
        } catch (SQLException e) {
            System.err.println("Error accessing the local database: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing the database connection: " + e.getMessage());
            }
        }
        return null; // Return null if no Pokémon was found
    }

    // Other methods for additional data operations can be added here.
}
