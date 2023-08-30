package com.ssinc.Nuzlocke.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class League {

    @Getter
    @NotBlank(message = "displayName may not be blank.")
    public String displayName;

    @Getter
    @NotBlank(message = "secureId may not be blank.")
    @Size(min = 1, message = "The secureId is too short.")
    @Size(max = 10, message = "The secureId is too long.")
    public String secureId;

    @Getter
    @NotBlank(message = "ownerId may not be blank.")
    public String ownerId;

    public boolean isActive;

    public boolean isRegOpen;

    public League() {

    }


    public League(String displayName, String ownerId, String secureId){
        this.displayName = displayName;
        this.ownerId = ownerId;
        this.secureId = secureId;
        this.isActive = true;
        this.isRegOpen = false;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setRegOpen(boolean regOpen) {
        isRegOpen = regOpen;
    }

}
