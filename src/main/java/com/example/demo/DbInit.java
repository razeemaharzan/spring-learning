package com.example.demo;

import com.example.demo.domain.Permission;
import com.example.demo.domain.Role;
import com.example.demo.domain.Users;
import com.example.demo.enums.PermissionEnum;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DbInit implements CommandLineRunner {

    private static final String ADMIN = "ADMIN";

    private static final String USER = "USER";

    private final UserService userService;

    private final PermissionService permissionService;

    private final RoleService roleService;

    public DbInit(UserService userService, PermissionService permissionService, RoleService roleService) {
        this.userService = userService;
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    private Set<Permission> getPermissions() {

        Set<Permission> permissions = new HashSet();

        permissions.add(permissionService.save(new Permission(PermissionEnum.READ)));

        permissions.add(permissionService.save(new Permission(PermissionEnum.CREATE)));

        permissions.add(permissionService.save(new Permission(PermissionEnum.UPDATE)));

        permissions.add(permissionService.save(new Permission(PermissionEnum.DELETE)));

        return permissions;

    }

    private Map<String, Role> getRoles(Set<Permission> permissions) {

        Map<String, Role> roles = new HashMap();

        Role adminRole = new Role("ADMIN", permissions);

        Role normalUserRole = new Role("USER", permissions
                .stream()
                .filter(
                        permission ->
                                (permission.getName() == PermissionEnum.READ
                                        ||
                                        permission.getName() == PermissionEnum.UPDATE

                                )
                )
                .collect(Collectors.toSet())
        );


        roles.put(ADMIN, roleService.save(adminRole));

        roles.put(USER, roleService.save(normalUserRole));

        return roles;

    }


    @Override
    public void run(String... args) {

        Set<Permission> permissions = getPermissions();

        Map<String, Role> roles = getRoles(permissions);

        Users admin = Users.builder()
                .email("abc@jklsdf.sdkljf")
                .username("admin")
                .password("admin")
                .roles(new HashSet<>(Arrays.asList(roles.get(ADMIN))))
                .build();

        Users normalUser = Users
                .builder()
                .email("user@jklsdf.sdkljf")
                .username("user")
                .password("user")
                .roles(new HashSet<>(Arrays.asList(roles.get(USER))))
                .build();

        userService.save(admin);
        userService.findByUsername("admin");

        userService.save(normalUser);
    }

}
