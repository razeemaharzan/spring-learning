package com.example.demo.repository;

import com.example.demo.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends BaseRepository<Permission> {
    Optional<Permission> findByName(String name);
}
