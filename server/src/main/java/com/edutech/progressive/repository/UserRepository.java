package com.edutech.progressive.repository;

import com.edutech.progressive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByStudent_StudentId(int studentId);
}