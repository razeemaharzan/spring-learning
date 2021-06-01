package com.example.demo.repository;

import com.example.demo.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findAll();

    Optional<Users> findByUsername(String name);
}
