package com.ssinc.Nuzlocke.model;


import java.util.ArrayList;
import java.util.List;

public class LeagueMembers {

    public String leagueId;

    public String trainerId;

    public String leagueName;

    public List<String> createdAt = new ArrayList<>();

    public LeagueMembers(){
        // TODO: ask salty about what the contructor params should be
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }


    public List<String> getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(List<String> createdAt) {
        this.createdAt = createdAt;
    }

}
