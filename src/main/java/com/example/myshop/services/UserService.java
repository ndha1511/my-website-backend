package com.example.myshop.services;

import com.example.myshop.dtos.RegisterRequest;
import com.example.myshop.dtos.RegisterResponse;
import com.example.myshop.models.User;
import com.example.myshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
    public void save(User user) {
        userRepository.save(user);
    }
    public RegisterResponse register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        registerRequest.setPassword(encoder.encode(registerRequest.getPassword()));
        Optional<User> optional = userRepository.findByEmail(email);
        if(optional.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime rs = now.plusHours(24);
            User user = User.builder()
                    .firstName(registerRequest.getFirstName())
                    .lastName(registerRequest.getLastName())
                    .email(registerRequest.getEmail())
                    .password(registerRequest.getPassword())
                    .role("ROLE_USER")
                    .enable(false)
                    .verificationCode(generateVerificationCode())
                    .verificationDate(rs)
                    .build();
            userRepository.save(user);
            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .verificationCode(user.getVerificationCode())
                    .verificationDate(user.getVerificationDate())
                    .build();
        }
        return null;

    }

    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            stringBuilder.append(randomNumber);
        }

        return stringBuilder.toString();
    }
}
