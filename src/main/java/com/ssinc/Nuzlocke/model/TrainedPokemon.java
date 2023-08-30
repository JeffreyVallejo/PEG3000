package com.ssinc.Nuzlocke.model;


public class TrainedPokemon {

    private String trainer;
    private String name;
    private String nature;
    private String nickname;
    private String status;

    public TrainedPokemon(){

    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getName() {
        return name;
    }

    public String getNature() {
        return nature;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
