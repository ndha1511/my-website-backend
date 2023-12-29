package com.example.myshop.services;

import com.example.myshop.configs.UserDetailConfig;
import com.example.myshop.dtos.RefreshTokenRequest;
import com.example.myshop.dtos.UserLogin;
import com.example.myshop.dtos.UserLoginResponse;
import com.example.myshop.models.User;
import com.example.myshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserLoginResponse login(UserLogin userLogin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getEmail(),
                        userLogin.getPassword()));

        var user = userRepository.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));
        var jwt = jwtService.generateToken(new UserDetailConfig(user));
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), new UserDetailConfig(user));
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(jwt);
        userLoginResponse.setRefreshToken(refreshToken);
        return userLoginResponse;

    }

    public UserLoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String email = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(email).orElseThrow();
        UserDetailConfig userDetailConfig = new UserDetailConfig(user);
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), userDetailConfig)) {
            var jwt = jwtService.generateToken(userDetailConfig);
            UserLoginResponse userLoginResponse = new UserLoginResponse();
            userLoginResponse.setToken(jwt);
            userLoginResponse.setRefreshToken(refreshTokenRequest.getToken());
            return userLoginResponse;
        }
        return null;
    }
}
