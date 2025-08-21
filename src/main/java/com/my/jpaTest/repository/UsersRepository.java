package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // Query Method 생성
    // 1. 이름으로 검색하기
    // SELECT * FROM users WHERE name = '장원영';
    // findByName(String searchName);
    List<Users> findByName(String searchName);

    // 2. 상위 3개 같은 색상 정보 찾기
    // SELECT * FROM users WHERE color = 'pink' limit 3;
    List<Users> findByLikeColor(String color);

    // 3. 성별이 여자이고 좋아하는 색상이 Red 인 자료 찾기
    // SELECT * FROM users WHERE gender = 'Female' AND like_color = 'Red'
    List<Users> findByGenderAndLikeColor(Gender gender, String color);

    // 4. 범위 검색 (날짜, 시간)
    // 어제 이후 생성 된 (created_At) 자료 검색하기
    // SELECT * FROM users WHERE created_at >= '어제'
    List<Users> findByCreatedAtAfter(LocalDateTime searchDate);

    // 5. 최근 1개월 자료 검색하기
    List<Users> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
