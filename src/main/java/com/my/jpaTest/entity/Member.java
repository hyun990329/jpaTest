package com.my.jpaTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    private String memberId;
    private String name;

    // 팀 정보를 갖는 연관 관계를 구성
    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

}
