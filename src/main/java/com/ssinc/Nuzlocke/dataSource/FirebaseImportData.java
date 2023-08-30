package com.ssinc.Nuzlocke.dataSource;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.ssinc.Nuzlocke.model.WildPokemon;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FirebaseImportData {


    public static boolean importToFirebase()throws ExecutionException, InterruptedException {
        Map<String, Map<String,Object>> doData = new HashMap<>();
        buildLocationsFromCSV(doData);
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference wildPokemonCollection = db.collection("WildPokemon");

        Iterator hmIterator = doData.entrySet().iterator();

        // Iterating through Hashmap and
        // adding some bonus marks for every student
        while (hmIterator.hasNext()) {

            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            String locationName = (String) mapElement.getKey();
            Map<String, Object> pokemonList = (Map<String, Object>) mapElement.getValue();

            wildPokemonCollection.document(locationName).set(pokemonList);
        }


        return true;
    }

    public static void buildLocationsFromCSV(Map<String, Map<String, Object>> doData){

        String line = "";
        String splitBy = ",";
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("PokeSheet.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] location = line.split(splitBy);    // use comma as separator
                WildPokemon pokemon = new WildPokemon(location[1], Integer.parseInt(location[3]));
                pokemon.exclusive = location[2];

                Map<String, Object> arrayMap = new HashMap<>();
                arrayMap.put("appearance rate", Integer.parseInt(location[3]));
                arrayMap.put("exclusive", location[2]);

                if(doData.containsKey(location[0])){
                    Map<String, Object> tempMap = doData.get(location[0]);
                    tempMap.put(location[1], arrayMap);
                    doData.put(location[0], tempMap);
                }else {
                    Map<String, Object> newMapper = new HashMap<>();
                    newMapper.put(location[1], arrayMap);
                    doData.put(location[0], newMapper);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
