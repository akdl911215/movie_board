package com.jh.move_review.repository;

import com.jh.move_review.entity.Movie;
import com.jh.move_review.entity.Review;
import com.jh.move_review.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // FETCH 속성값은 attributePaths 에 명시한 속성은 EAGER 로 처리하고, 나머지는 LAZY 로 처리한다.
    // LOAD 속성값은 attributePaths 에 명시한 속성은 EAGER 로 처리하고, 나머지는 엔티티 클래스에 명시되거나 기본 방식으로 처리한다.
    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    @Modifying // update or delete 를 이용하기 위해서는 필수
    @Query("delete from Review mr where mr.user = :user")
    void deleteByUser(User user);
}
