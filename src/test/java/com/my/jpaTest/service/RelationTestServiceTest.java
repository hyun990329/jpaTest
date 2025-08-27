package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import com.my.jpaTest.entity.Parent;
import com.my.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class RelationTestServiceTest {
    @Autowired
    RelationTestService relationTestService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("MemberAndTeam Insert Test")
    void insertTest() {
        relationTestService.insertMemberAndTeam();
    }

    @Test
    @DisplayName("단방향 연관 관계 설정 후 팀 이름 찾기")
    void findTeamName() {
        // jang 의 정보를 가져온다
        Member jang = em.find(Member.class, "jang");
        // team 정보 출력
        System.out.println("Team Name : " + jang.getTeam().getTeamName());
    }

    @Test
    @DisplayName("양방향 연관 관계 설정 후 저장하기")
    void saveBothData() {
        relationTestService.insertBothRelation();
    }

    @Test
    @DisplayName("씨름 팀 멤버 출력하기")
    void 씨름팀멤버출력() {
        Team team = em.find(Team.class, "ssirum");
        List<Member> members = team.getMemberList();
        for (Member m : members) {
            System.out.println(m.getName());
        }
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    void persistTest() {
        relationTestService.saveChildren();
    }

    @Test
    @DisplayName("부모 삭제 테스트")
    void deleteParent() {
        relationTestService.deleteParent();
    }

    // 장원영 (key : jang) 의 팀 이름을 가져오기
//    @Test
//    @DisplayName("Id : Jang 의 팀 이름 찾기 테스트")
//    void findTeamName() {
//        // 1. member 에서 jang 을 찾아온다
//        Member jang = em.find(Member.class, "jang");
//        // 2. member 의 teamId 로 team 에서 검색한다
//        Team team = em.find(Team.class, jang.getTeamId());
//        System.out.println("Team Name : " + team.getTeamName());
//    }
}