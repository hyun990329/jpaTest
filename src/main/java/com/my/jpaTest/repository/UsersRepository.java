package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

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

    // 6. 좋아하는 색상이 Pink 또는 Red 인 모든 자료 출력
    // SELECT * FROM users WHERE like_color in('Pink', 'Red');
    // In 구문자는 List 를 인자로 쓴다
    List<Users> findByLikeColorIn(List<String> colors);

    // 7. ID가 91번 이상인 자료를 출력
    // SELECT * FROM users WHERE id >= 91;
    // null 값 비교 : Null or IsNotNull
    List<Users> findByIdGreaterThanEqual(Long id);

    // 8. 문자열 관련 메서드 함수
    // StartingWith : 주어진 문자열로 시작하는 데이터
    // EndingWith : 주어진 문자열로 끝나는 데이터
    // Contains : 포함 된 자료
    // Like : 사용 시 넘겨주는 인자 값 양쪽에 % 를 붙여주어야 한다
    // 8.1. 이름이 D로 시작하는 데이터 전체 출력
    List<Users> findByNameStartingWith(String x);
    // 8.2 이름이 s로 끝나는 데이터 전체 출력
    List<Users> findByNameEndingWith(String x);
    // 8.3 이메일에 org 를 포함하는 데이터 (Contains / Like)
    List<Users> findByEmailContains(String x);
    List<Users> findByEmailLike(String x);

    // 9. 정렬
    // id : 1 ~ 10 까지 이름의 내림차순으로 정렬
    // SELECT * FROM users WHERE id BETWEEN 1 to 10 ORDER BY name DESC;
    List<Users> findByIdBetweenOrderByNameDesc(Long start, Long end);

    // Quiz
    // Orange 색상 중 Gender 의 오름차순, CreatedAt 에 내림차순으로 정렬
    List<Users> findByLikeColorOrderByGenderAscCreatedAtDesc(String color);

    // 10. Sort 사용하기
    List<Users> findByLikeColor(String color, Sort sort);




    // quiz 1
    List<Users> findByGenderAndNameContainsOrGenderAndNameContains(Gender gender1, String x, Gender gender2, String y);

    // quiz 2
//    List<Users> findByEmailContains(String email);

    // quiz 3
    List<Users> findByUpdatedAtGreaterThanEqualAndNameLike(LocalDateTime start, String data);

    // quiz 4
    List<Users> findTop10ByOrderByCreatedAtDesc();

    // quiz 5
//    List<Users> findByGenderAndLikeColor(Gender gender, String color);

    // quiz 6
//    List<Users> errorDataList();

    // quiz 7
    List<Users> findByGenderAndEmailContainsOrderByCreatedAtDesc(Gender gender, String email);

    // quiz 8
//    List<Users> findByLikeColorOrderByFavoriteColorAscNameDesc(String color);

    // quiz 10
    Page<Users> findByGender(Gender gender, Pageable pageable);

}
