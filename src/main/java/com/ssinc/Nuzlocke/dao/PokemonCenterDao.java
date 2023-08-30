package com.ssinc.Nuzlocke.dao;

import com.ssinc.Nuzlocke.Exception.MyFirebaseException;
import com.ssinc.Nuzlocke.Exception.PokemonNotFoundException;
import com.ssinc.Nuzlocke.Exception.TrainerNotFoundException;
import com.ssinc.Nuzlocke.model.PokemonCenter;
import com.ssinc.Nuzlocke.model.TrainedPokemon;

import java.util.List;

public interface PokemonCenterDao {
    PokemonCenter get(String username) throws TrainerNotFoundException;

    void add(TrainedPokemon pokemon);

    TrainedPokemon getSinglePokemon(String trainerId, String pokemonName) throws PokemonNotFoundException;

    void update(TrainedPokemon trainedPokemon) throws PokemonNotFoundException, MyFirebaseException;
}
