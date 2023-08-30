package com.ssinc.Nuzlocke.dao;

import com.ssinc.Nuzlocke.model.Trainer;

public interface TrainerDao {
    Trainer get(String username);
    boolean create(Trainer trainer);
}
