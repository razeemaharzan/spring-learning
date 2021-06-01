package com.example.demo.service;

import com.example.demo.domain.Users;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<Users> getAllUsers() {
        return repository.findAll();
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
