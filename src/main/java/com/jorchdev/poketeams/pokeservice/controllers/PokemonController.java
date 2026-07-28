package com.jorchdev.poketeams.pokeservice.controllers;

import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.basic.PokemonBasic;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.basic.PokemonBasicResponse;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonResponse;
import com.jorchdev.poketeams.pokeservice.services.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;
    private final Logger logger = LoggerFactory.getLogger(PokemonController.class);

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponse> getPokemonById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(pokemonService.getPokemonById(id));
        }catch(Exception e) {
            logger.error(e.getMessage());

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<PokemonResponse> getPokemonByName(@RequestParam String name) {
        try{
            return ResponseEntity.ok(pokemonService.getPokemonByName(name));
        }catch(Exception e) {
            logger.error(e.getMessage());

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/contains")
    public ResponseEntity<List<PokemonBasicResponse>> searchPokemon(@RequestParam String name) {
        try{
            return ResponseEntity.ok(pokemonService.searchPokemon(name));
        }catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
