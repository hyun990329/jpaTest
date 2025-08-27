package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import com.my.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.management.Query;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class QueryServiceTest {
    @Autowired
    QueryService queryService;

    @Test
    @DisplayName("이만기를 ID로 찾기")
    void dynamicQuery() {
        List<Member> members = queryService.dynamicQuery();
        members.forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("팀 전체 가져오기")
    void findAllTeam() {
        List<Team> teamList = queryService.findAllTeam();
        teamList.forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("중간 과제 1")
    void findMemberSsirum() {
        queryService.findMemberSsirum()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("중간 과제 2")
    void teamCount() {
        System.out.println("씨름 팀 인원 수 : " +
                queryService.teamCount());
    }

    @Test
    @DisplayName("중간 과제 3")
    void getMemberDto() {
        queryService.getMemberDto()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("중간 과제 3")
    void getProjection() {
        queryService.getProjection()
                .forEach(x -> System.out.println(x));
    }
}