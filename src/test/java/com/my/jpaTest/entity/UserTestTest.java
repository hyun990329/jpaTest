package com.my.jpaTest.entity;

import com.my.jpaTest.repository.UserTestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserTestTest {
    @Autowired
    UserTestRepository userTestRepository;

    @Test
    @DisplayName("userTest 테이블 입력 테스트")
    void userTestInput() {
        UserTest user = new UserTest();
        user.setName("장원영");
        user.setEmail("a@b.c");
        userTestRepository.save(user);
    }

    @Test
    @DisplayName("테이블 수정 테스트")
    void userTestUpdate() {
        // 1번 아이디 정보를 가져와 이메일 수정
        UserTest jang = userTestRepository.findById(1L).orElse(null);
        jang.setEmail("jang@b.c");
        userTestRepository.save(jang);
    }
}