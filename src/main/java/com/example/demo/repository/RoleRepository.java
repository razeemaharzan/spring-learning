package com.example.demo.repository;

import com.example.demo.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role> {
    Optional<Role> findByName(String name);


}

