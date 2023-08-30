package com.ssinc.Nuzlocke.impl;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.ssinc.Nuzlocke.Exception.InternalDbException;
import com.ssinc.Nuzlocke.Exception.LeagueNotFoundException;
import com.ssinc.Nuzlocke.Exception.MyFirebaseException;
import com.ssinc.Nuzlocke.dao.LeagueDao;
import com.ssinc.Nuzlocke.dataSource.CreateLeague;
import com.ssinc.Nuzlocke.dataSource.GetLeague;
import com.ssinc.Nuzlocke.model.League;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LeagueDaoImpl implements LeagueDao {

    @Override
    public void create(League league){
        try {
            CreateLeague.createLeague(league);
        } catch(ExecutionException | InterruptedException e) {
            throw new InternalDbException("There was an error on the DB.");
        }

    }

    @Override
    public List<League> getAll(String ownerId) {
        try {
            List<QueryDocumentSnapshot> snapshots = GetLeague.getAllLeaguesByOwner(ownerId);
            List<League> leagues = new ArrayList<>();
            if(snapshots.isEmpty()) {
                throw new LeagueNotFoundException("The trainer " + ownerId + " does not have any associated Leagues.");
            }

            for(QueryDocumentSnapshot doc : snapshots) {
                leagues.add(doc.toObject(League.class));
            }
            return leagues;
        } catch (MyFirebaseException e) {
            return null;
        }
    }

    public League getLeaguesByOwner(String ownerId) throws LeagueNotFoundException {
        try{
            QueryDocumentSnapshot qds = GetLeague.getLeaguesByOwner(ownerId);
            return qds.toObject(League.class);
        } catch(IndexOutOfBoundsException e) {
            throw new LeagueNotFoundException("The trainer " + ownerId + " does not have any associated Leagues.");
        } catch (MyFirebaseException e) {
            return null;
        }
    }
}
