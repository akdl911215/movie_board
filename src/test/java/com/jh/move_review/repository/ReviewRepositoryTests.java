package com.jh.move_review.repository;

import com.jh.move_review.entity.Movie;
import com.jh.move_review.entity.Review;
import com.jh.move_review.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testGetMovieReviews() {

        Movie movie = Movie.builder()
                .id(93L)
                .build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {

            System.out.println(movieReview.getId());
            System.out.println("\t" + movieReview.getGrade());
            System.out.println("\t" + movieReview.getText());
            System.out.println("\t" + movieReview.getUser().getEmail());
            System.out.println("-----------------------------");
        });
    }

    @Test
    public void insertMovieReviews() {

        // 200개의 리뷰를 등록
        IntStream.rangeClosed(1, 200).forEach(i -> {

            // 영화 번호
            Long movieId = (long)(Math.random() * 99) + 1;

            // 리뷰어 번호
            Long reviewerId = (long)(Math.random() * 100) + 1;

            User user = User.builder().id(reviewerId).build();
            Movie movie = Movie.builder().id(movieId).build();

            Review review = Review.builder()
                    .user(user)
                    .movie(movie)
                    .grade((int)(Math.random() * 5) + 1)
                    .text("feel..." + i)
                    .build();

            reviewRepository.save(review);
        });
    }
}
