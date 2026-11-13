package com.jorchdev.poketeams.pokeservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import java.security.interfaces.RSAPublicKey;
import java.util.List;

@Configuration
public class JwtConfig {

    private static final String ISSUER = "poketeams-gateway";
    private static final String AUDIENCE = "poketeams-internal";

    @Bean
    JwtDecoder jwtDecoder(@Value("${internal-jwt.public-key}") RSAPublicKey publicKey) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withPublicKey(publicKey).build();

        OAuth2TokenValidator<Jwt> audienceValidator = new JwtClaimValidator<List<String>>(
                "aud", aud -> aud != null && aud.contains(AUDIENCE));

        decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(
                JwtValidators.createDefaultWithIssuer(ISSUER),
                audienceValidator));
        return decoder;
    }
}