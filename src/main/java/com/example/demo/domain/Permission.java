package com.example.demo.domain;

import com.example.demo.enums.PermissionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Permission extends DomainEntity {


    private PermissionEnum name;

/*    For bi-drectional
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<Role>();*/

    public Permission(PermissionEnum name) {
        this.name = name;
    }
}