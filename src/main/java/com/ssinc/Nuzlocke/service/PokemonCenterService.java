package com.ssinc.Nuzlocke.service;

import com.ssinc.Nuzlocke.Exception.*;
import com.ssinc.Nuzlocke.NuzlockeUtils.PokemonStatusConst;
import com.ssinc.Nuzlocke.impl.PokemonCenterDaoImpl;
import com.ssinc.Nuzlocke.model.PokemonCenter;
import com.ssinc.Nuzlocke.model.TrainedPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonCenterService {

    @Autowired
    PokemonCenterDaoImpl pokemonCenterDao;

    public PokemonCenter getAllTrainedPokemon(String username) throws TrainerNotFoundException {
        return pokemonCenterDao.get(username);
    }

    public void addPokemonToPokemonCenter(TrainedPokemon pokemon) throws PartyFullException {
        // Check the intended destination of the pokemon.
        switch (pokemon.getStatus()) {
            case PokemonStatusConst.PARTY -> {
                // Get the pokemon that are currently in the party.
                PokemonCenter pc = pokemonCenterDao.getPokemonInParty(pokemon.getTrainer());
                // Check if the party is full.
                if(!pc.partyFull()) {
                    pokemonCenterDao.add(pokemon);
                    return;
                }
                // If the party is full we will default and put it in the Box
                pokemon.setStatus(PokemonStatusConst.BOXED);
                pokemonCenterDao.add(pokemon);
                throw new PartyFullException("Your party is full, the pokemon was added to the Box");
            }

            case PokemonStatusConst.BOXED -> pokemonCenterDao.add(pokemon);
        }
    }

    public void movePokemonTo(String trainerId, String pokemonName, String status) throws PokemonNotFoundException, PartyFullException, InvalidPcStatusException {
        if(!PokemonStatusConst.isValidStatus(status)) {
            throw new InvalidPcStatusException("The status (" + status + ") is not a valid status.");
        }

        // Get the pokemon to move from the DAO.
        TrainedPokemon trainedPokemon = pokemonCenterDao.getSinglePokemon(trainerId, pokemonName);
        trainedPokemon.setStatus(status);

        if (status.equals(PokemonStatusConst.PARTY)) {
            PokemonCenter pc = pokemonCenterDao.getPokemonInParty(trainerId);
            if (pc.partyFull()) {
                throw new PartyFullException("PARTY FULL! \nPokemon could not be moved to the party and is placed back into the box.");
            }
            pokemonCenterDao.update(trainedPokemon);
        } else {
            pokemonCenterDao.update(trainedPokemon);
        }
    }
}
