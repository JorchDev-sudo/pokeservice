package com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.basic;

public record PokemonBasicResponse(
        String name,
        String url,
        int pokemonId) { }
