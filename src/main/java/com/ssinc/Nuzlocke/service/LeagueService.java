package com.ssinc.Nuzlocke.service;

import com.ssinc.Nuzlocke.Exception.MyFirebaseException;
import com.ssinc.Nuzlocke.impl.LeagueDaoImpl;
import com.ssinc.Nuzlocke.model.League;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LeagueService {

    League league;
    LeagueDaoImpl leagueDaoImpl;

    public League createNewLeague(String displayName, String ownerId, String secureId) {
        league = new League(displayName, ownerId, secureId);
        leagueDaoImpl = new LeagueDaoImpl();
        leagueDaoImpl.create(league);

        return league;
    }

    public List<League> getAllLeaguesByOwnerId(String ownerId) {
        leagueDaoImpl = new LeagueDaoImpl();
        return leagueDaoImpl.getAll(ownerId);
    }

    public League getLeaguesByOwnerId(String ownerId) {
        leagueDaoImpl = new LeagueDaoImpl();
        return leagueDaoImpl.getLeaguesByOwner(ownerId);
    }
}
