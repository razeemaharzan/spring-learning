package com.example.demo.repository;

import com.example.demo.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findAll();

    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions p WHERE u.username = ?1")
    Optional<Users> findByUsername(String username);
}
