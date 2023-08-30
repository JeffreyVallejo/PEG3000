package com.ssinc.Nuzlocke.controller;


import com.ssinc.Nuzlocke.dataSource.FirebaseImportData;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.ExecutionException;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> requestOK(){
        return ResponseEntity.ok("The HTTP Status is OK (CODE 200)");
    }

    @GetMapping("/update-firebase-data")
    public ResponseEntity<String> updateFirebaseData(){
        try {
            FirebaseImportData.importToFirebase();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("The HTTP Status is OK (CODE 200)");
    }


}

