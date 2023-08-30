package com.ssinc.Nuzlocke.controller;

import com.ssinc.Nuzlocke.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("location")
public class LocationController {

    private final LocationService locationService;
    private static final Gson gson = new Gson();

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/Encounter")
    public ResponseEntity getEncounter(@RequestParam("location") String location){
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(locationService.getRandomEncounter(location)));
    }
}