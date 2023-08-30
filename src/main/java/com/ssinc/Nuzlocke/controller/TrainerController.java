package com.ssinc.Nuzlocke.controller;

import com.ssinc.Nuzlocke.model.Trainer;
import com.ssinc.Nuzlocke.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class TrainerController {
    @GetMapping("/Trainers")
    public ResponseEntity<Trainer> getTrainerByUsername(@RequestParam("username") String username) {
        Trainer trainer = TrainerService.getTrainer(username);
        if (trainer != null){
            return ResponseEntity.status(HttpStatus.OK).body(trainer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/Trainers")
    public ResponseEntity createNewTrainer(@RequestParam("username") String username,
                                           @RequestParam("displayName") String displayName) {
        boolean result = TrainerService.createTrainer(username, displayName);
        if(result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
