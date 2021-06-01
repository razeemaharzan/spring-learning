package com.example.demo.domain;

import com.example.demo.enums.PermissionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private PermissionEnum name;


    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<Role>();

    public Permission(PermissionEnum name) {
        this.name = name;
    }
}