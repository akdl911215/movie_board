package com.jh.move_review.controller;

import com.jh.move_review.dto.MovieDTO;
import com.jh.move_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public String register() {
        return "movie register post please";
    }

    @PostMapping("/register")
    public Long register(MovieDTO movieDTO) {
        log.info("movieDTO : " + movieDTO);

        Long id = movieService.register(movieDTO);

        return id;
    }

}


