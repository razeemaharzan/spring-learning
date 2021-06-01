package com.example.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainApi {
    @GetMapping(value = "/resource")
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", 3);
        model.put("content", "Hello World");
        return model;
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
    @PostMapping("/logout")
    public String logout(){
        return "Logout successfully";
    }

}
