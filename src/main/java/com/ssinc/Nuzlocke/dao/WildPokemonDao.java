package com.ssinc.Nuzlocke.dao;

import com.ssinc.Nuzlocke.model.WildPokemon;

import java.util.List;

/**
 * This interface will handle all the data access for Pokémon from the database.
 */
public interface WildPokemonDao {

     List<WildPokemon> getAllPokemonFromLocation(String location);
}
