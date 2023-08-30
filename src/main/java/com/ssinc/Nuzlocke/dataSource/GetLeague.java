package com.ssinc.Nuzlocke.dataSource;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.ssinc.Nuzlocke.Exception.LeagueNotFoundException;
import com.ssinc.Nuzlocke.Exception.MyFirebaseException;
import com.ssinc.Nuzlocke.Exception.PokemonNotFoundException;
import com.ssinc.Nuzlocke.NuzlockeUtils.FirebaseConst;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GetLeague {

    public static QueryDocumentSnapshot getLeaguesByOwner(String ownerId) throws MyFirebaseException {
        try {
            CollectionReference colRef = FirebaseHelper.getCollectionRef(FirebaseConst.LEAGUE_COLLECTION);

            ApiFuture<QuerySnapshot> future =
                    colRef.whereEqualTo(FirebaseConst.LEAGUE_OWNER_ID, ownerId)
                            .get();

            return future.get().getDocuments().get(0);

        } catch (InterruptedException | ExecutionException e) {
            throw new MyFirebaseException("There was an issue with the DB: " + e);
        }
    }

    public static List<QueryDocumentSnapshot> getAllLeaguesByOwner(String ownerId) throws MyFirebaseException {
        try {
            CollectionReference colRef = FirebaseHelper.getCollectionRef(FirebaseConst.LEAGUE_COLLECTION);

            ApiFuture<QuerySnapshot> future =
                    colRef.whereEqualTo(FirebaseConst.LEAGUE_OWNER_ID, ownerId)
                            .get();

            return future.get().getDocuments();

        } catch (InterruptedException | ExecutionException e) {
            throw new MyFirebaseException("There was an issue with the DB: " + e);
        }
    }
}
