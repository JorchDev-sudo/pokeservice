package com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveSlotDto {
    public MoveDto move;
}
