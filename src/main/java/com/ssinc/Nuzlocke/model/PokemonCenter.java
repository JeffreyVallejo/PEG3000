package com.ssinc.Nuzlocke.model;

import com.ssinc.Nuzlocke.NuzlockeUtils.PokemonStatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PokemonCenter {

    private List<TrainedPokemon> trainedPokemonList;
    private List<TrainedPokemon> box;

    private List<TrainedPokemon> grave;

    private List<TrainedPokemon> party;

    public PokemonCenter(List<TrainedPokemon> pokemonList) {
        setTrainedPokemon(pokemonList);
        this.box = new ArrayList<>();
        this.grave = new ArrayList<>();
        this.party = new ArrayList<>();
        buildPc();
    }

    public void addParty(TrainedPokemon pokemon) {
        if(this.party.size() <= 6) {
            this.party.add(pokemon);
        } else {
            throw new IndexOutOfBoundsException("Too many pokemon were added to the Party.");
        }
    }

    private void buildPc() {
        for (TrainedPokemon pokemon: trainedPokemonList) {
            switch(pokemon.getStatus()) {
                case PokemonStatusConst.PARTY -> addParty(pokemon);
                case PokemonStatusConst.BOXED -> addBoxed(pokemon);
                case PokemonStatusConst.GRAVE -> addGrave(pokemon);
            }
        }
    }

    public void addBoxed(TrainedPokemon pokemon) {
        this.box.add(pokemon);
    }

    public void addGrave(TrainedPokemon pokemon) {
        this.grave.add(pokemon);
    }

    public List<TrainedPokemon> getBox() {
        return box;
    }

    public void setBox(List<TrainedPokemon> box) {
        this.box = box;
    }

    public List<TrainedPokemon> getGrave() {
        return grave;
    }

    public void setGrave(List<TrainedPokemon> grave) {
        this.grave = grave;
    }

    public List<TrainedPokemon> getParty() {
        return party;
    }

    public void setParty(List<TrainedPokemon> party) {
        this.party = party;
    }

    public void setTrainedPokemon(List<TrainedPokemon> pokemonList) { this.trainedPokemonList = pokemonList;}

    public boolean partyFull() {
        return this.party.size() >= 6;
    }
}
