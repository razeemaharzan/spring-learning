package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Page<Role> findAll(Role role, Pageable pageable) {
        return repository.findAll(role, pageable);
    }

    public Role findByName(String name) throws IllegalArgumentException {

        return repository
                .findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Role"));

    }

    public Role save(Role role) {
        return repository.save(role);
    }

    public List<Role> saveAll(Set<Role> roles) {
        return repository.saveAll(roles);
    }
}
