package com.example.pokedex.models;

public class LocalPokemon extends Pokemon {
    private String description;

    public LocalPokemon(int id, String name, double height, double weight, String description) {
        super(id, name, height, weight);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
