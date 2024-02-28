package com.pokemonreview.api.dto;

import com.pokemonreview.api.models.Pokemon;
import lombok.Data;

@Data
public class ReviewDto {
    private Integer id;
    private String title;
    private String content;
    private Integer stars;
//    private Pokemon pokemon;
}
