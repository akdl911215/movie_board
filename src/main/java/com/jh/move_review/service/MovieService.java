package com.jh.move_review.service;

import com.jh.move_review.dto.MovieDTO;
import com.jh.move_review.dto.MovieImageDTO;
import com.jh.move_review.dto.PageRequestDTO;
import com.jh.move_review.dto.PageResultDTO;
import com.jh.move_review.entity.Movie;
import com.jh.move_review.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);

    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDto);

    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages, Double average, Long reviewCount) {

        MovieDTO movieDTO = MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();

        List<MovieImageDTO> movieImageDTOList = movieImages.stream().map(movieImage -> {

            return MovieImageDTO.builder()
                    .imageName(movieImage.getImageName())
                    .path(movieImage.getPath())
                    .uuid(movieImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageDTOList);
        movieDTO.setAverage(average);
        movieDTO.setReviewCount(reviewCount.intValue());

        return movieDTO;
    }

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) { // Map 타입으로 반환

        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .id(movieDTO.getId())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        // MovieImageDTO 처리
        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO -> {

                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imageName(movieImageDTO.getImageName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();

                return movieImage;
            }).collect(Collectors.toList());

            entityMap.put("imageList", movieImageList);
        }

        return entityMap;
    }

}
