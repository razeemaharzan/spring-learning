package com.example.demo;

import com.example.demo.domain.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER') or hasPermission('READ')")
    public ResponseEntity<List<Users>> findAll() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

}
