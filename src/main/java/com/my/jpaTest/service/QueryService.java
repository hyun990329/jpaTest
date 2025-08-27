package com.my.jpaTest.service;

import com.my.jpaTest.dto.MemberDto;
import com.my.jpaTest.dto.MemberProjection;
import com.my.jpaTest.entity.Member;
import com.my.jpaTest.entity.Team;
import com.my.jpaTest.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QueryService {
    @Autowired
    EntityManager em;
    // 이름이 이만기인 데이터를 검색
    public List<Member> dynamicQuery() {
        String sql = "SELECT m FROM Member m WHERE m.memberId=:id";
        TypedQuery<Member> Query = em.createQuery(sql, Member.class)
                .setParameter("id", "lee");
        List<Member> members = Query.getResultList();
        return members;
    }

    // 팀 전체 검색하기
    public List<Team> findAllTeam() {
        String sql = "SELECT t FROM Team t";
        Query query = em.createQuery(sql);
        List<Team> teamList = query.getResultList();
        return teamList;
    }

    // 멤버 테이블에서 씨름팀에 속해있는 멤버의 이름만 출력
    public List<Member> findMemberSsirum() {
        String sql = "SELECT m FROM Member m " +
                " WHERE m.team.teamName LIKE :teamName";
        TypedQuery<Member> results = em.createQuery(sql, Member.class)
                .setParameter("teamName", "씨름%");
        return results.getResultList();
    }

    // 씨름 팀의 인원 수 구하기
    public Long teamCount() {
        String sql = "SELECT COUNT(m) FROM Member m " +
                " WHERE m.team.teamName Like :teamName";
        Query query = em.createQuery(sql)
                .setParameter("teamName", "씨름%");
        Long result = (Long) query.getSingleResult();
        return result;
    }

    // MemberDTO 로 결과 받기
    public List<MemberDto> getMemberDto() {
        String sql = "SELECT NEW " +
                "com.my.jpaTest.dto.MemberDto(m.name, m.teamName) " +
                "FROM Member m";
        TypedQuery<MemberDto> query = em.createQuery(sql, MemberDto.class);
        return query.getResultList();
    }

    @Autowired
    MemberRepository memberRepository;

    // Projection 을 이용해서 데이터 받아오기
    public List<MemberProjection> getProjection() {
        return memberRepository.findProjection();
    }
}
