package com.jorchdev.poketeams.pokeservice.components;

import com.jorchdev.poketeams.pokeservice.dtos.PokemonApi.PokemonApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PokemonApiWebClient {
    private final WebClient webClient;

    public PokemonApiWebClient (WebClient webClient) {this.webClient = webClient;}

    public PokemonApiResponse getPokemonById(int pokemonId) {
        return webClient
                .get()
                .uri("/pokemon/{id}", pokemonId)
                .retrieve()
                .bodyToMono(PokemonApiResponse.class)
                .block();
    }

    public PokemonApiResponse getPokemonByName(String name){
        return webClient
                .get()
                .uri("/pokemon/{name}", name)
                .retrieve()
                .bodyToMono(PokemonApiResponse.class)
                .block();
    }
}
