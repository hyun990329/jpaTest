package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("1. findByName 테스트")
    void findByNameTest() {
        String name = "Miriam Denys";
        usersRepository
                .findByName(name)
                .stream()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("2. 핑크 색상 상위 3개 찾기 테스트")
    void findByLikeColor() {
        String color = "Pink";
        usersRepository
                .findByLikeColor(color)
                .stream()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("3. 성별이 여자이고 Red 색상을 좋아하는 값 찾기 테스트")
    void findByGenderAndLikeColor() {
        Gender gender = Gender.Female;
        String color = "Red";
        usersRepository
                .findByGenderAndLikeColor(gender, color)
                .stream()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("4. 어제 이후로 생성 된 자료 찾기 테스트")
    void findByCreatedAtAfter() {
        LocalDate yesterday = LocalDate.now().minusDays(1L);
        LocalDateTime start = yesterday.atTime(23, 59, 59);
        usersRepository
                .findByCreatedAtAfter(start)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("5. 최근 한 달 (오늘 포함) 자료 찾기 테스트")
    void findByCreatedAtBetween() {
        // 한 달 이전의 기준일 설정
        LocalDate baseDate = LocalDate.now().minusMonths(1L);
        // 한 달 전 날에 시 분 초를 붙인다
        LocalDateTime start = baseDate.atStartOfDay();
        LocalDateTime end = LocalDateTime.now();
        usersRepository
                .findByCreatedAtBetween(start, end)
                .forEach(x -> System.out.println(x));
    }
}