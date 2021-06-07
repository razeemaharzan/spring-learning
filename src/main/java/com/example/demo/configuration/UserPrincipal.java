package com.example.demo.configuration;

import com.example.demo.domain.Role;
import com.example.demo.domain.Users;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private Long id;
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> roles;
    private Collection<? extends GrantedAuthority> permissions;
    private Users user;

    public UserPrincipal(Users user) {
        this.user = user;
        createUserPrincipal(user);

    }

    public void createUserPrincipal(Users users) {
        if (users != null) {
            List<GrantedAuthority> roles = users.getRoles().stream().filter(Objects::nonNull)
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            List<GrantedAuthority> permissions = users.getRoles().stream().filter(Objects::nonNull)
                    .map(Role::getPermissions).flatMap(Collection::stream)
                    .map(permission -> new SimpleGrantedAuthority(permission.getName().name()))
                    .collect(Collectors.toList());

            this.setId(users.getId());

            this.setName(users.getUsername());
            this.setEmail(users.getEmail());
            this.setRoles(roles);
            this.setPermissions(permissions);

        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>(this.user.getRoles());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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