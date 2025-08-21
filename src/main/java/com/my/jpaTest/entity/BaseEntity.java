package com.my.jpaTest.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
// BaseEntity 의 ToString 호출
@ToString(callSuper = true)
public class BaseEntity {
    // 데이터가 추가 될 때 입력일이 자동 추가
    @CreatedDate
    LocalDateTime insertedAt;

    // 데이터가 수정 될 때 수정일이 자동 추가
    @LastModifiedDate
    LocalDateTime updatedAt;
}
