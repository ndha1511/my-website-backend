package com.example.myshop.controllers;

import com.example.myshop.dtos.RefreshTokenRequest;
import com.example.myshop.dtos.UserLogin;
import com.example.myshop.dtos.UserLoginResponse;
import com.example.myshop.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = {"http://localhost:3000"})
public class AuthController {
    private final AuthService authService;
    @PostMapping("/token")
    public ResponseEntity<UserLoginResponse> token(@RequestBody UserLogin userLogin) {
        return ResponseEntity.ok(authService.login(userLogin));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<UserLoginResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
