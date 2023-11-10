package com.iot.travel.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.iot.travel.entity.User;

import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUsers() {
        IntStream.rangeClosed(1,4).forEach(i -> {
            User user = User.builder()
                    .nickname("user"+i)
                    .build();

            userRepository.save(user);
        });
    }
}
