package com.example.demo.service;

import com.example.demo.domain.Permission;
import com.example.demo.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository repository;

    public Permission findByName(String name) throws IllegalArgumentException {

        return repository
                .findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Permission"));

    }
    public Permission save(Permission permission){
        return repository.save(permission);
    }

    public List<Permission> saveAll(Set<Permission> permissions){
        return repository.saveAll(permissions);
    }

}
