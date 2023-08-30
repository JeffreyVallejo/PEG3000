package com.ssinc.Nuzlocke.Helper;

import com.ssinc.Nuzlocke.model.WildPokemon;

import java.util.*;

public class GenerateRandomEncounter {
    private int totalChance = 0;
    private HashMap<String, ArrayList<Integer>> pokemonAppearanceRates = new HashMap<>();

    /**
     * Takes a location and generates a random encounter.
     *
     * @param wildPokemon  Contains the location name and the Pokémon associated with it.
     */
    public String generateRandomEncounter(List<WildPokemon> wildPokemon, String version) {

        for(WildPokemon pokemon : wildPokemon) {
            if(!pokemon.exclusive.isEmpty() || pokemon.exclusive.equals(version)) {
                assignChanceToPokemon(pokemon);
                totalChance += pokemon.appearancerate;
            }
        }

        return randomlyPickEncounter();
    }

    /**
     * Assigns a number range to the Pokémon given the rate at which the Pokémon would appear.
     *
     * @param pokemon  Name to be associated with the number range.
     */
    private void assignChanceToPokemon(WildPokemon pokemon) {
        ArrayList<Integer> numRange = new ArrayList<>();

        if(pokemonAppearanceRates.containsKey(pokemon.name)) {
            numRange = pokemonAppearanceRates.get(pokemon.name);
        }

        // Fill the array with the numbers that will represent the chances.
        for(int i = totalChance; i < totalChance + pokemon.appearancerate; i++) {
            numRange.add(i);
        }

        pokemonAppearanceRates.put(pokemon.name, numRange);
    }

    /**
     * Generates a number to pick a Pokémon from then returns the appropriate Pokémon.
     */
    private String randomlyPickEncounter() {
        int rand = (int)Math.floor(Math.random()*(totalChance-0+1)+0);

        Iterator it = pokemonAppearanceRates.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ArrayList<Integer> list = (ArrayList<Integer>) pair.getValue();
            if(list.contains(rand)) {
                return pair.getKey().toString();
            }
        }

        return "";
    }
}
