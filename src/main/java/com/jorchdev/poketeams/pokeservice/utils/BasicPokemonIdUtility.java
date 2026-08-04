package com.jorchdev.poketeams.pokeservice.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicPokemonIdUtility {
    private static final Pattern POKEMON_ID_PATTERN = Pattern.compile("/pokemon/(\\d+)/?$");

    public static int extractIdFromUrl(String url) {
        Matcher matcher = POKEMON_ID_PATTERN.matcher(url);
        if (!matcher.find()) {
            throw new IllegalStateException("No se pudo extraer el id de pokemon desde la url: " + url);
        }
        return Integer.parseInt(matcher.group(1));
    }
}
