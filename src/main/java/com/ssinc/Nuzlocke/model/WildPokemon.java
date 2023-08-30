package com.ssinc.Nuzlocke.model;

public class WildPokemon {

    public String name;
    public long appearancerate;

    public String exclusive;

    public WildPokemon(String name, int appearancerate) {
        this.name = name;
        this.appearancerate = appearancerate;
    }

    public WildPokemon(String name, long appearancerate, String exclusive) {
        this.name = name;
        this.appearancerate = appearancerate;
        this.exclusive = exclusive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAppearancerate() {
        return appearancerate;
    }

    public void setAppearancerate(int appearancerate) {
        this.appearancerate = appearancerate;
    }
}
