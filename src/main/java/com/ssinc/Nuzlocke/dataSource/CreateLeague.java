package com.ssinc.Nuzlocke.dataSource;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QuerySnapshot;
import com.ssinc.Nuzlocke.Exception.IllegalCreateException;
import com.ssinc.Nuzlocke.NuzlockeUtils.FirebaseConst;
import com.ssinc.Nuzlocke.model.League;

import java.util.concurrent.ExecutionException;

public class CreateLeague {

    private static CollectionReference colRef = FirebaseHelper.getCollectionRef(FirebaseConst.LEAGUE_COLLECTION);

    public static void createLeague(League league) throws IllegalCreateException, ExecutionException, InterruptedException {
        getLeague(league.getDisplayName());
        colRef.document().set(league);
    }

    private static void getLeague(String displayName) throws ExecutionException, InterruptedException, IllegalCreateException {
        ApiFuture<QuerySnapshot> snap = colRef.whereEqualTo(FirebaseConst.LEAGUE_DISPLAY_NAME, displayName).get();

        if(!snap.get().getDocuments().isEmpty()) {
            throw new IllegalCreateException("'" + displayName + "' is already in use.");
        }
    }
}
