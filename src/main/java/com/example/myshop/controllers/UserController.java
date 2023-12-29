package com.example.myshop.controllers;

import com.example.myshop.models.User;
import com.example.myshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = {"http://localhost:3000"})
public class UserController {
    private final UserService userService;

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
