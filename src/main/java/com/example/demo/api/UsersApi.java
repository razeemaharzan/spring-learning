package com.example.demo.api;

import com.example.demo.domain.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersApi {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Users> register(Users user){
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
