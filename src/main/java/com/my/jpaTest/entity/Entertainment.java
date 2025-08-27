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
public class Entertainment {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "entertainment", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<GirlGroup> girlGroups = new ArrayList<>();
}
