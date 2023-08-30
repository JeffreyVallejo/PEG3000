package com.ssinc.Nuzlocke.NuzlockeUtils;

public class PokemonStatusConst {
    public static final String BOXED = "boxed";
    public static final String GRAVE = "grave";
    public static final String PARTY = "party";

    public static boolean isValidStatus(String status) {
        if(status.equals(BOXED) || status.equals(GRAVE) || status.equals(PARTY)) {
            return true;
        }

        return false;
    }
}
