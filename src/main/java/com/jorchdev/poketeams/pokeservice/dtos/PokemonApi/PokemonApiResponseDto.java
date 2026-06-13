package com.jorchdev.poketeams.pokeservice.dtos.PokemonApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.move.MoveDto;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.move.MoveSlotDto;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.type.TypeDto;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.type.TypeSlotDto;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonApiResponseDto {
    public int id;

    public String name;

    public List<TypeSlotDto> types;

    public List<MoveSlotDto> moves;
}
