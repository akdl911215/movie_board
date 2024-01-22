package com.jh.move_review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MoveReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoveReviewApplication.class, args);
    }

}
