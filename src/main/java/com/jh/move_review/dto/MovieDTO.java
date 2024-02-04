package com.jh.move_review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;

    private String title;

    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();

    // 영화의 평균 평점
    private double average;

    // 리뷰 수 jpa 의 count()
    private int reviewCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
