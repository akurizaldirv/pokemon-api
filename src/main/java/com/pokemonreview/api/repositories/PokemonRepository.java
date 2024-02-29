package com.pokemonreview.api.repositories;

import com.pokemonreview.api.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}





