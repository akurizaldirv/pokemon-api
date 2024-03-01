package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.services.BlacklistService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class BlacklistServiceImpl implements BlacklistService {
    private HashSet<String> blacklist = new HashSet<>();
    @Override
    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
