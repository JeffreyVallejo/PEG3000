package com.ssinc.Nuzlocke.dataSource;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.ssinc.Nuzlocke.model.WildPokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class GetLocationPokemon {

    public static List<WildPokemon> getPokemonByLocation(String location) throws ExecutionException, InterruptedException {
        return getPokemon(location);
    }

    /**
     * This method will get all Pokémon for a given location.
     *
     * @param location  Given location query parameter.
     */
    private static List<WildPokemon> getPokemon(String location) throws ExecutionException, InterruptedException {
        //TODO: REFACTOR
        System.out.println("Accessing Firebase...");
        List<WildPokemon> tempList = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();
        // Get the DocumentSnapshot from the collection WildPokemon.
        DocumentSnapshot doc = db.collection("WildPokemon").document(location).get().get();

        // Get the Mapping of the document data. If its null we can return an empty list.
        Map<String, Object> docMap = doc.getData();
        if(docMap == null) {
            return tempList;
        }

        // Iterate over the map and fill the List of Pokémon with the data.
        for (Map.Entry<String, Object> entry : docMap.entrySet()) {
            Map<String, Object> map = (Map<String, Object>) entry.getValue();

            tempList.add(new WildPokemon(entry.getKey(), (Long) map.get("appearance rate"), (String) map.get("exclusive")));
        }

        return tempList;
    }
}
