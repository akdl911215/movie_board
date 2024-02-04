package com.jh.move_review.controller;

import com.jh.move_review.dto.MovieDTO;
import com.jh.move_review.dto.PageRequestDTO;
import com.jh.move_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
@ResponseBody
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO) {
        log.info("");
    }

    @GetMapping("/register")
    public String register() {
        return "movie register post please";
    }

    @PostMapping("/register")
    public Long register(@RequestBody MovieDTO movieDTO) {
        log.info("movieDTO : " + movieDTO);

        Long id = movieService.register(movieDTO);
        log.info("return id : " + id);

        return id;
    }
}


