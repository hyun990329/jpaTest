package com.my.jpaTest.quiz;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import com.my.jpaTest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuizTest {
    @Autowired
    UsersRepository userRepository;

    @Test
    @Transactional
    @DisplayName("Given/When/Then 으로 테스트하기")
    void assertThatTest() {
        // 신규 데이터 추가 테스트

        // Given
        Users jin = Users.builder()
                .name("안유진")
                .email("jin@korea.com")
                .gender(Gender.Female)
                .likeColor("Red")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // When
        userRepository.save(jin);

        // Then
        // 이름으로 검색한 결과와 jin 이 같으면 성공
        Users result = userRepository.findByName("안유진").get(0);

        // 검사
        Assertions.assertThat(result.getEmail()).isEqualTo(jin.getEmail());
    }

    @Test
    @DisplayName("문제 1")
    void findByGenderAndNameContainsOrGenderAndNameContains() {
        userRepository
                .findByGenderAndNameContainsOrGenderAndNameContains(Gender.Female, "w", Gender.Female , "m")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 2")
    void findByEmailContains() {
        System.out.println(userRepository.findByEmailContains("net")
                .stream().count());
    }

    @Test
    @DisplayName("문제 3")
    void findByUpdatedAtGreaterThanEqualAndNameLike() {
        LocalDate baseDate = LocalDate.now()
                .minusMonths(1L)
                .plusDays(1L);
        LocalDateTime start = baseDate.atStartOfDay();
        userRepository.findByUpdatedAtGreaterThanEqualAndNameLike(start, "J%");
    }

    @Test
    @DisplayName("문제 4")
    void findTop10ByOrderByCreatedAtDesc() {
        List<Users> result = userRepository.findTop10ByOrderByCreatedAtDesc();

        for (Users user : result) {
            System.out.println("ID : " + user.getId() +
                    ", Name : " + user.getName() +
                    ", Gender : " + user.getGender() +
                    ", CreatedAt : " + user.getCreatedAt());
        }
    }

    @Test
    @DisplayName("문제 5")
    void findByGenderAndLikeColor() {
        List<Users> result = userRepository.findByGenderAndLikeColor(Gender.Male, "Red");

        for (Users user : result) {
            String email = user.getEmail();
            String account = email.substring(0, email.indexOf("@"));
            System.out.println("Email : " + email + ", Account : " + account);
        }
    }

    @Test
    @DisplayName("문제 6")
    void errorDataList() {
        List<Users> result = userRepository.findAll();
        for (Users user : result) {
            if (user.getCreatedAt().isBefore(user.getUpdatedAt())) {
                System.out.println(user);
            }
        }
    }

    @Test
    @DisplayName("문제 7")
    void findByGenderAndEmailContainsOrderByCreatedAtDesc() {
        userRepository
                .findByGenderAndEmailContainsOrderByCreatedAtDesc(Gender.Female, "edu")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 8")
    void findByLikeColorOrderByFavoriteColorAscNameDesc() {
        List<Users> result = userRepository.findAll(
                Sort.by(Sort.Order.asc("likeColor")
                ,Sort.Order.desc("name"))
        );
    }

    @Test
    @DisplayName("문제 9")
    void sortAndPaging() {
        Sort sort = Sort.by(Sort.Order.desc("updatedAt"));
        Pageable pageable = PageRequest.of(0, 10, sort);
        userRepository.findAll(pageable).getContent()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 10")
    void manDataPaging() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(1, 3, sort);
        userRepository.findByGender(Gender.Male, pageable)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 11")
    void findByCreatedAtLastMonth() {
        LocalDate thisMonthFirstDay = LocalDate.now().withDayOfMonth(1);
        LocalDate lastMonthFirstDay = thisMonthFirstDay.minusMonths(1);

        LocalDateTime start = lastMonthFirstDay.atStartOfDay();
        LocalDateTime end = thisMonthFirstDay.atStartOfDay();

        userRepository.findByCreatedAtBetween(start, end)
                .forEach(x -> System.out.println(x));
    }

}

