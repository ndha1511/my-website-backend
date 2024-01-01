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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    public UserLoginResponse login(UserLogin userLogin) {
        Optional<User> optional = userRepository.findByEmail(userLogin.getEmail());
        if(optional.isEmpty()) {
            return null;
        } else {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getEmail(),
                            userLogin.getPassword()));
            User user = optional.get();
            UserLoginResponse userLoginResponse = new UserLoginResponse();
            if(user.isEnable()) {
                var jwt = jwtService.generateToken(new UserDetailConfig(user));
                var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), new UserDetailConfig(user));
                userLoginResponse.setToken(jwt);
                userLoginResponse.setRefreshToken(refreshToken);
                userLoginResponse.setVerification(true);
            }
            else {
                userLoginResponse.setVerification(false);
            }

            return userLoginResponse;
        }


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
