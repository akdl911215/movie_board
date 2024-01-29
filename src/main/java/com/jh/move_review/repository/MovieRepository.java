package com.jh.move_review.repository;

import com.jh.move_review.entity.Movie;
import com.jh.move_review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // coalesce() 함수의 두번째 인자에 0 을 넣으면 r.grade 가 null 일때 0으로 대체된다.
    @Query("SELECT m, mi, avg(coalesce(r.grade, 0)), count(distinct r) FROM Movie m" +
            " LEFT OUTER JOIN MovieImage mi ON mi.movie = m " +
            " LEFT OUTER JOIN Review r on r.movie = m GROUP BY m")
    Page<Object[]> getListPage(Pageable pageable); // 페이지 처리

    @Query("SELECT m, mi, avg(coalesce(r.grade, 0)), count(r) " +
            " from Movie m LEFT OUTER JOIN MovieImage mi on mi.movie = m" +
            " LEFT OUTER JOIN Review r on r.movie = m " +
            " WHERE m.id = :id GROUP BY mi")
    List<Object[]> getMovieWithAll(Long id); // 특정 영화 조회


}
