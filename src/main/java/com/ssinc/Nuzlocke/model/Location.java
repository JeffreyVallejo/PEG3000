package com.ssinc.Nuzlocke.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Location {
    public String name;
    public List<WildPokemon> pokemonList;

    public Location(@JsonProperty("name") String name,
                    @JsonProperty("pokemon") List<WildPokemon> pokemonList) {
        this.name = name;
        this.pokemonList = pokemonList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WildPokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<WildPokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
