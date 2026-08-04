package com.jorchdev.poketeams.pokeservice.dtos.PokemonApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.move.MoveSlot;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.type.TypeSlot;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonApiResponse {
    public int id;

    public String name;

    public List<TypeSlot> types;

    public List<MoveSlot> moves;
}
