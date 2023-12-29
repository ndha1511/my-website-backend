package com.example.myshop.controllers;

import com.example.myshop.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;
    @GetMapping
    public String test() {
        return "hello";
    }
    @GetMapping("/home")
    public String test2() {
        return userRepository.findByEmail("teo8@gmail.com").get().getRole();
    }

    @GetMapping("/admin")
    public String test3() {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userLogin.getEmail(),
//                        userLogin.getPassword()));
//        if(authentication.isAuthenticated())
//            return userService.generateToken(userLogin.getEmail());
        return "hi admin";
    }

    @GetMapping("/user")
    public String test4() {
        return "hi user";
    }

}
