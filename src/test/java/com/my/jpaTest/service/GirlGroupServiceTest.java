package com.my.jpaTest.service;

import com.my.jpaTest.entity.GirlGroup;
import com.my.jpaTest.entity.IdolMember;
import com.my.jpaTest.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class GirlGroupServiceTest {
    @Autowired
    GirlGroupService girlGroupService;

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("데이터 삽입")
    void insertData() {
        girlGroupService.insertEnter();
        girlGroupService.insertGirlGroup();
        girlGroupService.insertMember();
    }

    @Test
    @DisplayName("문제 1")
    void Q1() {
        // 지수 정보 가져오기
        IdolMember ji = em.find(IdolMember.class, "지수");
        // 걸그룹, 엔터 이름 출력
        System.out.println("그룹명 : " + ji.getGirlGroup().getName());
        System.out.println("엔터명 : " + ji.getGirlGroup().getEntertainment().getName());
    }

    @Test
    @DisplayName("문제 2")
    void Q2() {
        // 블랙핑크 멤버 출력하기
        GirlGroup girlGroup = em.find(GirlGroup.class, "blackPink");
        List<IdolMember> idolMembers = girlGroup.getIdolMembers();
        for (IdolMember m : idolMembers) {
            System.out.println(m.getName());
        }
    }
}