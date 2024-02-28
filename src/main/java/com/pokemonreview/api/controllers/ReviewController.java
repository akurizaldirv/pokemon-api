package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.dto.ReviewResponse;
import com.pokemonreview.api.services.ReviewService;
import com.pokemonreview.api.services.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable("pokemonId") Integer pokemonId) {
        return new ResponseEntity<ReviewDto>(reviewService.create(reviewDto, pokemonId), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/review")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReviewDto>> getPokemonReview(@PathVariable(value = "pokemonId") Integer pokemonId) {
        return new ResponseEntity<List<ReviewDto>>(reviewService.getReviewByPokemonId(pokemonId), HttpStatus.OK);
    }

    @GetMapping("/pokemon/{pokemonId}/review/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReviewDto> getReview(@PathVariable(value = "pokemonId") Integer pokemonId, @PathVariable(value = "id") Integer id) {
        return new ResponseEntity<ReviewDto>(reviewService.getById(id, pokemonId), HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/review/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "id") Integer id, @PathVariable(value = "pokemonId") Integer pokemonId,
                                                  @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<ReviewDto>(reviewService.updateById(id, pokemonId, reviewDto), HttpStatus.CREATED);
    };

    @DeleteMapping("/pokemon/{pokemonId}/review/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteReview(@PathVariable(value = "pokemonId") Integer pokemonId, @PathVariable(value = "id") Integer id) {
        reviewService.deleteById(id, pokemonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
