package com.jorchdev.poketeams.pokeservice.services;

import com.jorchdev.poketeams.pokeservice.components.PokemonApiWebClient;
import com.jorchdev.poketeams.pokeservice.components.mappers.PokemonMapper;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonResponseDto;
import com.jorchdev.poketeams.pokeservice.entities.Pokemon;
import com.jorchdev.poketeams.pokeservice.repositories.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    private final PokemonRepository repository;
    private final PokemonApiWebClient apiWebClient;
    private final PokemonMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    public PokemonService
            (PokemonRepository repository,
             PokemonApiWebClient apiWebClient,
             PokemonMapper pokemonMapper)
    {
        this.repository = repository;
        this.apiWebClient = apiWebClient;
        this.mapper = pokemonMapper;
    }

    public PokemonResponseDto getPokemonById(int id){
        return mapper.toResponseDto(
                repository.findById(id)
                        .orElseGet(() -> {
                            logger.warn("Pokemon with id {} not found in cache, fetching from PokeAPI", id);

                            Pokemon saved = repository.save(mapper.toEntity(apiWebClient.getPokemonById(id)));
                            logger.info("Pokemon with id {} saved in cache", id);
                            return saved;
                        })
        );
    }

    public PokemonResponseDto getPokemonByName(String name){
        return mapper.toResponseDto(
                repository.findByName(name)
                        .orElseGet(() -> {
                            logger.warn("Pokemon with name {} not found in cache, fetching from PokeAPI", name);

                            Pokemon saved = repository.save(mapper.toEntity(apiWebClient.getPokemonByName(name)));
                            logger.info("Pokemon with name {} saved in cache", name);
                            return saved;
                        })
        );
    }
}

