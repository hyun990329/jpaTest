package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContextService {
    @Autowired
    EntityManager em;

    // 자료를 저장하는 작업을 수행
    public Member memberInsert() {
        Member yan = Member.builder()
                .memberId("yan")
                .name("김얀얀")
                .build();
        // 영속성 공간에 저장하는 명령
        em.persist(yan);

        // member_id : yan 인 record를 찾아서 반환
        // Member 테이블에서 키 값이 yan 인 값을 찾기
        Member won = em.find(Member.class, "yan");
        return won;
    }
}
