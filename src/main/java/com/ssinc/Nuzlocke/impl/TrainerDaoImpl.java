package com.ssinc.Nuzlocke.impl;

import com.google.cloud.firestore.CollectionReference;
import com.ssinc.Nuzlocke.NuzlockeUtils.FirebaseConst;
import com.ssinc.Nuzlocke.dao.TrainerDao;
import com.ssinc.Nuzlocke.dataSource.FirebaseHelper;
import com.ssinc.Nuzlocke.model.Trainer;

import java.util.Objects;

public class TrainerDaoImpl implements TrainerDao {

    @Override
    public Trainer get(String username) {
        return getTrainerFromDB(username);
    }

    @Override
    public boolean create(Trainer trainer) {
        CollectionReference colRef = FirebaseHelper.getCollectionRef(FirebaseConst.TRAINERS_COLLECTION);
        // Check if the trainer already exists. If the username already exists, return a failed creation.
        if(!Objects.isNull(getTrainerFromDB(trainer.getUsername()))) {
            return false;
        }

        colRef.document(trainer.getUsername()).set(trainer);
        return true;
    }

    private Trainer getTrainerFromDB(String username) {
        // Create a Firebase instance.
        return Objects.requireNonNull(
                FirebaseHelper.getDocumentSnapshot(FirebaseConst.TRAINERS_COLLECTION, username))
                .toObject(Trainer.class);
    }

    private boolean putTrainer(Trainer trainer) {
        return true;
    }

}
