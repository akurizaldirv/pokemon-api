package com.pokemonreview.api.services;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewDto create(ReviewDto reviewDto, Integer pokemonId);
    ReviewResponse getAll(Integer pageNo, Integer pageSize);
    ReviewDto getById(Integer id, Integer pokemonId);
    ReviewDto updateById(Integer id, Integer pokemonId, ReviewDto reviewDto);
    void deleteById(Integer id, Integer pokemonId);

    List<ReviewDto> getReviewByPokemonId(Integer id);
}
