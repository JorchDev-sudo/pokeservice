package com.jorchdev.poketeams.pokeservice.services;

import com.jorchdev.poketeams.pokeservice.components.PokemonApiRestClient;
import com.jorchdev.poketeams.pokeservice.components.PokemonApiWebClient;
import com.jorchdev.poketeams.pokeservice.components.mappers.PokemonMapper;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.basic.PokemonBasic;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.basic.PokemonBasicResponse;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonResponse;
import com.jorchdev.poketeams.pokeservice.entities.Pokemon;
import com.jorchdev.poketeams.pokeservice.repositories.PokemonRepository;
import com.jorchdev.poketeams.pokeservice.utils.BasicPokemonIdUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {
    private final PokemonRepository repository;
    private final PokemonApiWebClient apiWebClient;
    private final PokemonApiRestClient apiRestClient;
    private final PokemonMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    public PokemonService
            (PokemonRepository repository,
             PokemonApiWebClient apiWebClient,
             PokemonApiRestClient apiRestClient,
             PokemonMapper pokemonMapper)
    {
        this.repository = repository;
        this.apiWebClient = apiWebClient;
        this.apiRestClient = apiRestClient;
        this.mapper = pokemonMapper;
    }

    public PokemonResponse getPokemonById(int id){
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

    public PokemonResponse getPokemonByName(String name){
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

    public List<PokemonBasicResponse> searchPokemon(String search) {
        List<PokemonBasic> pokemonBasics = apiRestClient.search(search.toLowerCase());
        List<PokemonBasicResponse> response = new ArrayList<>();

        for (PokemonBasic pokemon : pokemonBasics) {
            response.add(new PokemonBasicResponse(pokemon.name(), pokemon.url(), BasicPokemonIdUtility.extractIdFromUrl(pokemon.url())));
        }

        return response;
    }
}

