package com.pokemonreview.api.services;

public interface BlacklistService {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}
