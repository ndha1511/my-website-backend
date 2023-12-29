package com.example.myshop.controllers;

import com.example.myshop.dtos.UserLogin;
import com.example.myshop.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

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
        return userRepository.findByEmail("teo5@gmail.com").get().getEmail();
    }

    @PostMapping("/hi")
    public String test3(@RequestBody UserLogin userLogin) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userLogin.getEmail(),
//                        userLogin.getPassword()));
//        if(authentication.isAuthenticated())
//            return userService.generateToken(userLogin.getEmail());
        return "email or password invalid";
    }

}
