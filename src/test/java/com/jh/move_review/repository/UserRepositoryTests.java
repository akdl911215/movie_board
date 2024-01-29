package com.jh.move_review.repository;

import com.jh.move_review.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Commit
    @Transactional
    public void testDeleteUser() {

        Long userId = 2L;

        User user = User.builder().id(userId).build();

        reviewRepository.deleteByUser(user);
        userRepository.deleteById(userId);

    }

    @Test
    public void insertUsers() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            User user = User.builder()
                    .email("review" + i + "@name.com")
                    .password("111")
                    .nickname("reviewer" + i)
                    .build();

            userRepository.save(user);
        });
    }
}
