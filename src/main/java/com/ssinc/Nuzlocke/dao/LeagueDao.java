package com.ssinc.Nuzlocke.dao;

import com.ssinc.Nuzlocke.Exception.IllegalCreateException;
import com.ssinc.Nuzlocke.Exception.MyFirebaseException;
import com.ssinc.Nuzlocke.Exception.PokemonNotFoundException;
import com.ssinc.Nuzlocke.model.League;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface LeagueDao {
    void create(League league) throws IllegalCreateException, ExecutionException, InterruptedException;

    List<League> getAll(String ownerId);
    League getLeaguesByOwner(String ownerId) throws PokemonNotFoundException, MyFirebaseException;
}
