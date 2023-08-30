package com.ssinc.Nuzlocke.dataSource;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class FirebaseHelper {

    private static final Firestore db = FirestoreClient.getFirestore();

    public static DocumentSnapshot getDocumentSnapshot(String collectionName, String documentName){
        // Get the DocumentSnapshot from the collection WildPokemon.
        try {
            return db.collection(collectionName).document(documentName).get().get();
        } catch (ExecutionException | InterruptedException error) {
            System.out.println("There was an error reading the DB :" + error);
            return null;
        }

    }

    public static DocumentReference getDocumentRef(String collectionName, String documentName) {
        // Get the DocumentReference.
        return db.collection(collectionName).document(documentName);
    }

    public static CollectionReference getCollectionRef(String collectionName) {
        // Get the DocumentReference.
        return db.collection(collectionName);
    }
}
