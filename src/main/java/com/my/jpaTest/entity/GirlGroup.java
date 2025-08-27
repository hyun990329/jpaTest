package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GirlGroup {
    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "entertainment_id")
    private Entertainment entertainment;

    @OneToMany(mappedBy = "girlGroup", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<IdolMember> idolMembers = new ArrayList<>();
}
