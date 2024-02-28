package com.pokemonreview.api.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    public String extractUsername(String jwt);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public String generateToken(
            Map<String, Object> extraClaim,
            UserDetails userDetails
    );

    public boolean isTokenValid(String token, UserDetails userDetails);

    public String generateToken(UserDetails userDetails);
}
