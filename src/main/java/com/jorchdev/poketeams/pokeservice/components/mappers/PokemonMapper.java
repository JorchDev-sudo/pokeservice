package com.jorchdev.poketeams.pokeservice.components.mappers;

import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.PokemonApiResponseDto;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonResponseDto;
import com.jorchdev.poketeams.pokeservice.entities.Pokemon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonMapper {
    public Pokemon toEntity (PokemonApiResponseDto dto){
        Pokemon pokemon = new Pokemon();

        pokemon.setId(dto.id);
        pokemon.setName(dto.name);

        List<String> types = dto.types
                .stream()
                .map(slot -> slot.type.name)
                .toList();
        pokemon.setTypes(types);

        List<String> moves = dto.moves
                .stream()
                .map(slot -> slot.move.name)
                .toList();
        pokemon.setMoves(moves);

        return pokemon;
    }

    public PokemonResponseDto toResponseDto (Pokemon pokemon){
        PokemonResponseDto response = new PokemonResponseDto();

        response.id = pokemon.getId();
        response.name = pokemon.getName();
        response.types = pokemon.getTypes();
        response.moves = pokemon.getMoves();

        return response;
    }
}
