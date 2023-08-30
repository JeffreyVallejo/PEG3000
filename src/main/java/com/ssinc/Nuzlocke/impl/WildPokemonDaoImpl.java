package com.ssinc.Nuzlocke.impl;

import com.ssinc.Nuzlocke.dao.WildPokemonDao;
import com.ssinc.Nuzlocke.dataSource.GetLocationPokemon;
import com.ssinc.Nuzlocke.model.WildPokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WildPokemonDaoImpl implements WildPokemonDao {

    private List<WildPokemon> wildPokemon = new ArrayList<>();

    @Override
    public List<WildPokemon> getAllPokemonFromLocation(String location) {
        try{
            wildPokemon = GetLocationPokemon.getPokemonByLocation(location);
            return wildPokemon;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
