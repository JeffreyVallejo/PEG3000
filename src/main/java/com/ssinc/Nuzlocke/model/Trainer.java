package com.ssinc.Nuzlocke.model;

import java.util.ArrayList;
import java.util.List;

public class Trainer {

    private String username;

    private String displayName;

    public Trainer() {

    }

    public Trainer(String username, String displayName) {
        this.username = username;
        this.displayName = displayName;
    }

    public Trainer(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        if (username != null){
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username can't be null");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        if(displayName == null){
            this.displayName = this.username;
        }
        this.displayName = displayName;
    }

}
