package com.example.demo.service;

import com.example.demo.domain.Users;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserService service;

    private Users user;

    @BeforeEach
    void setUp() {
        user = Users.builder()
                .username("spring")
                .password("spring000")
                .email("spring@whatever.kll")
                .build();
    }

    @Test
    public void whenSaveUserShouldReturnUser() {
        when(
                repository.save(any(Users.class))
        ).thenReturn(user);

        Users entity = service.save(user);
        assertThat(entity.getUsername()).isSameAs(user.getUsername());

    }

    @Test
    public void whenfindAllShouldReturnAllUsers() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        Page<Users> users = new PageImpl(Arrays.asList(user), page, 1);
        when(
                repository.findAll(any(Users.class), any(Pageable.class))
        ).thenReturn(users);

        Page<Users> entity = service.findAll(user, page);
        assertThat(entity).isNotNull();
        assertThat(entity.getTotalElements()).isEqualTo(1);

    }
    @Test
    public void whenfindByUsernameShouldReturnUser() {
        when(
                repository.findByUsername(anyString())
        ).thenReturn(Optional.of(user));

        Users entity = service.findByUsername("spring");

        assertThat(entity).isNotNull();
        assertThat(entity.getUsername()).isNotNull();
        assertThat(entity.getEmail()).isEqualToIgnoringCase("spring@whatever.kll");

    }
}