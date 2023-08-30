package com.ssinc.Nuzlocke.dataSource;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.ssinc.Nuzlocke.NuzlockeUtils.FirebaseConst;
import com.ssinc.Nuzlocke.model.TrainedPokemon;

public class AddTrainedPokemon {

    public static void create(TrainedPokemon pokemon) {
        CollectionReference colRef = FirebaseHelper.getCollectionRef(FirebaseConst.POKEMON_CENTER_COLLECTION);

        colRef.document().set(pokemon);
    }

    public static void update(QueryDocumentSnapshot qSnap, TrainedPokemon trainedPokemon) {
        DocumentReference docRef =
                FirebaseHelper.getCollectionRef(FirebaseConst.POKEMON_CENTER_COLLECTION)
                        .document(qSnap.getId());

        docRef.update(FirebaseConst.TRAINED_POKEMON_STATUS, trainedPokemon.getStatus());
    }
}
