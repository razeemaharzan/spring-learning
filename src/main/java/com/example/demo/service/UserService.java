package com.example.demo.service;

import com.example.demo.domain.Users;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Page<Users> findAll(Users user, Pageable pageable) {
        return repository.findAll(user, pageable);
    }


    public Users findByUsername(String username) throws IllegalArgumentException {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username"));
    }

    public Users save(Users users) {
        return repository.save(users);
    }


}
