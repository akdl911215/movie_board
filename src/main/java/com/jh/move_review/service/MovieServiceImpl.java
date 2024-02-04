package com.jh.move_review.service;

import com.jh.move_review.dto.MovieDTO;
import com.jh.move_review.dto.PageRequestDTO;
import com.jh.move_review.dto.PageResultDTO;
import com.jh.move_review.entity.Movie;
import com.jh.move_review.entity.MovieImage;
import com.jh.move_review.repository.MovieImageRepository;
import com.jh.move_review.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieImageRepository movieImageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        log.info("entityMap : " + entityMap);

        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imageList");
        log.info("movieImageList : " + movieImageList);

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });

        log.info("movie : " + movie);
        log.info("movie.getId() : " + movie.getId());

        return movie.getId();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
                (Movie) arr[0],
                (List<MovieImage>) (Arrays.asList((MovieImage) arr[1])),
                (Double) arr[2],
                (Long) arr[3]
        ));

        return new PageResultDTO<>(result, fn);
    }
}
