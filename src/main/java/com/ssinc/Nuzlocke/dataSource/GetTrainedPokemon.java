package com.ssinc.Nuzlocke.dataSource;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.ssinc.Nuzlocke.Exception.MyFirebaseException;
import com.ssinc.Nuzlocke.Exception.PokemonNotFoundException;
import com.ssinc.Nuzlocke.NuzlockeUtils.FirebaseConst;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GetTrainedPokemon {

    public static List<QueryDocumentSnapshot> getTrainersPokemon(String username) throws MyFirebaseException {

        try {
            CollectionReference colRef = FirebaseHelper.getCollectionRef(FirebaseConst.POKEMON_CENTER_COLLECTION);

            ApiFuture<QuerySnapshot> future = colRef.whereEqualTo(FirebaseConst.TRAINED_POKEMON_TRAINER_ID, username).get();

            return future.get().getDocuments();

        } catch (InterruptedException | ExecutionException e) {
            throw new MyFirebaseException("There was an issue with the DB" + e);
        }

    }

    public static List<QueryDocumentSnapshot> getPokemonInParty(String trainer, String status) throws ExecutionException, InterruptedException {
        CollectionReference colRef = FirebaseHelper.getCollectionRef(FirebaseConst.POKEMON_CENTER_COLLECTION);

        ApiFuture<QuerySnapshot> future = colRef
                                            .whereEqualTo(FirebaseConst.TRAINED_POKEMON_TRAINER_ID, trainer)
                                            .whereEqualTo(FirebaseConst.TRAINED_POKEMON_STATUS, status)
                                            .get();

        return future.get().getDocuments();
    }

    public static QueryDocumentSnapshot getTrainedPokemon(String trainer, String pokemonName) throws MyFirebaseException, PokemonNotFoundException {

        try {
            CollectionReference colRef = FirebaseHelper.getCollectionRef(FirebaseConst.POKEMON_CENTER_COLLECTION);

            ApiFuture<QuerySnapshot> future =
                    colRef.whereEqualTo(FirebaseConst.TRAINED_POKEMON_TRAINER_ID, trainer)
                            .whereEqualTo(FirebaseConst.POKEMON_NAME, pokemonName)
                            .get();

            return future.get().getDocuments().get(0);

        } catch (InterruptedException | ExecutionException e) {
            throw new MyFirebaseException("There was an issue with the DB" + e);
        } catch (IndexOutOfBoundsException e) {
            throw new PokemonNotFoundException("Pokemon does not exist.");
        }

    }
}
