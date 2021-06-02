package com.example.demo.api;

import com.example.demo.domain.Permission;
import com.example.demo.domain.Role;
import com.example.demo.domain.Users;
import com.example.demo.enums.PermissionEnum;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UsersApiTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    private Users user;
    @Autowired
    private TestRestTemplate restTemplate;
    @BeforeEach
    void setup() {

    }

    @Test
    void getByUsername(){
        String password = "admin";
        String username = "admin";

        String url = "http://localhost:"+randomServerPort+"/users/";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setBasicAuth(username, password);

        HttpEntity<Users> requestEntity = new HttpEntity<>(requestHeaders);


        TestRestTemplate testRestTemplate2 = new TestRestTemplate();
        ResponseEntity<Users> responseEntity2 = testRestTemplate2
                .exchange(
                        url+"username/admin",
                        HttpMethod.GET,
                        requestEntity,
                        Users.class
                );
        assertNotNull(responseEntity2);
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
    }

    @Test
    void register() throws Exception {
        Set<Permission> permissions = new HashSet();

        permissions.add(new Permission(PermissionEnum.READ));

        permissions.add(new Permission(PermissionEnum.CREATE));

        Set<Role> roles = new HashSet();
        Role role = new Role();
        role.setName("new");
        role.setPermissions(permissions);
        roles.add(role);

        Users user1 = Users.builder()
                .username("java")
                .password("java")
                .email("java@whatever.kll")
                .roles(roles)
                .build();

        String password = "admin";
        String username = "admin";

        String url = "http://localhost:"+randomServerPort+"/users/create";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setBasicAuth(username, password);

//        String authorizationHeader = "Basic " + DatatypeConverter.printBase64Binary((username + ":" + password).getBytes());
//
//        HttpHeaders requestHeaders2 = new HttpHeaders();
//
//        requestHeaders2.add("Authorization", authorizationHeader);
//        requestHeaders2.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Users> requestEntity = new HttpEntity<>(user1, requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Users> responseEntity = restTemplate.postForObject(url, requestEntity, ResponseEntity.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }


}