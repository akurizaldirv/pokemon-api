package com.pokemonreview.api.repositories;

import com.pokemonreview.api.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPokemonId(Integer pokemonId);
}
