package com.ssinc.Nuzlocke.controller;

import com.ssinc.Nuzlocke.Exception.*;
import com.ssinc.Nuzlocke.model.PokemonCenter;
import com.ssinc.Nuzlocke.model.TrainedPokemon;
import com.ssinc.Nuzlocke.service.PokemonCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PokemonCenterController {

    @Autowired
    PokemonCenterService pokemonCenterService;

    @GetMapping("/pc")
    public ResponseEntity getAllTrainedPokemon(@RequestParam("username") String username) {
        try {
            PokemonCenter pc = pokemonCenterService.getAllTrainedPokemon(username);
            return ResponseEntity.status(HttpStatus.OK).body(pc);
        } catch(TrainerNotFoundException e) {
            return new ResponseEntity<>("There was no Trainer by the id " + username, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pc")
    @ResponseStatus
    public ResponseEntity addPokemon(@RequestBody TrainedPokemon pokemon) {
        try {
            pokemonCenterService.addPokemonToPokemonCenter(pokemon);
            return new ResponseEntity<>("Pokemon was successfully added to the PC!", HttpStatus.CREATED);
        } catch (PartyFullException error) {
            System.out.println("pokemon added to box");
            return new ResponseEntity<>("Party was full, pokemon added to box", HttpStatus.CREATED);
        }
    }

    @PutMapping("/pc/move")
    @ResponseStatus
    public ResponseEntity movePokemon(@RequestParam String trainerId, @RequestParam String pokemonName, @RequestParam String status) {
        try {
            pokemonCenterService.movePokemonTo(trainerId, pokemonName, status.toLowerCase());
            return new ResponseEntity<>("Pokemon " + pokemonName + " was successfully moved to " + status.toLowerCase() + ".", HttpStatus.OK);
        } catch (PokemonNotFoundException error) {
            System.out.println("The requested pokemon did not exist");
            return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
        } catch (PartyFullException error) {
            return new ResponseEntity<>("Party Full! Pokemon cannot be added to the party.", HttpStatus.BAD_REQUEST);
        } catch (InvalidPcStatusException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
