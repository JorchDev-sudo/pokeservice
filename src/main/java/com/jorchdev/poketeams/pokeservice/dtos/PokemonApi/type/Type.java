package com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {
    public String name;
}