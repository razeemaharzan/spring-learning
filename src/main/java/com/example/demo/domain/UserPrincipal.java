package com.example.demo.domain;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> roles;
    private Collection<? extends GrantedAuthority> permissions;
    private Users users;

    public  static UserPrincipal createUserPrincipal(Users users) {
        if (users != null) {
            List<GrantedAuthority> roles= users.getRoles().stream().filter(Objects::nonNull)
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            List<GrantedAuthority> permissions = users.getRoles().stream().filter(Objects::nonNull)
                    .map(Role::getPermissions).flatMap(Collection::stream)
                    .map(permission-> new SimpleGrantedAuthority(permission.getName().name()))
                    .collect(Collectors.toList());

            return UserPrincipal.builder()
                    .id(users.getId())
                    .name(users.getUsername())
                    .email(users.getEmail())
                    .roles(roles)
                    .permissions(permissions)
                    .build();
        }
        return null;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>(this.users.getRoles());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}