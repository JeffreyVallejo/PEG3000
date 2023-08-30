package com.ssinc.Nuzlocke.impl;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.ssinc.Nuzlocke.Exception.MyFirebaseException;
import com.ssinc.Nuzlocke.Exception.PokemonNotFoundException;
import com.ssinc.Nuzlocke.Exception.TrainerNotFoundException;
import com.ssinc.Nuzlocke.dao.PokemonCenterDao;
import com.ssinc.Nuzlocke.dataSource.AddTrainedPokemon;
import com.ssinc.Nuzlocke.dataSource.GetTrainedPokemon;
import com.ssinc.Nuzlocke.model.PokemonCenter;
import com.ssinc.Nuzlocke.model.TrainedPokemon;
import com.ssinc.Nuzlocke.NuzlockeUtils.PokemonStatusConst;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class PokemonCenterDaoImpl implements PokemonCenterDao {

    @Override
    public PokemonCenter get(String username) throws TrainerNotFoundException {
        try {
            List<QueryDocumentSnapshot> qds = GetTrainedPokemon.getTrainersPokemon(username);
            if(qds.size() == 0) {
                throw new TrainerNotFoundException("There was no trainer found by the id {" + username + "}.");
            }
            return toPokemonCenterModel(qds);
        } catch(MyFirebaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(TrainedPokemon pokemon) {
        AddTrainedPokemon.create(pokemon);
    }

    @Override
    public TrainedPokemon getSinglePokemon(String trainerId, String pokemonName) throws PokemonNotFoundException {
        try {
            QueryDocumentSnapshot ds = GetTrainedPokemon.getTrainedPokemon(trainerId, pokemonName);
            return ds.toObject(TrainedPokemon.class);

        } catch(MyFirebaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(TrainedPokemon trainedPokemon) throws PokemonNotFoundException {
        try {
            QueryDocumentSnapshot ds = GetTrainedPokemon.getTrainedPokemon(trainedPokemon.getTrainer(), trainedPokemon.getName());
            AddTrainedPokemon.update(ds, trainedPokemon);

        } catch (MyFirebaseException e) {
            throw new RuntimeException(e);
        }

    }

    public PokemonCenter getPokemonInParty(String trainer) {
        try {
             return toPokemonCenterModel(GetTrainedPokemon.getPokemonInParty(trainer, PokemonStatusConst.PARTY));
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }

    private PokemonCenter toPokemonCenterModel(List<QueryDocumentSnapshot> qds) {
        List<TrainedPokemon> pokemonList = new ArrayList<>();
        for (DocumentSnapshot ds : qds) {
            pokemonList.add(ds.toObject(TrainedPokemon.class));
        }
        return new PokemonCenter(pokemonList);
    }
}
