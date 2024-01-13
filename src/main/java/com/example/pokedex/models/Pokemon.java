package com.example.pokedex.models;

public class Pokemon {
    private int id;
    private String name;
    private double height;
    private double weight;

    public Pokemon() {
        // Default constructor
    }

    public Pokemon(int id, String name, double height, double weight) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Pokemon #" + id + "\n" +
                "Name: " + name + "\n" +
                "Height: " + height + "\n" +
                "Weight: " + weight;
    }
}
