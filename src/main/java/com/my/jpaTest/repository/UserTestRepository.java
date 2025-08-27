package com.my.jpaTest.repository;

import com.my.jpaTest.dto.MemberProjection;
import com.my.jpaTest.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTestRepository extends JpaRepository<UserTest, Long> {


}
