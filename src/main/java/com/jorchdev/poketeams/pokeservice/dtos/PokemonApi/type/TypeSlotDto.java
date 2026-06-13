package com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeSlotDto {
    public TypeDto type;
}
