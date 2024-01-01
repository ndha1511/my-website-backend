package com.example.myshop.controllers;

import com.example.myshop.dtos.*;
import com.example.myshop.models.User;
import com.example.myshop.services.AuthService;
import com.example.myshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = {"http://localhost:3000"})
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    @PostMapping("/token")
    public ResponseEntity<UserLoginResponse> token(@RequestBody UserLogin userLogin) {
        UserLoginResponse userLoginResponse = authService.login(userLogin);
        if(userLoginResponse == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userLoginResponse);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<UserLoginResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = userService.register(registerRequest);
        if(registerResponse != null) {
            return ResponseEntity.ok(registerResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/checkCode")
    public ResponseEntity<?> checkVerificationCode(String verificationCode, String email, String password) {
        User user = userService.findByEmail(email);
        if(Objects.equals(user.getVerificationCode(), verificationCode) &&
                LocalDateTime.now().isBefore(user.getVerificationDate())
        ) {
            user.setEnable(true);
            userService.save(user);
            UserLoginResponse userLoginResponse = authService.login(UserLogin.builder()
                    .email(email)
                    .password(password)
                    .build());;

            return ResponseEntity.ok(userLoginResponse);
        }
        return ResponseEntity.badRequest().build();
    }
}
