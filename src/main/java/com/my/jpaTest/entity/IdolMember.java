package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdolMember {
    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "girlGroup_id")
    private GirlGroup girlGroup;
}
