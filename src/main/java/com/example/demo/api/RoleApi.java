package com.example.demo.api;

import com.example.demo.domain.Role;
import com.example.demo.domain.Users;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/role")
public class RoleApi {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        if (Objects.isNull(role)
                || Objects.isNull(roleService.findByName(role.getName()))) {
            ResponseEntity.badRequest().body("Invalid request.");
        }
        roleService.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasPermission('READ')")
    public ResponseEntity<List<Users>> findAll(Role role, Pageable pageable) {

        return new ResponseEntity(roleService.findAll(role, pageable), HttpStatus.OK);
    }
}
