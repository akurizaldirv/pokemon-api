package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.dto.*;
import com.pokemonreview.api.exceptions.*;
import com.pokemonreview.api.entities.*;
import com.pokemonreview.api.repositories.*;
import com.pokemonreview.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final PokemonRepository pokemonRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(PokemonRepository pokemonRepository, ReviewRepository reviewRepository) {
        this.pokemonRepository = pokemonRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDto create(ReviewDto reviewDto, Integer pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Cannot post review, Pokemon ID does not exist"));

        Review review = new Review();
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        review.setPokemon(pokemon);

        Review reviewResponse = reviewRepository.save(review);

        return mapToDto(reviewResponse);
    }

    @Override
    public ReviewResponse getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Review> pages = reviewRepository.findAll(pageable);
        List<Review> reviewList = pages.getContent();
        List<ReviewDto> content = reviewList.stream().map(this::mapToDto).toList();

        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setContent(content);
        reviewResponse.setIsLast(pages.isLast());
        reviewResponse.setPageNo(pageable.getPageNumber());
        reviewResponse.setPageSize(pageable.getPageSize());
        reviewResponse.setTotalPages(pages.getTotalPages());
        reviewResponse.setTotalElements(pages.getTotalElements());

        return reviewResponse;
    }

    @Override
    public ReviewDto getById(Integer id, Integer pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Cannot find Pokemon with this Id"));
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Cannot find Review with this Id"));

        if (pokemon.getId() != review.getPokemon().getId()) {
            throw new ReviewNotFoundException("This review is not belong to this pokemon");
        }

        return mapToDto(review);
    }

    @Override
    public List<ReviewDto> getReviewByPokemonId(Integer id) {
        List<Review> reviewList = reviewRepository.findByPokemonId(id);

        if (reviewList.isEmpty()) {
            throw new PokemonNotFoundException("Cannot Find Pokemon with this Id");
        }

        return reviewList.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto updateById(Integer id, Integer pokemonId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Cannot Find Pokemon"));
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Cannot Find Review"));

        if (pokemon.getId() != review.getPokemon().getId()) {
            throw new ReviewNotFoundException("This Review is not Belong to Pokemon with ID = " + pokemonId);
        }

        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());
        review.setContent(reviewDto.getContent());

        Review updatedReview = reviewRepository.save(review);

        return mapToDto(updatedReview);
    }

    @Override
    public void deleteById(Integer id, Integer pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Cannot Find Pokemon"));
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Cannot Find Review"));

        if (pokemon.getId() != review.getPokemon().getId()) {
            throw new ReviewNotFoundException("This Review is not Belong to Pokemon with ID = " + pokemonId);
        }

        reviewRepository.deleteById(id);
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        reviewDto.setTitle(review.getTitle());

        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());
        review.setContent(reviewDto.getContent());

        return review;
    }
}
