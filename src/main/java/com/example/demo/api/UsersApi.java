package com.example.demo.api;

import com.example.demo.domain.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UsersApi {

    @Autowired
    private UserService userService;

    @PostMapping(
            value = "/create", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN') or hasPermission('READ')")
    public ResponseEntity<Users> create(@RequestBody Users user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasPermission('READ')")
    public ResponseEntity<List<Users>> findAll(Users user, Pageable pageable) {
        if (Objects.isNull(user)
                || Objects.isNull(userService.findByUsername(user.getUsername()))) {
            ResponseEntity.badRequest().body("Invalid request.");
        }

        return new ResponseEntity(userService.findAll(user, pageable), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasPermission('READ')")
    public ResponseEntity<List<Users>> findByUsername(@PathVariable String username) {
        return new ResponseEntity(userService.findByUsername(username), HttpStatus.OK);
    }

}
