package com.ssinc.Nuzlocke.service;

import com.ssinc.Nuzlocke.dataSource.FirebaseHelper;
import com.ssinc.Nuzlocke.impl.TrainerDaoImpl;
import com.ssinc.Nuzlocke.model.Trainer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
    public class TrainerService {

    public static Trainer getTrainer(String username){
        TrainerDaoImpl trainer = new TrainerDaoImpl();
        return trainer.get(username);
    }

    public static boolean createTrainer(String username, String displayName) {
        TrainerDaoImpl trainerDao = new TrainerDaoImpl();
        Trainer trainer = new Trainer(username, displayName);
        trainerDao.create(trainer);
        return true;
    }
}

