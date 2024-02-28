package com.pokemonreview.api.services;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {
    PokemonDto create(PokemonDto pokemonDto);
    PokemonResponse getAll(Integer pageNo, Integer pageSize);
    PokemonDto getById(Integer id);
    PokemonDto update(PokemonDto pokemonDto, Integer id);
    void delete(Integer id);
}
