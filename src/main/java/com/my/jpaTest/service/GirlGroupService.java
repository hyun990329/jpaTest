package com.my.jpaTest.service;

import com.my.jpaTest.entity.Entertainment;
import com.my.jpaTest.entity.GirlGroup;
import com.my.jpaTest.entity.IdolMember;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GirlGroupService {
    @Autowired
    EntityManager em;

    private Entertainment starship;
    private Entertainment YG;
    private GirlGroup ive;
    private GirlGroup blackPink;

    public void insertEnter() {
        starship = Entertainment.builder()
                .id("starship")
                .name("스타쉽")
                .build();
        em.persist(starship);

        YG = Entertainment.builder()
                .id("YG")
                .name("와이지")
                .build();
        em.persist(YG);
    }

    public void insertGirlGroup() {
        ive = GirlGroup.builder()
                .id("ive")
                .name("아이브")
                .entertainment(starship)
                .build();
        em.persist(ive);

        blackPink = GirlGroup.builder()
                .id("blackPink")
                .name("블랙핑크")
                .entertainment(YG)
                .build();
        em.persist(blackPink);
    }

    public void insertMember() {
        IdolMember ahn = IdolMember.builder()
                .id("안유진")
                .name("유진")
                .girlGroup(ive)
                .build();
        em.persist(ahn);

        IdolMember jang = IdolMember.builder()
                .id("장원영")
                .name("원영")
                .girlGroup(ive)
                .build();
        em.persist(jang);

        IdolMember jae = IdolMember.builder()
                .id("제니")
                .name("째니")
                .girlGroup(blackPink)
                .build();
        em.persist(jae);

        IdolMember ji = IdolMember.builder()
                .id("지수")
                .name("지수다")
                .girlGroup(blackPink)
                .build();
        em.persist(ji);
    }
}
