package com.ssinc.Nuzlocke.service;

import com.ssinc.Nuzlocke.Helper.GenerateRandomEncounter;
import com.ssinc.Nuzlocke.dao.LocationDao;
import com.ssinc.Nuzlocke.dataSource.FirebaseHelper;
import com.ssinc.Nuzlocke.dataSource.GetLocationPokemon;
import com.ssinc.Nuzlocke.impl.WildPokemonDaoImpl;
import com.ssinc.Nuzlocke.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class LocationService {
    private static WildPokemonDaoImpl wildPokemon;

    /**
     * Initial driver for getting all information about a given location.
     *
     * @param location  Given location.
     */
    public String getRandomEncounter(String location) {
        //TODO: Use trainer DAO to get the version.
        String version = "";
        wildPokemon = new WildPokemonDaoImpl();
        GenerateRandomEncounter generateRandomEncounter = new GenerateRandomEncounter();

        return generateRandomEncounter.generateRandomEncounter(wildPokemon.getAllPokemonFromLocation(location), version);
    }
}
