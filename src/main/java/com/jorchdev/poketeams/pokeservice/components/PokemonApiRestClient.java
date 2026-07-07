package com.jorchdev.poketeams.pokeservice.components;

import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.basic.PokemonBasic;
import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.basic.PokemonList;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Component
public class PokemonApiRestClient {
    private List<PokemonBasic> pokemonCache;
    private final Logger logger = LoggerFactory.getLogger(PokemonApiRestClient.class);

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://pokeapi.co/api/v2")
            .build();

    @PostConstruct
    public void init() {
        logger.info("init PokemonApiWebClient");
        pokemonCache = Objects.requireNonNull(restClient.get()
                        .uri("/pokemon?limit=100000")
                        .retrieve()
                        .body(PokemonList.class))
                .results();

        logger.info("{} pokemons found", pokemonCache.size());
    }

    public List<PokemonBasic> search(String query) {
        return pokemonCache.stream()
                .filter(p -> p.name().contains(query.toLowerCase()))
                .toList();
    }
}
