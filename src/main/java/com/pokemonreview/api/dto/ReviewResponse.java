package com.pokemonreview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReviewResponse {
    private List<ReviewDto> content;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;
}
